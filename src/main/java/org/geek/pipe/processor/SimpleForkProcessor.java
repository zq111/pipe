/**
 * 
 */
package org.geek.pipe.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.MultiProcessor;
import org.geek.pipe.api.OutBound;
import org.geek.pipe.api.ProcessException;

/**
 * @author haichuan
 * @Create 2012-4-1
 */
public class SimpleForkProcessor implements MultiProcessor {

	private List<OutBound> outQueue = new ArrayList<OutBound>();
	
	@Override
	public int getOutBoundsSize() {
		return outQueue.size();
	}
	
	@Override
	public void process(EventRequest request) throws ProcessException {
		for(int i = 0; i < getOutBoundsSize(); i++){
			try {
				getOutBound(i).deliverMessage(request);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}
		}
	}

	@Override
	public OutBound getOutBound() {
		return null;
	}

	@Override
	public void setOutBound(OutBound out) {
	}

	@Override
	public OutBound getOutBound(int index) {
		return outQueue.get(index);
	}

	@Override
	public void addOutBound(OutBound out) {
		outQueue.add(out);
	}

	@Override
	public Iterator<OutBound> getOutBoundIterator() {
		return outQueue.iterator();
	}

	@Override
	public void setOutBounds(List<OutBound> outs) {
		this.outQueue = outs;		
	}

}
