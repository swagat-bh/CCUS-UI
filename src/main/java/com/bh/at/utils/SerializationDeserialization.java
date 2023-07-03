package com.bh.at.utils;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.icommonallutil.IJSONO;
import com.bh.jsonutil.CyJsonUtilFactory;

public class SerializationDeserialization {
  private static final Logger LOG = LoggerFactory.getLogger(SerializationDeserialization.class);

  SerializationDeserialization() {
    // For SonarLint
  }

  public static byte[] convertStringtoByteArray(String data) {
    byte[] theByteArray = data.getBytes(StandardCharsets.UTF_8);
//        LOG.info("string To Convert into byte array" + theByteArray);
    return theByteArray;
  }

  public static String convertByteArraytoString(byte[] bytes) {
    String byteToString = bytes.toString();
//        LOG.info("Convert Byte Array to String" + byteToString);
    return byteToString;
  }

  public static IJSONO convertStringtoJSONObject(String data) {
    IJSONO obj = CyJsonUtilFactory.juf.getJSONOFromString(data);
//        LOG.info("convert String to JSONObject" + obj);
//        tagId= obj.getJSONA("TagIds").get(0).toString();
//        LOG.info("TagId" + tagId);
//        return tagId;
    return obj;
  }

  public static byte[] convertJSONObjecttoByteArray(IJSONO jsonObj) {
    byte[] byteArr = jsonObj.toString().getBytes(StandardCharsets.UTF_8);
//        LOG.info("convert JSONObject to Byte Array" + obj);
//        tagId= obj.getJSONA("TagIds").get(0).toString();
//        LOG.info("TagId" + tagId);
//        return tagId;
    return byteArr;
  }

}
