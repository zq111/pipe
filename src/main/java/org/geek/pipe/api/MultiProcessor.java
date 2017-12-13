/**
 * 
 */
package org.geek.pipe.api;

import java.util.Iterator;
import java.util.List;

/**
 * @author haichuan
 *
 */
public interface MultiProcessor extends Processor {
	
	OutBound getOutBound(int index);
	void addOutBound(OutBound out);
	Iterator<OutBound> getOutBoundIterator();
	
	int getOutBoundsSize();
	void setOutBounds(List<OutBound> outs);
}
