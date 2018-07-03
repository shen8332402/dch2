package com.digitalchina.frame.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 
 * @author shentt
 * @date 2018年1月23日
 * @className CopyProperties
 * @param 
 * @Description 不同类相同属性复制工具类
 */
public class CopyProperties {
	/** 
     * 复制源对象和目标对象的属性值 
     * 
     */  
    public static void copy(Object source, Object target) {  
          
        Class sourceClass = source.getClass();//得到对象的Class  
        Class targetClass = target.getClass();//得到对象的Class  
          
        Field[] sourceFields = sourceClass.getDeclaredFields();//得到Class对象的所有属性  
        Field[] targetFields = targetClass.getDeclaredFields();//得到Class对象的所有属性  
          
        for(Field sourceField : sourceFields){  
            String name = sourceField.getName();//属性名  
            Class type = sourceField.getType();//属性类型  
              
            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);  
              
            Method getMethod = null;
			try {
				getMethod = sourceClass.getMethod("get" + methodName);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//得到属性对应get方法  
              
            Object value = null;
			try {
				value = getMethod.invoke(source);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//执行源对象的get方法得到属性值  
              
            for(Field targetField : targetFields){  
                String targetName = targetField.getName();//目标对象的属性名  
                  
                if(targetName.equals(name)){  
                    Method setMethod = null;
					try {
						setMethod = targetClass.getMethod("set" + methodName, type);
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//属性对应的set方法  
                      
                    try {
						setMethod.invoke(target, value);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//执行目标对象的set方法  
                }  
            }  
        }  
    }  
    /** 
     * 将一个 Map 对象转化为一个 JavaBean 
     * @param type 要转化的类型 
     * @param map 包含属性值的 map 
     * @return 转化出来的 JavaBean 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InstantiationException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */  
    @SuppressWarnings("rawtypes")  
    public static Object convertMap(Class type, Map map)  
             {  
        BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(type);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取类属性  
        Object obj = null;
		try {
			obj = type.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 创建 JavaBean 对象  
  
        // 给 JavaBean 对象的属性赋值  
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
        for (int i = 0; i< propertyDescriptors.length; i++) {  
            PropertyDescriptor descriptor = propertyDescriptors[i];  
            String propertyName = descriptor.getName();  
  
            if (map.containsKey(propertyName)) {  
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
                Object value = map.get(propertyName);  
  
                Object[] args = new Object[1];  
                args[0] = value;  
  
                try {
					descriptor.getWriteMethod().invoke(obj, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
            }  
        }  
        return obj;  
    }  
}  
