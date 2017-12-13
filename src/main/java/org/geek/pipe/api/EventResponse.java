/**
 * 
 */
package org.geek.pipe.api;

import java.io.Serializable;
import java.util.Stack;

import org.geek.pipe.core.ResponseCode;

/**
 * @author haichuan
 * 2012-2-1
 */
public interface EventResponse extends Serializable{

	
	ResponseCode getResponseCode();
	void setResponseCode(ResponseCode code);
	
	Stack<Throwable> getFailException();
	void addFailException(Throwable t);
}
