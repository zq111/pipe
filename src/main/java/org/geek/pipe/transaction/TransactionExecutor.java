/**
 * 
 */
package org.geek.pipe.transaction;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.EventResponse;
import org.geek.pipe.api.policy.PolicyException;
import org.geek.pipe.api.policy.PolicyType;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public final class TransactionExecutor {

	public static final void doTransaction(EventRequest request) throws PolicyException{
		TransactionPolicy<EventResponse> transactionPolicy = (TransactionPolicy<EventResponse>)request.findPolicy(PolicyType.transaction);
		if(transactionPolicy != null){
			transactionPolicy.setCondition(request.getResponse());
			transactionPolicy.done();
		}
	}
}
