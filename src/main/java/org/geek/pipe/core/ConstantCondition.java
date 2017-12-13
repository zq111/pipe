/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.EventRequest;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public class ConstantCondition extends Conidition {

	private String condition;
	
	@Override
	public ConditionType getType() {
		return ConditionType.constant;
	}

	@Override
	public boolean eval(EventRequest request) throws ConditionException {
		return false;
	}

	public void setCondition(String condition){
		this.condition = condition;
	}
	
	public String getCondition() {
		return condition;
	}
}
