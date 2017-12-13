/**
 * 
 */
package org.geek.pipe.api.optional;

import org.geek.pipe.api.ResourceException;

/**
 * @author haichuan
 * 2012-2-2
 */
public interface NeedRelease {

	void release() throws ResourceException;
}
