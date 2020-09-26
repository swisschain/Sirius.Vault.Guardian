package io.swisschain.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class AppVersion {
  private static final Logger logger = LogManager.getLogger();
  public static String REVISION_NUMBER;
  public static String BUILD_NUMBER;
  public static String VERSION;
  public static String NAME;
  public static String INSTANCE_UNIQUE_ID;
  public static String HOSTNAME;
  public static Date STARTED_AT;

  static {
    Attributes attributes = new Attributes();
    try {
      Enumeration<URL> urls =
          AppVersion.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
      while (urls.hasMoreElements()) {
        try {
          Manifest manifest = new Manifest(urls.nextElement().openStream());
          attributes.putAll(manifest.getMainAttributes());
        } catch (IOException e) {
          logger.error("Fail to read manifest file: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      logger.error("Error while reading manifest: " + e.getMessage());
      e.printStackTrace();
    }

    REVISION_NUMBER =
        attributes.getValue("Revision-number") != null
            ? attributes.getValue("Revision-number")
            : "local";
    BUILD_NUMBER =
        attributes.getValue("Build-number") != null ? attributes.getValue("Build-number") : "local";
    VERSION = attributes.getValue("Version") != null ? attributes.getValue("Version") : "local";
    NAME = attributes.getValue("App-name") != null ? attributes.getValue("App-name") : "local";

    STARTED_AT = new Date();

    try {
      HOSTNAME = InetAddress.getLocalHost().getHostName();
      INSTANCE_UNIQUE_ID =
          HOSTNAME
              + " "
              + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(STARTED_AT)
              + "."
              + System.nanoTime() % 1000000L;
    } catch (UnknownHostException e) {
      logger.error("Error getting host name: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
