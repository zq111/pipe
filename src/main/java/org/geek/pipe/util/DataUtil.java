/**
 * 
 */
package org.geek.pipe.util;

import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

/**
 * @author haichuan
 * 2012-2-29
 */
public final class DataUtil {

	public static final <T> T deepCopy(Serializable orign, Class<T> T){
		Object tar = SerializationUtils.clone(orign);
		return T.cast(tar);
	}
}
