package app.cn.qtt.bm.common.utils;

/**
* BeanUtils.java
* Created by yanshien 2010-08-22
* 属性拷贝工具
*/


import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;

import org.apache.commons.beanutils.PropertyUtils;


public class BeanUtils
{
    /**
     * 通过反射拷贝非空属性
     * @param target
     * @param src
     */
    public static Object copyProperty(Object target, Object src){
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(target);
        for(PropertyDescriptor pd : pds){
            try {
                Object result = PropertyUtils.getProperty(src, pd.getName());

                if(result != null){
                    PropertyUtils.setProperty(target, pd.getName(), result);
                }
            } catch (Exception e) {
                new RuntimeException("复制对象" + src.getClass().getName() + "的属性出错!",e);
            } 
        }
        return target;
    }
    
    
    /**
    * 方法名称:          
    * 方法描述:      把对象里的所有的String 类型的对象转化成utf-8
    * @param ：       
    * @return String ：
    * @Author:       Administrator
    * @Create Date: 2011-4-23 上午11:14:48
    */ 
     
    public static Object Object2UTF_8(Object target){
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(target);
        for(PropertyDescriptor pd : pds){
            try {
            	if(PropertyUtils.getPropertyType(target, pd.getName())==String.class)
            	{
            		Object result = PropertyUtils.getProperty(target, pd.getName());
            		if(result != null){
            			result=java.net.URLDecoder.decode((String)result,"UTF-8");
            			PropertyUtils.setProperty(target, pd.getName(), result);
            		}
            		
            		
            	}
                
            } catch (Exception e) {
                new RuntimeException("对象" + target.getClass().getName() + "的属性出错!",e);
            } 
        }
        return target;
    }
    
    
    
    
    /**
    * 方法名称:          
    * 方法描述:       对单个字符进行转化
    * @param ：        
    * @return String ：
    * @Author:       Administrator
    * @Create Date: 2011-4-23 上午11:18:34
    */ 
     
    public static String String2UTF_8(String target){
    	String result = null;
    	try {
			result= java.net.URLDecoder.decode((String)target,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			new RuntimeException("对象" + target + "的属性出错!",e);
		}
		return result;
    }
    
    
    /**
     * 获得属性定义
     * @param bean
     * @param name
     * @return
     */
    public static PropertyDescriptor getPropertyDescriptor(Object bean, String name)
    {
        try
        {
            return PropertyUtils.getPropertyDescriptor(bean, name);
        } catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * 获得属性定义集
     * @param bean
     * @param name
     * @return
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Object bean)
    {
        try
        {
            return PropertyUtils.getPropertyDescriptors(bean);
        } catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 获得属性
     * @param bean
     * @param name
     * @return
     */
    public static Object getProperty(Object bean, String name)
    {
        try
        {
            return PropertyUtils.getProperty(bean, name);
        } catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * 设置属性
     * @param bean
     * @param name
     * @return
     */
    public static void setProperty(Object bean, String name, Object value)
    {
        try
        {
            PropertyUtils.setProperty(bean, name, value);
        } catch (Exception e)
        {
            return;
        }
    }
}
