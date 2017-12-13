/**
 * 
 */
package org.geek.pipe.api;



/**
 * @author haichuan
 */
public interface Processor {

	OutBound getOutBound();
	void setOutBound(OutBound out);
	
	void process(EventRequest request) throws ProcessException;
}
