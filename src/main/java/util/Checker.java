package util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

 

/**
 * 
 * 
 * @author: jaydenwang
 * @version: 1.0, Aug 5, 2014
 */
@SuppressWarnings("rawtypes")
public class Checker {

	/**
	 * 
	 * To judge the object or objects is/are null and empty or not.
	 * 
	 * @param pObj
	 * @param pCheckEmpty
	 * @return
	 */
	public static boolean isNullOrEmpty(final Object pObj, final boolean pCheckEmpty) {
		if (pObj == null) {
			return true;
		}
		if (!pCheckEmpty) {
			return false;
		}
		if (pObj instanceof String) {
			return StringUtils.isEmpty(((String) pObj).trim());
		}
		if (pObj instanceof CharSequence) {
			return ((CharSequence) pObj).length() == 0;
		}
		if (pObj instanceof Collection) {
			return ((Collection) pObj).isEmpty();
		}
		if (pObj instanceof Map) {
			return ((Map) pObj).isEmpty();
		}
		if (!pObj.getClass().isArray()) {
			return false;
		}

		// check object array
		return isArrayEmpty(pObj);
	}



	/**
	 * 
	 * overwrite the method isNullOrEmpty.
	 * 
	 * @param pObj
	 * @return
	 */
	public static boolean isNullOrEmpty(final Object pObj) {
		return isNullOrEmpty(pObj, true);
	}



	/**
	 * 
	 * To judge the object or objects is/are not null and empty or not.
	 * 
	 * @param pObj
	 * @return
	 */
	public static boolean isNotNullOrEmpty(final Object pObj) {
		return !isNullOrEmpty(pObj, true);
	}



	/**
	 * 
	 * To judge the object or objects is/are array null and empty or not.
	 * 
	 * @param pObj
	 * @return
	 */
	public static boolean isArrayEmpty(final Object pObj) {

		final Object[] objects = (Object[]) pObj;
		if (objects.length == 0) {
			return true;
		}
		for (int i = 0; i < objects.length; i++) {
			if (!isNullOrEmpty(objects[i])) {
				return false;
			}
		}
		return true;

	}
}
