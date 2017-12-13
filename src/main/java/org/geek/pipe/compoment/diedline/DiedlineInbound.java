/**
 * 
 */
package org.geek.pipe.compoment.diedline;

import org.geek.pipe.api.DeliverException;
import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.core.AbstractInbound;

/**
 * @author haichuan
 * @Create 2012-3-30
 */
public class DiedlineInbound extends AbstractInbound {

	public static final DiedlineInbound NONE = new DiedlineInbound() {
		public void deliverMessage(EventRequest request) throws DeliverException {
		}
	};
	
	@Override
	public void deliverMessage(EventRequest request) throws DeliverException {
		try {
			getProcessor().process(request);
		} catch (ProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DeliverException(e);
		}
	}

	
}
