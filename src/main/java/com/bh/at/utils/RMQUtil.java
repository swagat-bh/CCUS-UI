package com.bh.at.utils;

import static com.bh.at.main.AppConfig.getEnvParam;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import com.bh.at.messagingqueueadapter.exceptions.CommunicationException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.at.messagingqueueadapter.AMQPMessageProperties;
import com.bh.at.messagingqueueadapter.IAMQPMessageProperties;
import com.bh.at.messagingqueueadapter.IReceivedHandler;
import com.bh.at.messagingqueueadapter.exceptions.AMQPException;
import com.bh.at.rabbitmqconnector.RabbitMQProvider;
import com.bh.icommonallutil.IJSONA;
import com.bh.icommonallutil.IJSONO;
import com.bh.jsonutil.CyJSONO;

public class RMQUtil extends MQUtil {
    private static final Logger LOG = LoggerFactory.getLogger(RMQUtil.class);
    RabbitMQProvider rmqProvider = new RabbitMQProvider(getEnvParam("data_API/MQurl", ""));
    Date date = new Date();
    public static final String SYNC_ENTERPRISE = "SyncEnterprise";
    public static final String GET_ADDED_EDGES = "GetAddedEdges";
    public static final String GET_DELETED_NODES = "GetDeletedNodes";
    public static final String GET_UPDATED_EDGES = "GetUpdatedEdges";
    public static final String CONTENT_TYPE = "ContentType";
    public static final String TRANSACTION_ID = "TransactionId";
    public static final String TRANSACTION_STATUS = "TransactionStatus";
    public static final String ENTERPRISE_ID = "EnterpriseId";
    public static final String OFFSET = "Offset";
    public static final String BEGIN_SEQUENCE_NUMBER = "BeginSequenceNumber";
    public static final String END_SEQUENCE_NUMBER = "EndSequenceNumber";
    public static final String SYNC_ENTERPRISE_ACKNOWLEDGEMENT_CODE = "SyncEnterpriseAcknowledgementCode";
    public static String messageTypeResponse;
    public static String id;
    public static String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public IAMQPMessageProperties messageProperties;
    public String enterpriseID;
    public String enterpriseIDTimeseries = "fcd5434e-7417-4292-aa30-96d6d441ddee";
    public boolean getAck = false;
    public String IsoEndTime = null;
    public IReceivedHandler receivedHandler;
    public String output;
    public String tagId;

    public RMQUtil(String enterpriseId) {
        super(enterpriseId);
    }

    public RMQUtil() {
        super(null);
    }

