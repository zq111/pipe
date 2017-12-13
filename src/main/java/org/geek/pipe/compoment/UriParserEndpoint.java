/**
 * 
 */
package org.geek.pipe.compoment;

import java.util.HashMap;
import java.util.Map;


/**
 * @author haichuan
 * 2012-1-20
 */
public abstract class UriParserEndpoint implements EndPoint {

	private String uri;
	private Map<String, String> parmeters;
	
	@Override
	public String getUri() {
		return this.uri;
	}

	@Override
	public void setUri(String uri) {
		this.uri = uri;
		parmeters = parserUriParmeter();
	}

	protected Map<String, String> parserUriParmeter(){
		Map<String, String> result = new HashMap<String, String>();
		
		return result;
	}
	
	protected abstract boolean vaildateUri() throws VaildateURIException;
	
	public Map<String, String> getParmeters() {
		return parmeters;
	}
}
