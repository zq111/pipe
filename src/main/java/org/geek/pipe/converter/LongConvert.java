/**
 * 
 */
package org.geek.pipe.converter;

import org.geek.pipe.api.patterns.Converter;

/**
 * @author haichuan
 * @Create 2012-3-14
 */
public class LongConvert implements Converter<Object, Long> {

	@Override
	public Class<Object> from() {
		return Object.class;
	}

	@Override
	public Class<Long> to() {
		return Long.class;
	}

	@Override
	public Long convert(Object f) throws ConvertException{
		try {
			if(f == null){
				throw new NullPointerException("from is null");
			}
			return Long.parseLong(f.toString());
		} catch (Exception e) {
			throw new ConvertException(e);
		}
	}
	
}
