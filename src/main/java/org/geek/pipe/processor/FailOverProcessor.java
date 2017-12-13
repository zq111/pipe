/**
 * 
 */
package org.geek.pipe.processor;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;
import org.geek.pipe.core.DelegateProcessor;

/**
 * @author haichuan
 * @Create 2012-4-6
 */
public class FailOverProcessor extends DelegateProcessor {

	private long timeMs = 100;
	private int tryTimes = 0;
	private int realdoTimes = 0;
	
	
	public FailOverProcessor(Processor processor) {
		super(processor);
	}

	public FailOverProcessor() {
	}
	
	@Override
	protected void doFail(EventRequest request)  throws ProcessException{
		if(this.realdoTimes < tryTimes){
			try {
				Thread.sleep(timeMs);
			} catch (InterruptedException e) {
				throw new ProcessException(e);
			}
			process(request);
		}
	}

	
	@Override
	protected void doFinally(EventRequest request) {
		
	}

	
	@Override
	protected void doBefore(EventRequest request) {
		realdoTimes++;
	}

	
	@Override
	protected void doAfter(EventRequest request) {
		realdoTimes = 0;
	}
	
	public void setRealdoTimes(int realdoTimes) {
		this.realdoTimes = realdoTimes;
	}
	
	public void setTimeMs(long timeMs) {
		this.timeMs = timeMs;
	}

	public void setTryTimes(int tryTimes) {
		this.tryTimes = tryTimes;
	}
}
