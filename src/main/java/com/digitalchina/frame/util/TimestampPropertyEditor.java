package com.digitalchina.frame.util;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;

public class TimestampPropertyEditor  extends PropertyEditorSupport {

    public TimestampPropertyEditor()
    {
        dateFormat = 4;
    }

    public TimestampPropertyEditor(int dateFormat)
    {
        this.dateFormat = 4;
        this.dateFormat = dateFormat;
    }

    public void setValue(Object value)
    {
        if(value != null && (value instanceof Long))
            value = new Timestamp(((Long)value).longValue());
        super.setValue(value);
    }

    public void setAsText(String text)
        throws IllegalArgumentException
    {
        if(text != null && text.length() > 0)
        {
            switch(dateFormat)
            {
            case 1: // '\001'
                text = text.concat(" 0:0:0");
                break;

            case 2: // '\002'
                text = text.concat(":0:0");
                break;

            case 3: // '\003'
                text = text.concat(":0");
                break;
            }
            super.setValue(Timestamp.valueOf(text));
        }
    }

    public void setDateFormat(int dateFormat)
    {
        this.dateFormat = dateFormat;
    }

    public static final int DATE_FORMATMICROSECOND = 5;
    public static final int DATE_FORMAT = 1;
    public static final int DATE_FORMATHOUR = 2;
    public static final int DATE_FORMATMINUTE = 3;
    public static final int DATE_FORMATSECOND = 4;
    private int dateFormat;
   
}
