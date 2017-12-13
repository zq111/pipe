/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.DeliverException;
import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.InBound;
import org.geek.pipe.api.OutBound;

/**
 * @author haichuan
 * 2012-2-2
 */
public abstract class AbstractOutbound implements OutBound {

	private InBound in;

	@Override
	public void deliverMessage(EventRequest request) throws DeliverException {
		
	}

	@Override
	public InBound getInbound() {
		return in;
	}

	@Override
	public void setInbound(InBound in) {
		this.in = in;
	}

	@Override
	public boolean isEnd() {
		return getInbound() == null;
	}
}
