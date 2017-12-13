/**
 * 
 */
package org.geek.pipe.compoment.diedline;

import lombok.extern.slf4j.Slf4j;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.Processor;
import org.geek.pipe.core.DelegateProcessor;

/**
 * @author haichuan
 * @Create 2012-3-30
 */
@Slf4j
public class DiedlineProcessor extends DelegateProcessor {

	private DiedlineInbound diedline = DiedlineInbound.NONE;
	
	public DiedlineProcessor(Processor processor) {
		super(processor);
	}
	
	public DiedlineProcessor() {
	}
	
	@Override
	protected void doFail(EventRequest request) {
		try {
			diedline.deliverMessage(request);
		}
		catch (Exception e) {
			request.addFailReason(e);
			log.error("����Ϣ�ܵ������쳣 : ", e);
		}
	}

	@Override
	protected void doFinally(EventRequest request) {

	}

	@Override
	protected void doBefore(EventRequest request) {

	}

	@Override
	protected void doAfter(EventRequest request) {

	}

}
