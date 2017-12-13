/**
 * 
 */
package org.geek.pipe.util;

import java.io.Serializable;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.patterns.Builder;
import org.geek.pipe.api.policy.Policy;
import org.geek.pipe.api.policy.PolicyType;
import org.geek.pipe.core.EventRequestImp;
import org.geek.pipe.helper.EventRequestHelper;

/**
 * @author haichuan
 * 2012-2-2
 */
public class RequestBuilder implements Builder<EventRequest> {

	private EventRequest request = null;
	
	public RequestBuilder() {
		request = EventRequestHelper.createRequest();
	}
	
	public RequestBuilder anthor(){
		request = EventRequestHelper.createRequest();
		return this;
	}
	
	public RequestBuilder source(Serializable source){
		request.setSource(source);
		return this;
	}
	
	@Override
	public EventRequest build() {
		return request;
	}
	
	public RequestBuilder policy(PolicyType type, Policy policy){
		request.installPolicy(type, policy);
		return this;
	}
}
