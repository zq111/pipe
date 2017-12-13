/**
 * 
 */
package org.geek.pipe.core;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;

/**
 * @author haichuan
 *
 */
@Slf4j
public abstract class DelegateProcessor extends AbstractProcessor {

	@Setter
	private Processor processor;
	
	public DelegateProcessor(Processor processor) {
		this.processor = processor;
	}
	
	public DelegateProcessor() {
	}
	
	@Override
	public void process(EventRequest request) throws ProcessException {
		try{
			doBefore(request);
			processor.process(request);
			doAfter(request);
		}
		catch(ProcessException e){
			log.error("", e);
			request.addFailReason(e);
			doFail(request);
			
			throw e;
		}
		finally{
			doFinally(request);
		}
	}

	protected abstract void doFail(EventRequest request) throws ProcessException;
	
	protected abstract void doFinally(EventRequest request) throws ProcessException;
	
	protected abstract void doBefore(EventRequest request) throws ProcessException;
	
	protected abstract void doAfter(EventRequest request) throws ProcessException;
}
