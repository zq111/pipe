/**
 * 
 */
package org.geek.pipe.core;

import java.util.Stack;

import org.geek.pipe.api.EventResponse;

/**
 * @author haichuan
 * 2012-2-2
 */
public class ResponseImp implements EventResponse {

	private static final long serialVersionUID = -310285761367274573L;
	private Stack<Throwable> exceptionList = new Stack<Throwable>();
	private ResponseCode code = null;
	
	
	public ResponseImp(ResponseCode code) {
		this.code = code;
	}
	
	
	@Override
	public Stack<Throwable> getFailException() {
		return this.exceptionList;
	}

	@Override
	public void addFailException(Throwable t) {
		this.exceptionList.push(t);
	}

	@Override
	public ResponseCode getResponseCode() {
		return this.code;
	}

	@Override
	public void setResponseCode(ResponseCode code) {
		this.code = code;
	}

}
