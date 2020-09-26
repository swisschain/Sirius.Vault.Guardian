package io.swisschain.services;

import java.util.Random;

public class Utils {
  private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final java.util.Random random = new Random();

  public static String generateRandomString(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      sb.append(chars.charAt(random.nextInt(chars.length())));
    }
    return sb.toString();
  }
}
