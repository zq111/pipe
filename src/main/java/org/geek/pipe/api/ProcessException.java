/**
 * 
 */
package org.geek.pipe.api;

/**
 * @author haichuan
 *
 */
public class ProcessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5468027560888258969L;

	public ProcessException() {
	}
	
	public ProcessException(Throwable t) {
		super(t);
	}
	
	public ProcessException(String cause, Throwable t) {
		super(cause, t);
	}
}
