/**
 * 
 */
package org.geek.pipe.core;

import java.util.HashMap;
import java.util.Map;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.EventResponse;
import org.geek.pipe.api.RequestType;
import org.geek.pipe.api.policy.Policy;
import org.geek.pipe.api.policy.PolicyType;

/**
 * @author haichuan
 * 2012-2-2
 */
public class EventRequestImp implements EventRequest {

	
	private static final long serialVersionUID = 1416535340820761862L;
	
	private Object source;
	private RequestType requestType;
	private transient Map<PolicyType, Policy> policies = new HashMap<PolicyType, Policy>();
	private transient EventResponse response = null;
	
	
	
	@Override
	public Object getSource() {
		return this.source;
	}

	@Override
	public void setSource(Object source) {
		this.source = source;
	}

	@Override
	public RequestType getRequestType() {
		return requestType;
	}

	@Override
	public void setRequestType(RequestType type) {
		requestType = type;
	}

	@Override
	public EventResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(EventResponse response) {
		this.response =response;
	}

	@Override
	public void installPolicy(PolicyType type, Policy policy) {
		policies.put(type, policy);
	}
	
	@Override
	public void unstallPolicy(PolicyType type) {
		policies.remove(type);
	}
	
	@Override
	public Policy findPolicy(PolicyType type) {
		return policies.get(type);
	}
	
	@Override
	public Iterable<PolicyType> policies() {
		return policies.keySet();
	}
	
	@Override
	public void addFailReason(Throwable t) {
		if(this.response == null){
			setResponse(new ResponseImp(ResponseCode.FAIL));
		}
		this.response.addFailException(t);
	}
}
