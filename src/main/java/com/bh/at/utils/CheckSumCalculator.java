package com.bh.at.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckSumCalculator {
  private static final Logger LOG = LoggerFactory.getLogger(CheckSumCalculator.class);

  private CheckSumCalculator() {
    // For SonarLint
  }

  public static FileTemplate getCheckSum(String filePath) throws IOException, NoSuchAlgorithmException {
    Path path = Paths.get(filePath);
    String message = Files.readString(path, StandardCharsets.UTF_8);
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    LOG.info("Size of Checksum : {}", message.getBytes().length);

    md.update(message.getBytes());
    byte[] digest = md.digest();
    String encodedString = Base64.getEncoder().encodeToString(digest);
    LOG.info("Checksum Value : {}", encodedString);

    return new FileTemplate(encodedString, message.getBytes());
  }

  // for timeseries
  public static String getCheckSum(byte[] payload) throws IOException, NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    LOG.info("Size of Checksum : {}", payload.length);

    md.update(payload);
    byte[] digest = md.digest();
    String encodedString = Base64.getEncoder().encodeToString(digest);
    LOG.info("Checksum Value : {}", encodedString);

    return encodedString;
  }
}
