package com.bh.at.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.at.messagingqueueadapter.IAMQPMessageProperties;
import com.bh.at.messagingqueueadapter.IReceivedHandler;
import com.bh.icommonallutil.IJSONA;
import com.bh.jsonutil.CyJsonUtilFactory;

public abstract class MQUtil {
  private static final Logger LOG = LoggerFactory.getLogger(MQUtil.class);
  AtomicBoolean result = new AtomicBoolean(true);
  public static Stack<String> enterpriseIdsStack = new Stack<>();
  public boolean getAck = false;

  public MQUtil(String enterpriseId) {
    if (enterpriseId != null)
      enterpriseIdsStack.add(enterpriseId);
  }

  public static int numberOfEnterprisesIngested() {
    return enterpriseIdsStack.size();
  }

  public static String getEnterpriseId() {
    if (enterpriseIdsStack.size() <= 0) {
      throw new RuntimeException("No Enterprise Ids in Stack;");
    } else {
      return enterpriseIdsStack.peek();
    }
  }

  public static void clearEnterpriseIds() {
    enterpriseIdsStack.clear();
  }

  public static String popEnterpriseId() {
    if (enterpriseIdsStack.size() <= 0) {
      throw new RuntimeException("No Enterprise Ids in Stack to pop;");
    } else {
      return enterpriseIdsStack.pop();
    }
  }

  public IReceivedHandler getRequestReceiveHandler(int endSequenceNumber, String id, String messageType) {
    return receivedPayload -> {
      LOG.info("Processing Response");
      this.getAck = true;
      String ackMessageName = messageType + "_Acknowledgement";
      IAMQPMessageProperties receivedProperties = receivedPayload.getBasicProperties();
      Map<String, Object> receivedHeaders = receivedProperties.getHeaders();
      String incomingCorrelationId = receivedProperties.getCorrelationId();
      String incomingMessageType = receivedProperties.getType();

      LOG.info("EndSequenceNumber : " + endSequenceNumber);
      LOG.info("Received Message Type " + ackMessageName + "  Send Message Type " + incomingMessageType);
      LOG.info("Incoming-CorrelationID " + incomingCorrelationId + "  Send ID :" + id);

      if (id.equals(incomingCorrelationId) && incomingMessageType.equals(ackMessageName)) {
        String responseStatusCode = receivedHeaders.get("StatusCode").toString();
        if (!responseStatusCode.equals("200")) {
          result.set(false);
          LOG.error("Status Message:" + receivedHeaders.get("StatusMessage").toString());
          LOG.error("Status Code:" + responseStatusCode);
        } else {
          LOG.info("Status Message:" + receivedHeaders.get("StatusMessage").toString());
          LOG.info("Status Code:" + receivedHeaders.get("StatusCode").toString());
        }
      } else {
        LOG.info("Received message has unknown type - Test/Stale message");
      }
    };
  }

  protected static void addEnterpriseId(String enterpriseId) {//add duplicate removal
    if (enterpriseIdsStack.search(enterpriseId) == -1)
      enterpriseIdsStack.add(enterpriseId);//add if not in stack
  }

  protected static String extractEnterpriseID(File inputFile) {
    String enterpriseID = null;
    boolean exist = inputFile.exists();
    boolean isEmpty = inputFile.length() <= 2;
    if (exist && !isEmpty) {
      IJSONA extract = null;
      boolean isFound = false;
      try (FileInputStream fis = new FileInputStream(inputFile)) {
        extract = CyJsonUtilFactory.juf.getJSONAFromInputStream(fis);
        enterpriseID = extract.getJSONO(0).get("Group").toString();
      } catch (Exception e) {
        isFound = true;
      } finally {
        if (isFound && extract != null) {
          enterpriseID = extract.getJSONO(0).get("groupid").toString();
        }
      }
    } else {
      enterpriseID = (java.util.UUID.randomUUID().toString());
    }
    return enterpriseID;

  }
}
