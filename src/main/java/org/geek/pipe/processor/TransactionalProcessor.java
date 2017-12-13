/**
 * 
 */
package org.geek.pipe.processor;

import lombok.extern.slf4j.Slf4j;

import org.geek.pipe.api.EventRequest;
import org.geek.pipe.api.Processor;
import org.geek.pipe.api.policy.PolicyException;
import org.geek.pipe.core.DelegateProcessor;
import org.geek.pipe.core.ResponseCode;
import org.geek.pipe.transaction.TransactionExecutor;

/**
 * @author haichuan
 * 2012-2-2
 */
@Slf4j
public class TransactionalProcessor extends DelegateProcessor {

	public TransactionalProcessor(Processor processor) {
		super(processor);
	}
	
	public TransactionalProcessor() {
	}

	@Override
	protected void doFail(EventRequest request) {
		failRollBack(request);
	}

	@Override
	protected void doFinally(EventRequest request) {
		try {
			TransactionExecutor.doTransaction(request);
		} catch (PolicyException e) {
			log.error("�ύ�����쳣", e);
			e.printStackTrace();
		}
		finally{
			
		}
	}

	@Override
	protected void doBefore(EventRequest request) {
	}

	@Override
	protected void doAfter(EventRequest request) {
		//����ϲ㴦������쳣�ͻ��ߵ�doFail���� �����ع�
		//���û�г����쳣 ������Ҫ�ع� �ϲ㴦����Ҫ���� ���ع�������
		//���û�г����쳣 ��������������ع�װ�� @see TranCommitProcessor ��ô������Լ��ύ
		//���û�г����쳣 Ҳû����������ع�װ�� ��ô���������ύ
		if(request.getResponse() == null || request.getResponse().getResponseCode().getCode() != ResponseCode.FAILROLLBACK.getCode()){
			doneCommit(request);
		}
	}

}
