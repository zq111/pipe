/**
 * 
 */
package org.geek.pipe.api;

/**
 * @author haichuan
 */
public interface OutBound extends MessageFlow{

	InBound getInbound();
	void setInbound(InBound in);
	
	boolean isEnd();
	
	class EndBound implements OutBound{

		@Override
		public void deliverMessage(EventRequest request) throws DeliverException {}

		@Override
		public InBound getInbound() {
			return null;
		}

		@Override
		public void setInbound(InBound in) {}

		@Override
		public boolean isEnd() {
			return true;
		}
	}
	
	OutBound END = new EndBound();
}
