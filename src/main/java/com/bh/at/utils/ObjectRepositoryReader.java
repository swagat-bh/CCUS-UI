package com.bh.at.utils;

import static com.bh.jsonutil.CyJsonUtilFactory.juf;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bh.commonutil.CyBaseRuntimeException;
import com.bh.icommonallutil.IJSONA;
import com.bh.icommonallutil.IJSONO;

public class ObjectRepositoryReader {

  private static final Logger LOG = LoggerFactory.getLogger(ObjectRepositoryReader.class);

  /**
   * The constant REPLACE_ME.
   */
  private static final String REPLACE_ME = "\\{\\{REPLACE_ME\\}\\}";
  /**
   * The constant REPLACE_ME.
   */
  private static final String OBJECT_REPOSITORIES_PATH = "ObjectRepositories/";
  /**
   * The constant JSON_EXT.
   */
  private static final String JSON_EXT = ".json";
  /**
   * The constant LOCATOR_TYPE.
   */
  private static final String LOCATOR_TYPE = "locatorType";
  /**
   * The constant LOCATOR_VALUE.
   */
  private static final String LOCATOR_VALUE = "locatorValue";
  /**
   * The constant WEBVIEW.
   */
  private static final String WEBVIEW = "webview";
  /**
   * The map to hold fetched page jsono.
   */
  private static final Map<String, IJSONO> pages = new HashMap<>();

  /**
   * The json pointer string array specifying, which page and element to fetch.
   * String array needs to be of length 2 or 3 inclusive.
   * String[0] - Page.
   * String[1] - Element.
   * String[2] - Replacement text. Optional.
   *
   * @param jsonPointer The json pointer string array.
   * @return The JSONA as required by a Cyborg.
   */
  public static IJSONA getLocatorFromJson(String... jsonPointer) {
    IJSONA locator = juf.getJSONA();

    if (jsonPointer.length >= 2 && jsonPointer.length < 4) {
      String page = checkStringForNullOrEmpty("Page value", jsonPointer[0]);

      String element = checkStringForNullOrEmpty(page + " : element value", jsonPointer[1]);

      String replaceVal = "";
      if (jsonPointer.length == 3) {
        replaceVal = checkStringForNullOrEmpty(element + " : replace text value", jsonPointer[2]);
      }

      IJSONO locPage;
      if (pages.containsKey(page)) {
        locPage = pages.get(page);
      } else {
        locPage = checkJsonoForNullOrEmpty(page + " : page config", juf.getJSONOFromInputStream(
            Objects.requireNonNull(ObjectRepositoryReader.class.getClassLoader()
                .getResourceAsStream(OBJECT_REPOSITORIES_PATH + page + JSON_EXT))));
        pages.put(page, locPage);
      }

      IJSONO locEle = checkJsonoForNullOrEmpty(element + " : element config", locPage.get(element, null));

      // Loc Strategy.
      String locType = checkStringForNullOrEmpty(element + " : locator type", locEle.get(LOCATOR_TYPE));
      locator.put(0, locType);

      // Loc locValue.
      String locValue = locEle.get(LOCATOR_VALUE);
      checkStringForNullOrEmpty(element + " : locator value", locValue);
      if (!replaceVal.isEmpty()) {
        locValue = locValue.replaceAll(REPLACE_ME, replaceVal);
      }
      locator.put(1, locValue);

      // Webview.
      if (locEle.hasKey(WEBVIEW)) {
        locator.put(2, WEBVIEW);
      }
    } else {
      throw new CyBaseRuntimeException("Json pointer needs length should be between 2 and 3 inclusive.");
    }
    return checkJsonaForNullOrEmpty("Generated cy element config", locator);
  }

  public IJSONO readJsonLocator(String elementName, String pageName) {
    String filePath = "./src/main/resources/ObjectRepositories/" + pageName + JSON_EXT;
    File inputFile = new File(filePath);
    IJSONO locator = null;
    try (FileInputStream fis = new FileInputStream(inputFile)) {
      IJSONO jsonExtract = juf.getJSONOFromInputStream(fis);
      locator = jsonExtract.get(elementName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return locator;
  }

  /**
   * Check the string for null or empty.
   *
   * @param refVal   The string reference.
   * @param strValue The string value.
   * @return The passed string.
   */
  private static String checkStringForNullOrEmpty(String refVal, String strValue) {
    if (strValue == null || strValue.isEmpty()) {
      throw new CyBaseRuntimeException(refVal + " cannot be null or empty.");
    } else {
      return strValue;
    }
  }

  /**
   * Check the jsono for null or empty.
   *
   * @param refVal     The string reference.
   * @param jsonoValue The jsono.
   * @return The passed IJSONO.
   */
  private static IJSONO checkJsonoForNullOrEmpty(String refVal, IJSONO jsonoValue) {
    if (jsonoValue == null || jsonoValue.isEmpty()) {
      throw new CyBaseRuntimeException(refVal + " cannot be null or empty.");
    } else {
      return jsonoValue;
    }
  }

  /**
   * Check the jsona for null or empty.
   *
   * @param refVal     The string reference.
   * @param jsonaValue The jsona.
   * @return The passed IJSONA.
   */
  private static IJSONA checkJsonaForNullOrEmpty(String refVal, IJSONA jsonaValue) {
    if (jsonaValue == null || jsonaValue.isEmpty()) {
      throw new CyBaseRuntimeException(refVal + " cannot be null or empty.");
    } else {
      return jsonaValue;
    }
  }
}
