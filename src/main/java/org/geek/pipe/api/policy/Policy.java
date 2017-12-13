/**
 * 
 */
package org.geek.pipe.api.policy;

/**
 * @author haichuan
 * 2012-2-2
 */
public interface Policy<C> {

	void done() throws PolicyException;
	void setCondition(C condition);
}
