///**
// *
// */
//package org.geek.pipe.transaction;
//
//import com.taobao.notify.remotingclient.MessageStatus;
//
///**
// * @author haichuan
// * 2012-2-24
// */
//public class NotifyTransactionPolicy extends TransactionPolicy<MessageStatus> {
//
//	private MessageStatus status;
//
//	public NotifyTransactionPolicy(MessageStatus status) {
//		this.status = status;
//	}
//
//	@Override
//	protected void commintInternal() throws TransactionalFailException {
//
//	}
//
//	@Override
//	protected void rollbackInternal() throws TransactionalFailException {
//		this.status.setRollbackOnly();
//	}
//}
