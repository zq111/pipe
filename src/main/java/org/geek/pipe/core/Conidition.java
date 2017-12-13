/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.EventRequest;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public abstract class Conidition {
	
	public abstract ConditionType getType();
	
	/**
	 * pass or not
	 * */
	public abstract boolean eval(EventRequest request) throws ConditionException;
		
	
}
