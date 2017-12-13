/**
 * 
 */
package org.geek.pipe.util;

import org.geek.pipe.api.EventResponse;
import org.geek.pipe.api.patterns.Builder;
import org.geek.pipe.core.ResponseCode;

/**
 * @author haichuan
 * 2012-2-2
 */
public class ResponseBuilder implements Builder<EventResponse>{

	@Override
	public EventResponse build() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static final class ResponseCodeBuilder implements Builder<ResponseCode>{
		
		@Override
		public ResponseCode build() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
