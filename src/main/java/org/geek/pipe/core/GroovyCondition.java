/**
 * 
 */
package org.geek.pipe.core;

import groovy.lang.GroovyShell;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.lang.StringUtils;

import org.geek.pipe.api.EventRequest;

/**
 * @author haichuan
 * @Create 2012-3-7
 */
public class GroovyCondition extends Conidition {

	private String file = "";
	private String code = "";
	private final GroovyShell shell = new GroovyShell();
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public ConditionType getType() {
		return ConditionType.groovy;
	}

	@Override
	public boolean eval(EventRequest request) throws ConditionException {
		try {
			shell.setVariable("request", request);
			if(StringUtils.isNotEmpty(code)){
				return returnFromCode();
			}
			else{
				return returnFromFile();
			}
		}
		catch (Exception e) {
			throw new ConditionException("ִ�нű��쳣", e);
		}
		
	}

	private boolean returnFromCode() throws Exception{
		Object result = shell.evaluate(code);
		if((result != null) && (result instanceof Boolean)){
			return ((Boolean)result).booleanValue();
		}
		return result != null;
	}
	
	private boolean returnFromFile() throws Exception{
		if(StringUtils.isEmpty(file)) return true;
		Object result = shell.evaluate(new FileInputStream(new File(file)));
		if((result != null) && (result instanceof Boolean)){
			return ((Boolean)result).booleanValue();
		}
		return result != null;
	}
}
