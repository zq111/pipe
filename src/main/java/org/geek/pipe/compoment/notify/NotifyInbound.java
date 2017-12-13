///**
// *
// */
//package org.geek.pipe.compoment.notify;
//
//import org.geek.pipe.api.DeliverException;
//import org.geek.pipe.api.EventRequest;
//import org.geek.pipe.api.ProcessException;
//import org.geek.pipe.api.patterns.Converter;
//import org.geek.pipe.api.policy.PolicyType;
//import org.geek.pipe.converter.ConvertException;
//import org.geek.pipe.core.AbstractInbound;
//import org.geek.pipe.core.Registry;
//import org.geek.pipe.transaction.NotifyTransactionPolicy;
//import com.taobao.notify.message.Message;
//import com.taobao.notify.remotingclient.MessageListener;
//import com.taobao.notify.remotingclient.MessageStatus;
//
///**
// * @author haichuan
// * 2012-2-24
// */
//public class NotifyInbound extends AbstractInbound {
//
//	private MessageSubscriber subscriber;
//
//
//	@Override
//	public void deliverMessage(EventRequest request) throws DeliverException {
//		try {
//			getProcessor().process(request);
//		} catch (ProcessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new DeliverException(e);
//		}
//	}
//
//	public void setSubscriber(MessageSubscriber subscriber) {
//		this.subscriber = subscriber;
//		this.subscriber.addMessageListener(new MessageReceiver());
//	}
//
//
//	public class MessageReceiver implements MessageListener {
//
//		@Override
//		public void receiveMessage(Message message, MessageStatus status) {
//			try{
//				EventRequest request = buildRequest(message, status);
//				NotifyInbound.this.deliverMessage(request);
//			}
//			catch(DeliverException e){
//				//���������лع�����Ҫ�����ع�
//				//TODO
//			}
//			catch (ConvertException e) {
//				status.setRollbackOnly();
//			}
//		}
//
//		private EventRequest buildRequest(Message message, MessageStatus status) throws ConvertException{
//			Converter<Message, EventRequest> converter = Registry.findConverter(Message.class, EventRequest.class);
//			EventRequest request = converter.convert(message);
//			request.installPolicy(PolicyType.transaction, new NotifyTransactionPolicy(status));
//			return request;
//		}
//
//	}
//}
