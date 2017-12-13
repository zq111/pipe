/**
 * 
 */
package org.geek.pipe.api;

import java.io.Serializable;

import org.geek.pipe.api.policy.Policy;
import org.geek.pipe.api.policy.PolicyType;

/**
 * @author haichuan
 * 2012-2-1
 */
public interface EventRequest extends Serializable{

	Object getSource();
	void setSource(Object source);
	
	RequestType getRequestType();
	void setRequestType(RequestType type);
	
	void installPolicy(PolicyType type, Policy policy);
	void unstallPolicy(PolicyType type);
	Policy findPolicy(PolicyType type);
	Iterable<PolicyType> policies();
	
	EventResponse getResponse();
	void setResponse(EventResponse response);
	
	void addFailReason(Throwable t);
}
