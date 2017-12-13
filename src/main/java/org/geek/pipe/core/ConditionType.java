/**
 * 
 */
package org.geek.pipe.core;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public enum ConditionType {

	constant("constant"), groovy("groovy");
	
	private String type;
	
	private ConditionType(String typeStr) {
		this.type = typeStr;
	}
	
	public final String getStrType(){
		return type;
	}
}
