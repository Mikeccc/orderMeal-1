
 /*
 * 文 件 名:  DateJsonValueProcessor.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  linch
 * 修改时间:  2012-7-30
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
 
package app.cn.qtt.bm.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


 /**       
 * 项目名称：CmWebProject2    
 * 类名称：DateJsonValueProcessor    
 * 类描述：    
 * 创建人：linch  
 * 创建时间：2012-7-30 上午9:39:49    
 * 修改人：linch   
 * 修改时间：2012-7-30 上午9:39:49    
 * 修改备注：    
 * @version       
 */

public class DateJsonValueProcessor implements JsonValueProcessor{
	
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";   
    private DateFormat dateFormat;   
    public DateJsonValueProcessor() {   
    this(DEFAULT_DATE_PATTERN);   
    }   
    public DateJsonValueProcessor(String datePattern) {   
        try {  
            dateFormat = new SimpleDateFormat(datePattern);  
            } catch (Exception ex) {  
                dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);  
                }  
                }  
      
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
        return process(value);  
        }  
      
    public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {   
            return process(value);  
    }  
      
    private Object process(Object value) {  
        if(value instanceof Timestamp)  
            return dateFormat.format((Timestamp) value);  
        else if(value instanceof Date)  
            return dateFormat.format((Date) value);  
        else if(value==null)  
            return "";  
        else  
            return value.toString();  
    }  
}

