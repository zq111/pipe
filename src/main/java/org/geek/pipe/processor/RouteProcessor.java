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
import org.geek.pipe.core.ResponseCode;
import org.geek.pipe.core.ResponseImp;

/**
 * @author haichuan
 *
 */
public class RouteProcessor implements MultiProcessor {

	private List<String> responseCodeValues = new ArrayList<String>();
	private List<OutBound> outs = new ArrayList<OutBound>();
	
	@Override
	public void process(EventRequest request) throws ProcessException {
		try {
			for(int i = 0; i < responseCodeValues.size(); i++){
				if(Integer.parseInt(responseCodeValues.get(i)) == request.getResponse().getResponseCode().getCode()){
					getOutBound(i).deliverMessage(request);
				}
			}
		} catch (Exception e) {
			request.setResponse(new ResponseImp(ResponseCode.FAILROLLBACK));
			throw new ProcessException(e);
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
		return outs.get(index);
	}

	@Override
	public void addOutBound(OutBound out) {
		outs.add(out);
	}

	@Override
	public Iterator<OutBound> getOutBoundIterator() {
		return outs.iterator();
	}

	@Override
	public int getOutBoundsSize() {
		return outs.size();
	}

	public void setResponseCodeValues(List<String> values){
		this.responseCodeValues = values;
	}

	@Override
	public void setOutBounds(List<OutBound> outs) {
		this.outs = outs;
	}
}
