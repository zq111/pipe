/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.InBound;
import org.geek.pipe.api.Processor;

/**
 * @author haichuan
 * 2012-1-21
 */
public abstract class AbstractInbound implements InBound {
	
	private Processor processor;
	
	@Override
	public Processor getProcessor() {
		return this.processor;
	}

	@Override
	public void setProcessor(Processor out) {
		this.processor = out;
	}

}
