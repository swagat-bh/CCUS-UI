package com.bh.at.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.List;

import com.bh.at.resuepojos.MarkerNode;
import com.bh.jsonutil.CyJSONA;
import com.bh.jsonutil.CyJSONO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.bh.icommonallutil.IJSONA;
import com.bh.icommonallutil.IJSONO;
import com.bh.jsonutil.CyJsonUtilFactory;

import javax.imageio.ImageIO;

public class IAPMUtil {
  private static final int MAX_LAT = 160;
  private static final int MAX_LONG = 75;
  public static final String JSON_FILES_PATH = "/src/main/resources/JSON_Files/";
  public static final String SYSTEM_PATH = System.getProperty("user.dir");
  public static Map<String, File> fileNameAndFileMap = new HashMap<>(100);

  static {
    Collection<File> allJsonFilesPaths = FileUtils.listFiles(new File(System.getProperty("user.dir") + JSON_FILES_PATH), new SuffixFileFilter(".json"), TrueFileFilter.INSTANCE);
    for (Iterator<File> it = allJsonFilesPaths.iterator(); it.hasNext(); ) {
      File next = it.next();
      String fileName = next.getName();
      fileNameAndFileMap.put(fileName, next);
    }
  }

  private IAPMUtil() {
  }

  public static File getFileForName(String fileName) {
    return fileNameAndFileMap.get(fileName);
  }

  public static boolean doesFileExist(String fileName) {
    return fileNameAndFileMap.containsKey(fileName);
  }

   public static int[] getRandomGeoData() {
        double lat = Math.random() * MAX_LAT;
        double longg = Math.random() * MAX_LONG;

        int[] geoLoc = new int[2];
        geoLoc[0] = (int) lat * isNegativeByChance();
        geoLoc[1] = (int) longg * isNegativeByChance();
        return geoLoc;
    }

  public static IJSONO getJSONOFromJSONFile(String fileName) {
    IJSONO arr = CyJsonUtilFactory.juf.getJSONOFromFile(getFileForName(fileName));
    return arr;
  }

  public static IJSONA getJSONAFromJSONFile(String fileName) {
    IJSONA arr = CyJsonUtilFactory.juf.getJSONAFromFile(getFileForName(fileName).getAbsolutePath());
    return arr;
  }

   public static boolean doesAssetExistInUpdatedAssets(List<?> nodes, String assetId) {
        List<?> markerNodeList = (List<?>) nodes;
        for (Object markerNode : markerNodeList) {
            MarkerNode node = (MarkerNode) markerNode;
            if (node.getAssetId().equals(assetId)) {
                return true;
            }
        }
        return false;
    }

    public static IJSONA getAssetIdsForGeoDataFetch(List<MarkerNode> nodes) {
        IJSONA jsonArr = new CyJSONA();
        int i = 0;
        for (Object markerNode : nodes) {
            MarkerNode node = (MarkerNode) markerNode;
            jsonArr.put(i++, node.getAssetId());
        }
        return jsonArr;
    }

    public static boolean isGeoLocSameForAnAssetId(List<?> nodes, MarkerNode node) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(node)) {
                return true;
            }
        }
        return false;
    }

    private static int isNegativeByChance() {
        return Double.compare(Math.random(), .5) > 0 ? -1 : 1;
    }

    private static IJSONO getGeoJsonForMarkerNode(MarkerNode markerNode) {
        IJSONO marker = new CyJSONO();
        IJSONO geoObj = new CyJSONO();
        IJSONO geometry = new CyJSONO();
        IJSONA coords = new CyJSONA();

        coords.put(0, markerNode.getGeoLoc()[0]);
        coords.put(1, markerNode.getGeoLoc()[1]);

        geometry.put("type", "Point");
        geometry.put("coordinates", coords);

        geoObj.put("type", "Feature");
        geoObj.put("properties", "{}");
        geoObj.put("geometry", geometry);

        marker.put("assetId", markerNode.getAssetId());
        marker.put("geo", geoObj);
        marker.put("created_by", "S1CORE");
        return marker;
    }

    public static IJSONA getGeoJSONArrayFromMarkerNodes(List<MarkerNode> nodes) {
        IJSONA array = new CyJSONA();
        int i = 0;
        for (Object node : nodes) {
            array.put(i++, getGeoJsonForMarkerNode((MarkerNode) node));
        }
        return array;
    }

    public static MarkerNode getMarkerNodeFromGDSvcResponse(IJSONO singleObj) {
        String assetId = singleObj.get("assetId");
        IJSONO geoObj = singleObj.get("geo");
        IJSONO geometry = geoObj.get("geometry");
        IJSONA coords = geometry.get("coordinates");
        int arr[] = new int[2];
        arr[0] = coords.get(0);
        arr[1] = coords.get(1);
        return new MarkerNode(assetId, arr);
    }

  public static LocalDateTime getLocalDateTimeFromTicksDateTimeOffset_type0000_01_01(long uTCBasedTicks) {
    long javaBasedTicks = (uTCBasedTicks - 621355968000000000l) / 10000;
    Instant instant = Instant.ofEpochMilli(javaBasedTicks);
    LocalDateTime ltd1 = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    return ltd1;
  }

  public static String getRandomCharsAtoZ(int noOfChars) {
    String allChars = "abcdefghijklmnopqrstuvwxyz";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < noOfChars; i++) {
      int rand = (int) (Math.random() * allChars.length());
      sb.append(allChars.charAt(rand));
    }
    return sb.toString();
  }

    public static byte[] pngBytesToJpgBytes(byte[] pngBytes) throws IOException {
        //create InputStream for ImageIO using png byte[]
        ByteArrayInputStream bais = new ByteArrayInputStream(pngBytes);
        //read png bytes as an image
        BufferedImage bufferedImage = ImageIO.read(bais);

        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

        //create OutputStream to write prepaired jpg bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //write image as jpg bytes
        ImageIO.write(newBufferedImage, "JPG", baos);

        //convert OutputStream to a byte[]
        return baos.toByteArray();
    }
}