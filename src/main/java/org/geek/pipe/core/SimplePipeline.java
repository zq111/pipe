/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.DeliverException;
import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.InBound;
import org.geek.pipe.api.Pipline;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;

/**
 * @author haichuan
 * @Create 2012-3-2
 */
public class SimplePipeline implements Pipline {

	private Processor processor;
	
	
	@Override
	public Processor getProcessor() {
		return processor;
	}

	@Override
	public void setProcessor(Processor out) {
		this.processor = out;
	}

	@Override
	public void deliverMessage(EventRequest request) throws DeliverException {
		try {
			getProcessor().process(request);
		} catch (ProcessException e) {
			throw new DeliverException(e);
		}
	}

	@Override
	public InBound getInbound() {
		return this;
	}

	@Override
	public void setInbound(InBound in) {
	}

	@Override
	public boolean isEnd() {
		return false;
	}
}
