/**
 * 
 */
package org.geek.pipe.api;

/**
 * @author haichuan
 * @Create 2012-4-6
 */
public class DeliverException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6342526115223648723L;

	public DeliverException(String cause, Throwable t) {
		super(cause, t);
	}
	
	public DeliverException(Throwable t) {
		super(t);
	}
}
