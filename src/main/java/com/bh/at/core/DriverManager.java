package com.bh.at.core;

import static com.bh.at.client.uiutil.ClientUiUtil.getDriverConfig;
import static com.bh.at.uiutil.CyConfig.createConfig;
import static com.bh.at.uiutil.CyUIUtilFactory.uif;
import static com.bh.jsonutil.CyJsonUtilFactory.juf;
import java.time.Duration;
import com.bh.at.uiutil.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bh.at.iuiutil.IElement;
import com.bh.at.utils.ObjectRepositoryReader;
import com.bh.commonutil.CyBaseRuntimeException;
import com.bh.icommonallutil.IJSONA;
import com.bh.icommonallutil.IJSONO;

/**
 * The class to implement CyBrowserPage.
 */
public class DriverManager extends CyBrowserPage {

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

  /**
   * The constant DEFAULT_OPTIONAL_TIMEOUT_IN_SEC.
   */
  public static final int DEFAULT_OPTIONAL_TIMEOUT_IN_SEC = 60;

  //  @Deprecated(since = "Do not expose driver.", forRemoval = true)
  public static WebDriver driver;

  /**
   * The enum for supported HTML tags.
   */
  public enum ElementType {
    DIV,
    SELECT,
    INPUT,
    FRAME,
    BUTTON,
    ANCHOR,
    CHECKBOX,
    ICON,
    TEXTAREA,
    IMG,
    CANVAS,
    TEXT
  }

  /**
   * Setup default driver and PO.
   */
  public DriverManager() {
    super(getDriverConfig("default"));
    setupDefaultWait();
  }

  /**
   * Setup default waits.
   */
  public void setupDefaultWait() {
    driver = uiDriver;
    uiDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    uiDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
  }

  /**
   * Get element.
   *
   * @param locatorConfig The locator config string array.
   * @return The IElement.
   */
  public IElement getElement(String... locatorConfig) {
    return getElement(ElementType.DIV, DEFAULT_OPTIONAL_TIMEOUT_IN_SEC, locatorConfig);
  }

  /**
   * Get element.
   *
   * @param elementType   The element type.
   * @param locatorConfig The locator config string array.
   * @return The IElement.
   */
  public IElement getElement(ElementType elementType, String... locatorConfig) {
    return getElement(elementType, DEFAULT_OPTIONAL_TIMEOUT_IN_SEC, locatorConfig);
  }

  /**
   * Get elements.
   *
   * @param elementType   The element type.
   * @param locatorConfig The locator config string array.
   * @return The CyElement<IElement> list of WebElements.
   */
  public CyElements<IElement> getElements(ElementType elementType, String... locatorConfig) {
    return getElements(elementType, DEFAULT_OPTIONAL_TIMEOUT_IN_SEC, locatorConfig);
  }

  /**
   * Get element.
   *
   * @param optionalTimeoutInSec The optional timeout in sec.
   * @param locatorConfig        The locator config string array.
   * @return The IElement.
   */
  public IElement getElement(int optionalTimeoutInSec, String... locatorConfig) {
    return getElement(ElementType.DIV, optionalTimeoutInSec, locatorConfig);
  }

  /**
   * Get element.
   *
   * @param elementType          The element type.
   * @param optionalTimeoutInSec The optional timeout in sec.
   * @param locatorConfig        The locator config string array.
   * @return The IElement.
   */
  public IElement getElement(ElementType elementType, int optionalTimeoutInSec, String... locatorConfig) {
    IElement element = getElement(elementType, optionalTimeoutInSec, ObjectRepositoryReader.getLocatorFromJson(locatorConfig));
    if (element.elementExists()) {
      return (IElement) element.collectData();
    } else {
      throw new CyBaseRuntimeException("Element: [" + locatorConfig[1] + "] from Page: [" + locatorConfig[0] + "] not found.");
    }
  }

  /**
   * Get elements.
   *
   * @param elementType          The element type.
   * @param optionalTimeoutInSec The optional timeout in sec.
   * @param locatorConfig        The locator config string array.
   * @return The CyElement<IElement> list of WebElements.
   */
  public CyElements<IElement> getElements(ElementType elementType, int optionalTimeoutInSec, String... locatorConfig) {
    CyElements<IElement> elements = getElements(elementType, optionalTimeoutInSec, ObjectRepositoryReader.getLocatorFromJson(locatorConfig));
    return elements.collectData();
  }

  /**
   * Get element.
   *
   * @param elementType          The element type.
   * @param optionalTimeoutInSec The optional timeout in sec.
   * @param locatorConfig        The locator config string array.
   * @return The IElement.
   */
  private IElement getElement(ElementType elementType, int optionalTimeoutInSec, IJSONA locatorConfig) {
    LOG.info("Creating element type : " + elementType);
    IJSONO elementConfig = juf.getJSONO();
    elementConfig.put("loc", locatorConfig);
    IElement element = switch (elementType) {
      case ANCHOR -> uif.getAnchor(createConfig(elementConfig, this));
      case BUTTON -> uif.getButton(createConfig(elementConfig, this));
      case CANVAS -> uif.getCanvas(createConfig(elementConfig, this));
      case CHECKBOX -> uif.getCheckbox(createConfig(elementConfig, this));
      case DIV -> uif.getDiv(createConfig(elementConfig, this));
      case FRAME -> uif.getFrame(createConfig(elementConfig, this));
      case ICON -> uif.getIcon(createConfig(elementConfig, this));
      case IMG -> uif.getImg(createConfig(elementConfig, this));
      case INPUT -> uif.getInput(createConfig(elementConfig, this));
      case SELECT -> uif.getSelect(createConfig(elementConfig, this));
      case TEXTAREA -> uif.getTextArea(createConfig(elementConfig, this));
      case TEXT -> uif.getText(createConfig(elementConfig, this));
    };
    return (IElement) element.init(optionalTimeoutInSec);
  }

  /**
   * Get elements.
   *
   * @param elementType          The element type.
   * @param optionalTimeoutInSec The optional timeout in sec.
   * @param locatorConfig        The locator config string array.
   * @return The CyElement<IElement> list of WebElements.
   */
  private CyElements<IElement> getElements(ElementType elementType, int optionalTimeoutInSec, IJSONA locatorConfig) {
    LOG.info("Creating element type : " + elementType);
    IJSONO elementConfig = juf.getJSONO();
    elementConfig.put("loc", locatorConfig);
    CyElements<IElement> elements = switch (elementType) {
      case DIV, SELECT -> new CyElements<>(CyDiv::new, createConfig(elementConfig, this));
      case INPUT ->  new CyElements<>(CyInput::new, createConfig(elementConfig, this));
      case FRAME -> new CyElements<>(CyFrame::new, createConfig(elementConfig, this));
      case BUTTON -> new CyElements<>(CyButton::new, createConfig(elementConfig, this));
      case ANCHOR -> new CyElements<>(CyAnchor::new, createConfig(elementConfig, this));
      case CHECKBOX -> new CyElements<>(CyCheckbox::new, createConfig(elementConfig, this));
      case ICON -> new CyElements<>(CyIcon::new, createConfig(elementConfig, this));
      case TEXTAREA -> new CyElements<>(CyTextArea::new, createConfig(elementConfig, this));
      case IMG -> new CyElements<>(CyImg::new, createConfig(elementConfig, this));
      case CANVAS -> new CyElements<>(CyCanvas::new, createConfig(elementConfig, this));
      case TEXT -> new CyElements<>(CyText::new, createConfig(elementConfig, this));
    };
    return elements.init(optionalTimeoutInSec);
  }
}
