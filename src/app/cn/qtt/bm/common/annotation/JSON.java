/*************************************************************
 * Title: JSON.java
 * Description: 
 * Author: 黄绍斌
 * Email: huangshaobin@qtt.cn
 * CreateTime: Oct 19, 2011 5:36:06 PM
 * Copyright © 北京全天通信息咨询服务有限公司 All right reserved
 ************************************************************/
package app.cn.qtt.bm.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSON {
    String name() default "";

    boolean serialize() default true;

    boolean deserialize() default true;

    String format() default "";
}
