package com.digitalchina.frame.util;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.validation.DataBinder;

/**
 * MAP 类型与bean 的绑定
 * @author lvhuic
 *
 */
public class BeanDataBinder  extends DataBinder{
    public BeanDataBinder(Object target, String objectName)
    {
        super(target, objectName);
        registerCustomEditor(Timestamp.class, new TimestampPropertyEditor());
        registerCustomEditor(Date.class, new TimestampPropertyEditor(1));
    }
}
