/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.DeliverException;
import org.geek.pipe.api.EventRequest;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public class FilterPipeline extends SimplePipeline {

	private Conidition condition;
	
	public void setCondition(Conidition condition) {
		this.condition = condition;
	}
	
	@Override
	public void deliverMessage(EventRequest request) throws DeliverException {
		boolean pass;
		try {
			pass = condition.eval(request);
		} catch (ConditionException e) {
			
			throw new DeliverException(e);
		}
		if(!pass) return;
		super.deliverMessage(request);
	}
}
