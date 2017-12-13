/**
 * 
 */
package org.geek.pipe.compoment;

import org.geek.pipe.api.Group;

/**
 * @author haichuan
 * 2012-2-1
 */
public class AbstractComponent implements Group {

	private String groupId;
	
	@Override
	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
