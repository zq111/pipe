/**
 * 
 */
package org.geek.pipe.processor;

import java.util.Stack;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;
import org.geek.pipe.core.DelegateProcessor;
import org.geek.pipe.core.ResponseCode;

/**
 * @author haichuan
 *
 */
public class TranCommitProcessor extends DelegateProcessor {
	
	public TranCommitProcessor(Processor processor) {
		super(processor);
	}

	public TranCommitProcessor() {
	}
	
	@Override
	protected void doFail(EventRequest request) throws ProcessException {
		Stack<Throwable> fails = request.getResponse().getFailException();
		failRollBack(request, fails.pop().toString());
	}

	@Override
	protected void doFinally(EventRequest request) throws ProcessException {

	}

	@Override
	protected void doBefore(EventRequest request) throws ProcessException {

	}

	@Override
	protected void doAfter(EventRequest request) throws ProcessException {
		if(request.getResponse() == null || request.getResponse().getResponseCode() != ResponseCode.FAILROLLBACK){
			doneCommit(request, "");
		}
	}

}
