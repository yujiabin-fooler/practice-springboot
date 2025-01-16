package com.jiabin.api.secret.signature.practice.utils;

public class Hex {

  private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

  public static byte[] decode(CharSequence s) {
    int nChars = s.length();
    if (nChars % 2 != 0) {
      throw new IllegalArgumentException("16进制数据错误");
    }
    byte[] result = new byte[nChars / 2];
    for (int i = 0; i < nChars; i += 2) {
      int msb = Character.digit(s.charAt(i), 16);
      int lsb = Character.digit(s.charAt(i + 1), 16);
      if (msb < 0 || lsb < 0) {
        throw new IllegalArgumentException(
            "Detected a Non-hex character at " + (i + 1) + " or " + (i + 2) + " position");
      }
      result[i / 2] = (byte) ((msb << 4) | lsb);
    }
    return result;
  }

  public static String encode(byte[] buf) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0, leng = buf.length; i < leng; i++) {
      sb.append(HEX[(buf[i] & 0xF0) >>> 4]).append(HEX[buf[i] & 0x0F]);
    }
    return sb.toString();
  }
}