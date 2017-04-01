package ml.that.pigeon.msg;

import android.util.Log;

import java.io.UnsupportedEncodingException;

import ml.that.pigeon.util.LogUtils;

/**
 * Initiating authentication by send a authentication code.
 *
 * @author That Mr.L (thatmr.l@gmail.com)
 */
public class AuthenticateRequest extends Message {

  private static final String TAG = LogUtils.makeTag(AuthenticateRequest.class);

  public static final short ID = 0x0102;

  private final String mAuthCode;

  private AuthenticateRequest(Builder builder) {
    super(ID, builder.cipher, builder.phone, builder.body);

    mAuthCode = builder.authCode;
  }

  @Override
  public String toString() {
    return new StringBuilder("{ id=0102")
        .append(", auth=").append(mAuthCode)
        .append(" }").toString();
  }

  public static class Builder extends MessageBuilder {

    // Required parameters
    private final String authCode;

    public Builder(String auth) {
      if (auth == null) {
        throw new NullPointerException("Authentication code is null.");
      }

      this.authCode = auth;
    }

    @Override
    public AuthenticateRequest build() {
      try {
        this.body = this.authCode.getBytes("ascii");
      } catch (UnsupportedEncodingException uee) {
        Log.e(TAG, "build: Encode message body failed.", uee);
      }

      return new AuthenticateRequest(this);
    }

  }

}
