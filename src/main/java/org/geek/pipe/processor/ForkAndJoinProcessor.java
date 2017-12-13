/**
 * 
 */
package org.geek.pipe.processor;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;
import org.geek.pipe.core.AbstractProcessor;

/**
 * @author haichuan
 * @Create 2012-4-1
 */
public class ForkAndJoinProcessor extends AbstractProcessor {

	private List<Processor> processorChain;
	
	public ForkAndJoinProcessor(List<Processor> processorChain) {
		this.processorChain = processorChain;
	}
	
	@Override
	public void process(EventRequest request) throws ProcessException {
		//do process processor chain
		final EventRequest frequest = request;
		CyclicBarrier barrier = new CyclicBarrier(processorChain.size(), new Runnable() {
			public void run() {
				try {
					ForkAndJoinProcessor.this.getOutBound().deliverMessage(frequest);
				} catch (Exception e) {
					
				}
			}
		});
		for(Processor current : processorChain){
			Thread thread = new Thread(new Worker(barrier, current, frequest));
			thread.start();
		}
		
	}
	
	
	class Worker implements Runnable{

		private CyclicBarrier barrier;
		private Processor processor;
		private EventRequest request;
		
		public Worker(CyclicBarrier barrier, Processor processor, EventRequest request) {
			this.barrier = barrier;
			this.processor = processor;
			this.request = request;
		}
		
		public void run() {
			try {
				processor.process(request);
				barrier.await();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
