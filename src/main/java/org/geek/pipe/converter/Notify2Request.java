///**
// *
// */
//package org.geek.pipe.converter;
//
//import org.geek.pipe.api.EventRequest;
//import org.geek.pipe.api.patterns.Converter;
//import org.geek.pipe.core.EventRequestImp;
//import org.geek.pipe.helper.EventRequestHelper;
//import com.taobao.notify.message.Message;
//
///**
// * @author haichuan
// * 2012-2-24
// */
//public class Notify2Request implements Converter<Message, EventRequest> {
//
//	@Override
//	public Class<Message> from() {
//		return Message.class;
//	}
//
//	@Override
//	public Class<EventRequest> to() {
//		return EventRequest.class;
//	}
//
//	@Override
//	public EventRequest convert(Message m) {
//		EventRequest request = EventRequestHelper.createRequest();
//		request.setSource(m);
//		return request;
//	}
//
//
//}
