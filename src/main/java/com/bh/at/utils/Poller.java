package com.bh.at.utils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.at.iapiutil.IAPIHelper;

public final class Poller {

  private static final Logger LOG = LoggerFactory.getLogger(Poller.class);
  private static String isoEndTime = null;
  public static Map<String, Object> metaInfo = new HashMap<>();

  private Poller() {
    // For SonarLint
  }

  public static void waitForSpecifiedTime(int interval) {
    try {
      Thread.sleep(interval);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean waitForServiceToResponse(IAPIHelper getAPIHelper) {
    boolean isSuccess = false;
    if (getAPIHelper.getActRespBody().get("jsonObj").toString().length() <= 2)
      waitForSpecifiedTime(10000);
    else
      isSuccess = true;

    return isSuccess;
  }

  private static void epochToIsoConversion() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    long epochSecond = System.currentTimeMillis();
    Instant instant = Instant.ofEpochMilli(epochSecond);
    isoEndTime = instant.toString();
    metaInfo.put("ISOEndTime", isoEndTime);
    LOG.info("=============The ISO time from metaInfo" + metaInfo.get(isoEndTime) + "====================");
  }
}
