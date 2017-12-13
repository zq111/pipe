/**
 * 
 */
package org.geek.pipe.helper;

import org.geek.pipe.api.EventRequest;

/**
 * @author haichuan
 * @Create 2012-6-7
 */
public class EventRequestHelper {

	public static Class<? extends EventRequest> ec;
	
	public static final EventRequest createRequest(){
		try {
			return ec.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalAccessError("EventRequest not specified");
		}
	}
	
	public static final void init(Class<? extends EventRequest> ect){
		ec = ect;
	}
}
