/**
 * 
 */
package org.geek.pipe.transaction;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import lombok.extern.slf4j.Slf4j;

import org.geek.pipe.api.EventResponse;
import org.geek.pipe.api.policy.PolicyException;
import org.geek.pipe.core.AbstractPolicy;
import org.geek.pipe.core.ResponseCode;

/**
 * @author haichuan
 * 2012-2-2
 */
@Slf4j
public abstract class TransactionPolicy<Tran> extends AbstractPolicy<EventResponse> {

	private Tran internalTran = null;
	
	public void setInternalTran(Tran tran){
		this.internalTran = tran;
	}
	
	public Tran getInternalTran(){
		return this.internalTran;
	}
	
	public boolean hasInternalTran(){
		return this.internalTran != null;
	}
	
	protected abstract void commintInternal() throws TransactionalFailException;
	protected abstract void rollbackInternal() throws TransactionalFailException;
	
	@Override
	public void done() throws PolicyException{
		try {
			doTransactionInternal();
		} catch (TransactionalFailException e) {
			throw new PolicyException(e);
		}
	}

	private void doTransactionInternal() throws TransactionalFailException{
		if(condition.getResponseCode() == null) {
			log.warn("������ع�ʱû���趨�����״̬" + ReflectionToStringBuilder.toString(condition));
			return;
		}
		if(condition.getResponseCode().getCode() == ResponseCode.SUCCESSCOMMINT.getCode()){
			commintInternal();
		}
		else if(condition.getResponseCode().getCode() == ResponseCode.FAILROLLBACK.getCode()){
			rollbackInternal();
		}
	}
}
