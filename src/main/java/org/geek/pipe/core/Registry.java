/**
 * 
 */
package org.geek.pipe.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.geek.pipe.api.patterns.Converter;


/**
 * @author haichuan
 * 2012-1-21
 */
public class Registry implements ApplicationContextAware{

	private static ApplicationContext SPRING_CONTEXT = null;
	private static Map LOCAL_CONTEXT = null;
	private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
	private static final Lock READ_LOCK = LOCK.readLock();
	private static final Lock WRITE_LOCK = LOCK.writeLock();
	
	private List<ResourceListener> initListener = null;
	
	public void init(){
		LOCAL_CONTEXT = new HashMap();
		afterInitalize();
	}
	
	public static void destory(){
		LOCAL_CONTEXT.clear();
		LOCAL_CONTEXT = null;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SPRING_CONTEXT = applicationContext;
	}
	
	public static void regist(Object key, Object value){
		try{
			WRITE_LOCK.lock();
			LOCAL_CONTEXT.put(key, value);
		}
		finally{
			WRITE_LOCK.unlock();
		}
	}
	
	public static <F, T>Converter<F, T> findConverter(Class<F> from, Class<T> to){
		
		
		return null;
	}
	
//	public static void registAndAutoWare(Object key, Object value){
//		try{
//			SPRING_CONTEXT.getAutowireCapableBeanFactory().autowire(beanClass, autowireMode, dependencyCheck);
//			WRITE_LOCK.lock();
//			LOCAL_CONTEXT.put(key, value);
//		}
//		finally{
//			WRITE_LOCK.unlock();
//		}
//	}
	
	public static <T> T get(Object key, Class<T> type){
		Object value = get(key);
		return type.cast(value);
	}
	
	public static Object get(Object key){
		Object value = find(key);
		if(value == null){
			value = findLocal(key);
		}
		return value;
	}
	
	public void setInitListener(List<ResourceListener> initListener) {
		this.initListener = initListener;
	}
	
	protected static Object find(Object key){
		if(key == null){
			return null;
		}
		return SPRING_CONTEXT.getBean(key.toString());
	}
	
	protected static Object findLocal(Object key){
		try{
			READ_LOCK.lock();
			return LOCAL_CONTEXT.get(key);
		}
		finally{
			READ_LOCK.unlock();
		}
	}
	
	private void afterInitalize() {
		if(this.initListener != null){
			for(ResourceListener lin : initListener){
				lin.doEvent(this);
			}
		}
	}
}
