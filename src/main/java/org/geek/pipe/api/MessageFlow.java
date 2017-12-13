/**
 * 
 */
package org.geek.pipe.api;


/**
 * @author haichuan
 * 2012-1-20
 */
public interface MessageFlow {

	void deliverMessage(EventRequest request)throws DeliverException;
}
