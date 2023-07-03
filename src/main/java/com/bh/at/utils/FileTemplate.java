package com.bh.at.utils;

public class FileTemplate {
  private final String checksum;
  private final byte[] payload;

  public FileTemplate(String checksumValue, byte[] payloadValue) {
    checksum = checksumValue;
    payload = payloadValue;
  }

  public String getCheckSum() {
    return this.checksum;
  }

  public byte[] getPayload() {
    return this.payload;
  }
}
