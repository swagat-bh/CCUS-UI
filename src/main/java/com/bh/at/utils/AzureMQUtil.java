/*
package com.bh.at.utils;

import static com.bh.at.main.AppConfig.getEnvParam;

import java.io.File;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.at.messagingqueueadapter.AMQPMessageProperties;
import com.bh.at.messagingqueueadapter.IAMQPMessageProperties;
import com.bh.at.messagingqueueadapter.IReceivedHandler;
import com.bh.at.messagingqueueadapter.exceptions.AMQPException;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public class AzureMQUtil extends MQUtil {
  private static final Logger LOG = LoggerFactory.getLogger(RMQUtil.class);
  private final String azureConnectionString = getEnvParam("data_API/MQurl", "");
//  AzureMQProvider azureMQProvider = new AzureMQProvider(getEnvParam("data_API/MQurl", ""));
  ;
  Date date = new Date();
  public IAMQPMessageProperties messageProperties;
  public IReceivedHandler receivedHandler;
  public String output;
  public String tagId;
  public String enterpriseIDTimeseries = "fcd5434e-7417-4292-aa30-96d6d441ddee";

  public AzureMQUtil(String enterpriseId) {
    super(enterpriseId);

  }

  public AzureMQUtil() {
    super(null);
  }

  public boolean sendMessageToAzureMQ(String messageType, String requestQueue, String responseQueue, String inputFile,    int beginSequence, int endSequence) {

    return true;
  }

  public void initializeConnnectionwithAZB() throws AMQPException {
  }

  //for timeseriesAZ
  public void sendMessageToRmq_timeseriesAZ(String messageTypeResponse, String responseQueue, String inputFile, String id) {

  }

  //    for timeseriesAZ
  public void createEndpoint(String endpointName) throws AMQPException, ServiceBusException, InterruptedException {
  }

  public void createQueueWithEndpoint(String topicName, String subscriptionName, String filterFormater, Map<String, Object> filterOptions) {
  }

  //    for timeseriesAZ
  public void subscribeAndSendResponse_timeseriesAZ(String messageTypeRequest, String subName, String inputFile, String messageTypeResponse, String endpointName) {
  }

  public void deleteQueueWithEndpoint(String topicName, String subscriptionName) throws AMQPException {
  }

 // public void sendMessageonTopic(String endpointName) {
    azureMQProvider.sendMessageOnTopic(endpointName);
  }

  private IReceivedHandler getRequestReceiveHandler() {
    return receivedPayload -> {
      this.getAck = true;
    };
  }

  // for timeseriesAZ
  private IReceivedHandler getRequestReceiveHandler(String inputFile, String messageTypeResponse) {
    return receivedPayload -> {
      this.getAck = true;
    };
  }

  // for timeseriesAZ
  private void sendMessageToRmq_timeseriesAZ(String messageTypeResponse, String responseQueue, File inputFile, String id) {

  }

  //    for timeseriesAZ
  private void subscribeAndSendResponse_timeseriesAZ(String messageTypeRequest, String subName, String inputFile, String messageTypeResponse, String endpointName, String id) {
  }

  // for timeseriesAZ response
  private IAMQPMessageProperties generateMessagePropertyByMessageType_timeseries(String messageTypeResponse, String checksum, String id) {
    IAMQPMessageProperties messageProperties = new AMQPMessageProperties();
    return messageProperties;
  }
}
*/
