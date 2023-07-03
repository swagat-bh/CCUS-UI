package com.bh.at.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GzipCompressionHelper {
  private static final Logger LOG = LoggerFactory.getLogger(GzipCompressionHelper.class);

  GzipCompressionHelper() {
    // For SonarLint
  }

  public static byte[] compressByteArray(byte[] data) throws IOException {

    ByteArrayOutputStream bos = null;
    GZIPOutputStream gzipOS = null;
    try {
      bos = new ByteArrayOutputStream(data.length);
      gzipOS = new GZIPOutputStream(bos);
      gzipOS.write(data);
      gzipOS.close();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        assert gzipOS != null;
        gzipOS.close();
        bos.close();
      } catch (Exception ignored) {
      }
    }
    byte[] output = bos.toByteArray();
    System.out.println("Original: " + data.length + " bytes, -->  Compressed: " + output.length + " bytes");
    return output;
  }

  public static String decompressByteArray(byte[] data) {
    ByteArrayInputStream bis = null;
    ByteArrayOutputStream bos = null;
    GZIPInputStream gzipIS = null;

    try {
      bis = new ByteArrayInputStream(data);
      bos = new ByteArrayOutputStream();
      gzipIS = new GZIPInputStream(bis);

      byte[] buffer = new byte[1024];
      int len;
      while ((len = gzipIS.read(buffer)) != -1) {
        bos.write(buffer, 0, len);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        assert gzipIS != null;
        gzipIS.close();
        bos.close();
        bis.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    String output = bos.toString();
    System.out.println("Original: " + data.length + " bytes, -->  Decompressed: " + output.length() + " bytes");
    return output;
  }

}

    /*public byte[] DeCompress(byte[] input){

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(input);

            GZIPInputStream gzipIn = new GZIPInputStream(bais);
            byte[] buffer = new byte[1024];
            int len;
            while((len = gzipIn.read(buffer)) != -1)
            {
                os.write(buffer, 0, len);
            }
            os.close();
            gzipIn.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        byte[] output = os.toByteArray();
        return output;

}*/


