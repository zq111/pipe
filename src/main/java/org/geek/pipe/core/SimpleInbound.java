/**
 * 
 */
package org.geek.pipe.core;

import lombok.Getter;
import lombok.Setter;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;

/**
 * @author haichuan
 *
 */
public class SimpleInbound extends AbstractInbound {

	@Getter
	@Setter
	private String id;
	
	@Override
	public void deliverMessage(EventRequest request) {
		try {
			getProcessor().process(request);
		} 
		catch (ProcessException e) {
			
		}
	}

}
