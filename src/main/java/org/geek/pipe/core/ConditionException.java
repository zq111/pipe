/**
 * 
 */
package org.geek.pipe.core;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public class ConditionException extends Exception {

	public ConditionException(Throwable t) {
		super(t);
	}
	
	public ConditionException(String reason, Throwable t){
		super(reason, t);
	}
}
