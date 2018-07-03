package app.cn.qtt.bm.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCrppLog4j {
	private Logger gLogger;
	
	public CCrppLog4j( String xClassName ){ 
		this.gLogger = LoggerFactory.getLogger( xClassName );
	}

	// begin end pair without timespan
	public void begin( String xFunctionName ){
		this.gLogger.info( "{} begin", xFunctionName );
	}
	
	public void end( String xFunctionName ){
		this.gLogger.info( "{} end", xFunctionName );
	}
	
	// begin end pair with timespan
	public long begin( String xFunctionName, boolean xWithStartTime ){
		this.gLogger.info( "{} begin", xFunctionName );
		return System.nanoTime();
	}
	
	public void end( String xFunctionName, long xFunctionStartTime ){
		this.gLogger.info( "{} end with timespan {} ms", xFunctionName, System.nanoTime() - xFunctionStartTime );
	}
	
	// exception/info/error/debug
	public void exception( String xFunctionName ){
		this.gLogger.error( "{} exception", xFunctionName );
	}
	
	public void exception( String xFunctionName, Exception e ){
		//this.gLogger.error( "{} exception - {}", xFunctionName, e );
		this.exception( xFunctionName, e, "_no_user_id" );
	}
	
	public void exception( String xFunctionName, Exception e, String xUserId ){
		Object[] obj =  new Object[3];
		obj[0] = xFunctionName;
		obj[1] = e;
		obj[2] = e.getStackTrace();
		this.gLogger.error( "{} exception - {}\n---message:{} ", obj );
		// todo: insert one record in the monitoring table
		// insert( id, opdate, e.getClass.getMethod, e.getMessage, e.printStack, xUserId, xFunctionName );
	}
	
	public void info( String xMessage ){
		this.gLogger.info( xMessage );
	}

	public void error( String xMessage ){
		this.gLogger.error( xMessage );
	}
	
	public void debug( String xMessage ){
		this.gLogger.debug( xMessage );
	}

	// logger
	public Logger logger(){
		return this.gLogger;
	}
	
}
