/**
 * 
 */
package org.geek.pipe.core;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.OutBound;
import org.geek.pipe.api.Processor;

/**
 * @author haichuan
 *
 */
public abstract class AbstractProcessor implements Processor {

	private OutBound out;
	

	/* (non-Javadoc)
	 * @see com.taobao.baoxian.yetask.api.Processor#getOutBound()
	 */
	@Override
	public OutBound getOutBound() {
		return this.out;
	}

	@Override
	public void setOutBound(OutBound out) {
		this.out = out;
	}

	public void fail(EventRequest request){
		request.setResponse(new ResponseImp(ResponseCode.FAIL));
	}
	
	public void failRollBack(EventRequest request){
		request.setResponse(new ResponseImp(ResponseCode.FAILROLLBACK));
	}
	
	public void fail(EventRequest request, String failDetail){
		request.setResponse(new ResponseImp(ResponseCode.FAIL(failDetail)));
	}
	
	public void failRollBack(EventRequest request, String failDetail){
		request.setResponse(new ResponseImp(ResponseCode.FAILROLLBACK(failDetail)));
	}
	
	public void done(EventRequest request){
		request.setResponse(new ResponseImp(ResponseCode.SUCCESS));
	}
	
	public void doneCommit(EventRequest request){
		request.setResponse(new ResponseImp(ResponseCode.SUCCESSCOMMINT));
	}
	
	public void done(EventRequest request, String failDetail){
		request.setResponse(new ResponseImp(ResponseCode.SUCCESS(failDetail)));
	}
	
	public void doneCommit(EventRequest request, String failDetail){
		request.setResponse(new ResponseImp(ResponseCode.SUCCESSCOMMINT(failDetail)));
	}
}
