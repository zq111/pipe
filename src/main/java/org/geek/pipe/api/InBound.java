/**
 * 
 */
package org.geek.pipe.api;


/**
 * @author haichuan
 *����������
 */
public interface InBound extends MessageFlow{

	Processor getProcessor();
	void setProcessor(Processor out);
	
}
