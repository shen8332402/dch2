package com.digitalchina.frame.util;

import java.util.UUID;
/**
 * @description 生成uuid
 * @author shentongtong
 *
 */
public class UUIDUtil
{
  public static String getUUIDAsString()
  {
    return getUUID().toString();
  }

  private static UUID getUUID() {
    UUID uuId = UUID.randomUUID();
    return uuId;
  }
}  