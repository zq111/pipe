/**
 * 
 */
package org.geek.pipe.compoment;

import org.geek.pipe.api.InBound;
import org.geek.pipe.api.OutBound;

/**
 * @author haichuan
 * 2012-1-20
 */
public interface EndPoint {

	String getUri();
	void setUri(String uri);
	
	InBound getInbound();
	OutBound getOutBound();
	
	String getUriPrefix();
}
