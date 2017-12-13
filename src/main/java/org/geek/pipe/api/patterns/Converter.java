/**
 * 
 */
package org.geek.pipe.api.patterns;

import org.geek.pipe.converter.ConvertException;


/**
 * @author haichuan
 * 2012-2-2
 */
public interface Converter<F, T> {

	Class<F> from();
	Class<T> to();
	
	T convert(F f) throws ConvertException;
}
