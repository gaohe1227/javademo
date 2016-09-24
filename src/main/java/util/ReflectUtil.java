package util;

/**
 * 反射工具类
 * @author SCY
 *
 * @createDate 2013-12-19上午09:29:45
 * 
 * @version 1.0
 */
public class ReflectUtil {
	
	/**
	 * 反射获取对象String类型属性值
	 * @param obj
	 * @param paramName
	 * @return String 对象属性值
	 * @throws Exception
	 */
	public static String getObjValue(Object obj, String paramName)
			throws Exception{
		
		String getMethodName = "get"
				+ Character.toUpperCase(paramName.charAt(0))
				+ paramName.substring(1);
		return (String) obj.getClass().getMethod(getMethodName).invoke(obj);
	}

	/**
	 * 为对象某String类型属性设置指定值
	 * @param obj 待赋值对象
	 * @param paramName  属性名称
	 * @param paramValue 属性值
	 * @throws Exception
	 */
	public static void setObjValue(Object obj, String paramName,
			String paramValue) throws Exception {
		
		String getMethodName = "set"
				+ Character.toUpperCase(paramName.charAt(0))
				+ paramName.substring(1);
		obj.getClass().getMethod(getMethodName, String.class).invoke(obj,
				paramValue);
	}
}
