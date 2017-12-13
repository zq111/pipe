/**
 * 
 */
package org.geek.pipe.processor;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.ProcessException;
import org.geek.pipe.api.Processor;
import org.geek.pipe.api.ResourceException;
import org.geek.pipe.api.optional.NeedRelease;
import org.geek.pipe.core.AbstractProcessor;
import org.geek.pipe.transaction.TransactionExecutor;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public class SEDAProcessor extends AbstractProcessor implements NeedRelease{

	private ThreadPoolExecutor executor = null;
	private Processor transactionFailHandle;
	private int corePoolSize = 10;
	private int maxPoolSize = 25;
	private long aliveTime = Long.MAX_VALUE;
	private AtomicBoolean inited = new AtomicBoolean(false);
	private SEDAFutuerChecker checker = new SEDAFutuerChecker();
	
	public SEDAProcessor() {
		
	}
	
	@Override
	public void release() throws ResourceException {
		if(this.executor != null){
			executor.shutdown();
		}
	}
	
	@Override
	public void process(EventRequest request) throws ProcessException {
		init();
		try {
			Future<EventRequest> future = executor.submit(new SEDACallable(request));
			checker.check(future);
		} 
		catch (Exception e) {
			throw new ProcessException(e);
		} 
	}
	
	public void setTransactionFailHandle(Processor transactionFailHandle) {
		this.transactionFailHandle = transactionFailHandle;
	}
	
	public Processor getTransactionFailHandle() {
		return transactionFailHandle;
	}
	
	private void init(){
		if(inited.compareAndSet(false, true)){
			executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1000), new SEDAThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
			checker.start();
		}
	}
	
	class SEDACallable implements Callable<EventRequest>{
		
		private EventRequest request;
		
		public SEDACallable(EventRequest request) {
			this.request = request;
		}
		
		@Override
		public EventRequest call() throws Exception {
			final EventRequest requestF = request;
			try {
				SEDAProcessor.this.getOutBound().deliverMessage(requestF);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return requestF;
		}
	}
	
	class SEDAThreadFactory implements ThreadFactory{
		
		@Override
		public Thread newThread(Runnable r) {
			Thread newThread = new Thread(r);
			newThread.setName("SEDA Thread - " + UUID.randomUUID());
			return newThread;
		}
	}
	
	class SEDAFutuerChecker extends Thread{
		private Lock lock = new ReentrantLock();
		private LinkedList<Future<EventRequest>> futurePool = new LinkedList<Future<EventRequest>>();
		
		public SEDAFutuerChecker() {
			setDaemon(true);
		}
		
		public void check(Future<EventRequest> future){
			try{
				lock.lock();
				futurePool.add(future);
			}
			finally{
				lock.unlock();
			}
			
		}
		
		@Override
		public void run() {
			while(true){
				Future<EventRequest> futuer = null;
				try{
					lock.lock();
					for(int i = futurePool.size(); i < 0; i--){
						futuer = futurePool.get(i);
						if(futuer.isDone()){
							futurePool.remove(futuer);
							TransactionExecutor.doTransaction(futuer.get());
						}
					}
				}
				catch(Exception e){
					if(SEDAProcessor.this.getTransactionFailHandle() != null){
						try {
							SEDAProcessor.this.getTransactionFailHandle().process(futuer.get());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else{
						futurePool.push(futuer);
					}
				}
				finally{
					lock.unlock();
				}
				try{
					wait(500);
				}
				catch(Exception e){
					
				}
			}
		}
	}
}
