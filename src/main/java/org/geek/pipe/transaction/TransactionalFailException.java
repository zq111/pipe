/**
 * 
 */
package org.geek.pipe.transaction;

/**
 * @author haichuan
 * 2012-2-2
 */
public class TransactionalFailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3644081757256502131L;

	public TransactionalFailException() {
	}
	
	public TransactionalFailException(Throwable cause) {
		super(cause);
	}
}