    // TODO: can be used as utility, not in use
    // covert date time to ticks function
    public static File extractTimeStamp(File inputFile) {
        boolean exist = inputFile.exists();
        boolean isEmpty = inputFile.length() <= 2;
        FileWriter fw = null;
        File inFile_ticks = null;
        if (exist && !isEmpty) {

            try {
                String content = Files.readString(Paths.get(String.valueOf(inputFile)));

                IJSONO obj = new CyJSONO(content);
                IJSONO arrObj = obj.getJSONA("primaryData").get(0);
                IJSONA sampleArr = arrObj.getJSONA("samples");
                LOG.info(">>>>>>>>>>arr length" + sampleArr.length());
                for (int i = 0; i <= sampleArr.length() - 1; i++) {
                    IJSONO getTimestamp = arrObj.getJSONA("samples").get(i);
                    String timestamp = getTimestamp.get("timestamp");
                    long timeInTicks = convertDateTimetoTicks(timestamp);
                    getTimestamp.put("timestamp", timeInTicks);
                }
                fw = new FileWriter((System.getProperty("user.dir")) + "/src/main/resources/JSON_Files/EI.TrendDataWithSpeedResponse_toTicks.json");
                fw.write(obj.toString());
                fw.close();

                inFile_ticks = new File((System.getProperty("user.dir")) + "/src/main/resources/JSON_Files/EI.TrendDataWithSpeedResponse_toTicks.json");

                LOG.info("JSON Object Successfully written to the file!!");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return inFile_ticks;
    }

    //for timeseries
    // TODO: can be used as utility, not in use
    //convert DateTime to Ticks
    public static long convertDateTimetoTicks(String timestamp) throws Exception {
        long TICKS_AT_EPOCH = 621355968000000000L;
        long TICKS_PER_MILLISECOND = 10000;

        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        sd.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date d = sd.parse(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        long ticks = (calendar.getTimeInMillis() * TICKS_PER_MILLISECOND) + TICKS_AT_EPOCH;
        return ticks;
    }

    public void waitForResponseMessage() {
        for (int i = 0; i < 6; i++) {
            if (!this.getAck) {
                LOG.info("#####   Waiting for Response");
                Poller.waitForSpecifiedTime(10000);
            } else {
                break;
            }
        }
    }

    // Epoch time convert method for ISO date time with time zone
    public String getEpochToIsoConversion() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        long epochSecond = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(epochSecond);
        IsoEndTime = instant.toString();
        return IsoEndTime;
    }

    public boolean sendMessageToRmq(String messageType, String requestQueue, String responseQueue, String inputFile, int beginSequenceNumber, int endSequenceNumber) {
        return sendMessageToRmq(messageType, requestQueue, responseQueue, inputFile,
                beginSequenceNumber,
                endSequenceNumber,
                UUID.randomUUID().toString());
    }

    public boolean sendMessageToRmq(String messageType, String requestQueue, String responseQueue, String inputFile, int beginSequenceNumber, int endSequenceNumber, String id) {
        File inFile = IAPMUtil.getFileForName(inputFile);
        if (inFile.exists()) {
            addEnterpriseId(extractEnterpriseID(inFile));
            return sendMessageToRmq(messageType, requestQueue, responseQueue, inFile, beginSequenceNumber, endSequenceNumber, id);
        } else {
            LOG.info("File {} for message {} does not exist", inputFile, messageType);
            return false;
        }
    }


    //    for timeseries
    public void subscribeAndSendResponse_timeseries(String messageTypeRequest, String requestQueue, String inputFile, String messageTypeResponse) {
    }

    //    for timeseries
    private void subscribeAndSendResponse_timeseries(String messageTypeRequest, String requestQueue, String inputFile, String messageTypeResponse, String id) {
    }


    private IReceivedHandler getRequestReceiveHandler(String inputFile, String messageTypeResponse, String id) {
        return null;
    }


    public void sendMessageToRmq_timeseries(String messageTypeResponse, String responseQueue, String inputFile, String id) {

    }

    // for timeseries
    private void sendMessageToRmq_timeseries(String messageTypeResponse, String responseQueue, File inputFile, String id) {

    }

    public IAMQPMessageProperties generateMessagePropertyByMessageType(String messageType, String replyQueueName, int beginSequenceNumber, int endSequenceNumber, FileTemplate data, String id) {
        IAMQPMessageProperties messageProperties = new AMQPMessageProperties();


        return messageProperties;
    }

    public IAMQPMessageProperties generateMessagePropertyByMessageTypeForAzureEnv(String messageType, String replyQueueName, int beginSequenceNumber, int endSequenceNumber, FileTemplate data, String id) {
        IAMQPMessageProperties messageProperties = new AMQPMessageProperties();
        return messageProperties;
    }

    //    for timeseries
    private void subscribeAndSendResponse_timeseries(String messageTypeRequest, String requestQueue, String inputFile, String messageTypeResponse, String responseQueue, String id) {

    }

    // TODO use the functions above and make the next one private
    private boolean sendMessageToRmq(String messageType, String requestQueue, String responseQueue, File inputFile, int beginSequenceNumber, int endSequenceNumber, String id) {

        return true;
    }


    private IReceivedHandler getRequestReceiveHandler() {
        return receivedPayload -> {
            this.getAck = true;
        };
    }

    // for timeseries
    private IReceivedHandler getRequestReceiveHandler(String inputFile, String messageTypeResponse, String responseQueue, String id) {
        return null;
    }

    // for timeseries response
    private IAMQPMessageProperties generateMessagePropertyByMessageType_timeseries(String messageTypeResponse, String checksum, String id) {
        IAMQPMessageProperties messageProperties = new AMQPMessageProperties();

        return messageProperties;
    }

    public void initializeConnnectionwithRMQ() throws AMQPException {

    }


    public void createQueue(String queueName) throws CommunicationException {
    }

    public void queueBindingUnBinding(String queueName, String endpointName, String filterFormater, Map<String, Object> oldFilterOptions, Map<String, Object> newFilterOptions) throws IOException, CommunicationException {
    }

    public void declareExchange(String exchangeName) throws AMQPException {
    }

    public void deleteQueue(String queueName) throws AMQPException {
    }

    public void deleteExchange(String endpointName) throws AMQPException {
    }

}
