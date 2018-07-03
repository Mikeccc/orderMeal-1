package app.cn.qtt.bm.common.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import app.cn.qtt.bm.common.annotation.Description;




public class LogAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
	
	private static final Log log = LogFactory.getLog(LogAdvice.class);

	public void before(Method method, Object[] args, Object target) throws Throwable {
		if (log.isInfoEnabled()) {
			String targetDescription = describeTarget(target);
			String methodDescription = describeMethod(method);
			
			StringBuffer info = new StringBuffer();
			info.append("开始调用[");
			info.append(targetDescription);
			info.append("]的[");
			info.append(methodDescription);
			info.append("]方法,");
			if (args == null || args.length == 0) {
				info.append("无传入参数");
			}
			else {
				String argumentsDescription = describeArguments(args);
				info.append("传入参数为:");
				info.append(argumentsDescription);
			}
			
			log.info(info);
		}
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		if (log.isInfoEnabled()) {
			String targetDescription = describeTarget(target);
			String methodDescription = describeMethod(method);
			
			StringBuffer info = new StringBuffer();
			info.append("完成调用[");
			info.append(targetDescription);
			info.append("]的[");
			info.append(methodDescription);
			info.append("]方法,");
			if (method.getReturnType() == Void.class) {
				info.append("无返回值");
			}
			else {
				String returnDescription = describeReturn(returnValue);
				info.append("返回值为:");
				info.append(returnDescription);
			}
			
			log.info(info);
		}
	}
	
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		if (log.isInfoEnabled()) {
			String targetDescription = describeTarget(target);
			String methodDescription = describeMethod(method);
			String exceptionDescription = describeException(ex);
			
			StringBuffer info = new StringBuffer();
			info.append("调用[");
			info.append(targetDescription);
			info.append("]的[");
			info.append(methodDescription);
			info.append("]方法出现异常,异常信息为:");
			info.append(exceptionDescription);
			
			log.info(info);
			log.error(ex, ex);
		}
	}
	
	private String describeTarget(Object target) {
		Description description = target.getClass().getAnnotation(Description.class);
		if (description != null) {
			String desc = description.value();
			if (desc != null && desc.trim().length() > 0) {
				return desc;
			}
		}
		
		return target.getClass().getSimpleName();
	}
	
	private String describeMethod(Method method) {
		Description description = method.getAnnotation(Description.class);
		if (description != null) {
			String desc = description.value();
			if (desc != null && desc.trim().length() > 0) {
				return desc;
			}
		}
		
		return method.getName();
	}
	
	private String describeArguments(Object[] args) {
		StringBuffer info = new StringBuffer();
		info.append("[");
		for (int i = 0; i < args.length; i++) {
			info.append(args[i]);
			if (i != args.length - 1) {
				info.append(", ");
			}
		}
		info.append("]");
		
		return info.toString();
	}
	
	private String describeReturn(Object returnValue) {
		return String.valueOf(returnValue);
	}
	
	private String describeException(Exception ex) {
		return ex.getClass().getSimpleName();
	}
}
