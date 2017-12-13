/**
 * 
 */
package org.geek.pipe.processor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.MultiProcessor;
import org.geek.pipe.api.OutBound;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.policy.PolicyType;

/**
 * @author haichuan
 * copy and fork
 * 2012-2-29
 */
public class DuplicateProcessor implements MultiProcessor {

	private List<OutBound> outQueue = new ArrayList<OutBound>();
	
	
	@Override
	public int getOutBoundsSize() {
		return outQueue.size();
	}
	
	@Override
	public void process(EventRequest request) throws ProcessException {
		EventRequest[] newReqs = dupRequest(request);
		for(int i = 0; i < getOutBoundsSize(); i++){
			try {
				getOutBound(i).deliverMessage(newReqs[i]);
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

	private Serializable dupSource(Serializable source){
		Serializable target = (Serializable)SerializationUtils.clone(source);
		return target;
	}
	
	private EventRequest[] dupRequest(EventRequest orign){
		EventRequest[] reqs = new EventRequest[getOutBoundsSize()];
		if(!(orign.getSource() instanceof Serializable)){
			for(int i = 0; i < reqs.length; i++){
				reqs[i] = orign;
			}
		}
		else{
			for(int i = 0; i < reqs.length; i++){
				copyRequest(orign, reqs[i]);
			}
		}
		
		return reqs;
	}
	
	private void copyRequest(EventRequest source, EventRequest target){
		target.setSource(dupSource((Serializable)source.getSource()));
		target.setRequestType(source.getRequestType());
		target.setResponse(source.getResponse());
		for(PolicyType type : source.policies()){
			target.installPolicy(type, source.findPolicy(type));
		}
	}

	@Override
	public void setOutBounds(List<OutBound> outs) {
		this.outQueue = outs;
	}
}
