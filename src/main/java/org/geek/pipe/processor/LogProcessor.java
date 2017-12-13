/**
 * 
 */
package org.geek.pipe.processor;

import lombok.extern.slf4j.Slf4j;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;
import org.geek.pipe.core.AbstractProcessor;

/**
 * @author haichuan
 * 2012-2-2
 */
@Slf4j
public class LogProcessor extends AbstractProcessor {

	private Processor logHandler = null;

	@Override
	public void process(EventRequest request) throws ProcessException {
		if(this.logHandler != null){
			logHandler.process(request);
		}
		else{
			log.info("message");
		}
		try {
			getOutBound().deliverMessage(request);
		} catch (Exception e) {
			throw new ProcessException(e);
		}
	}

	public void setLogHandel(Processor logHandler) {
		this.logHandler = logHandler;
	}
}
