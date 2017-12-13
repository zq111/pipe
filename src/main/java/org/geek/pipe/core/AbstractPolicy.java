/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.policy.Policy;

/**
 * @author haichuan
 * 2012-2-2
 */
public abstract class AbstractPolicy<C> implements Policy<C> {
	
	protected C condition;
	
	public void setCondition(C condition) {
		this.condition = condition;
	}
	

}
