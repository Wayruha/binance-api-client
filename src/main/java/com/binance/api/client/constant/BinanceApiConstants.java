package com.binance.api.client.constant;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Constants used throughout Binance's API.
 */
public class BinanceApiConstants {

  /**
   * HTTP Header to be used for API-KEY authentication.
   */
  public static final String API_KEY_HEADER = "X-MBX-APIKEY";

  /**
   * Decorator to indicate that an endpoint requires an API key.
   */
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY";
  public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";

  /**
   * Decorator to indicate that an endpoint requires a signature.
   */
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
  public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

  /**
   * Default receiving window.
   */
  public static final long DEFAULT_RECEIVING_WINDOW = 60_000L;

  /**
   * Default margin receiving window.
   */
  public static final long DEFAULT_MARGIN_RECEIVING_WINDOW = 5_000L;

  /**
   * Default ToStringStyle used by toString methods.
   * Override this to change the output format of the overridden toString methods.
   *  - Example ToStringStyle.JSON_STYLE
   *  ToStringStyle.SHORT_PREFIX_STYLE;
   */
  public static ToStringStyle TO_STRING_BUILDER_STYLE = new SkipEmptyToStringStyle();

  public static final int STATUS_OK = 200;
  public static final int UNKNOWN_ERROR = 500;

  public static final int API_KEY_ERROR_CODE = -2015;

  private static final class SkipEmptyToStringStyle extends ToStringStyle {
    private static final long serialVersionUID = 2L;

    SkipEmptyToStringStyle() {
      this.setUseShortClassName(true);
      this.setUseIdentityHashCode(false);
      this.setNullText("");
    }

    private Object readResolve() {
      return this;
    }
  }
}
