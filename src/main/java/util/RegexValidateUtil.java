package util;

 

/**
 * 
 * 正则表达式验证工具类
 * 
 * @author: jaydenwang
 * @version: 1.0, Aug 6, 2014
 */
public class RegexValidateUtil {

	/**
	 * 
	 * 根据传入的正则表达式来匹配传入的参数是否合法
	 * 
	 * @param pRegex
	 * @param pString
	 * @return
	 */
	public static boolean validateString(String pRegex, String pString) {
		if (Checker.isNullOrEmpty(pString)) {
			return false;
		}
		if (Checker.isNullOrEmpty(pRegex)) {
			return false;
		}
		return pString.matches(pRegex);
	}



	/**
	 * 
	 * 匹配图像 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
	 * 
	 * @return
	 */
	public static boolean validateImage(String pString) {
		String regex = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配IP地址
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateIp(String pString) {
		String regex = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配邮件地址 格式: XXX@XXX.XXX.XX
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateEmail(String pString) {
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配URL 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateUrl(String pString) {
		String regex = "[a-zA-z]+://[^s]*";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配Http和https 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或
	 * ftp://XXX.XXX.XXX 或 https://XXX
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateHttp(String pString) {
		String regex = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配12小时制 的时间
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateTimeTwelve(String pString) {
		String regex = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配24小时制的时间
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateTimeTwentyFour(String pString) {
		String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配年月日 格式(首位不为0): XXXX-XX-XX 或 XXXX XX XX 或 XXXX-X-X
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateDate(String pString) {
		String regex = "^(^(\\d{4}|\\d{2})(\\-|\\/|\\.)\\d{1,2}\\3\\d{1,2}$)|(^\\d{4}年\\d{1,2}月\\d{1,2}日$)$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配电话号码 格式 :0XXX-XXXXXX(10-13位首位必须为0),0XXX XXXXXXX(10-13位首位必须为0),(
	 * 0XXX)XXXXXXXX,XXXXXXXX(6-8位首位不为0),XXXXXXXXXXX(11位首位不为0)
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validatePhone(String pString) {
		String regex = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配身份征号码 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位)
	 * 或 XXXXXXXXXXXXXXXXXX(18位)
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateIDCard(String pString) {
		String regex = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配中国邮政编码 格式为: XXXXXX(6位)
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateZipCode(String pString) {
		String regex = "^[0-9]{6}$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配非负整数(正整数+0)
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateNonNegativeIntegers(String pString) {
		String regex = "^\\d+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配不包括零的非负整数（正整数 > 0)
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateNonZeroNegativeIntegers(String pString) {
		String regex = "^[1-9]+\\d*$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配正整数
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validatePositiveInteger(String pString) {
		String regex = "^[0-9]*[1-9][0-9]*$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配非正整数（负整数 + 0）
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateNonPositiveIntegers(String pString) {
		String regex = "^((-\\d+)|(0+))$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配负整数
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateNegativeIntegers(String pString) {
		String regex = "^-[0-9]*[1-9][0-9]*$";
		return validateString(regex, pString);
	}



	/**
	 * 匹配整数
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateInteger(String pString) {
		String regex = "^-?\\d+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配非负浮点数（正浮点数 + 0）
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateNonNegativeRationalNumbers(String pString) {
		String regex = "^\\d+(\\.\\d+)?$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配正浮点数
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validatePositiveRationalNumbers(String pString) {
		String regex = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配非正浮点数（负浮点数 + 0）
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateNonPositiveRationalNumbers(String pString) {
		String regex = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配负浮点数
	 * 
	 * @param pString
	 * @return
	 * 
	 */
	public static boolean validateNegativeRationalNumbers(String pString) {
		String regex = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配浮点数
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateRationalNumbers(String pString) {
		String regex = "^(-?\\d+)(\\.\\d+)?$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配由26个英文字母组成的字符串
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateLetter(String pString) {
		String regex = "^[A-Za-z]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配由26个英文字母的大写组成的字符串
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateUpwardLetter(String pString) {
		String regex = "^[A-Z]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配由26个英文字母的小写组成的字符串
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateLowerLetter(String pString) {
		String regex = "^[a-z]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配由数字和26个英文字母组成的字符串
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateLetterNum(String pString) {
		String regex = "^[A-Za-z0-9]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配由数字、26个英文字母或者下划线组成的字符串
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateLetterNumUnderline(String pString) {
		String regex = "^\\w+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配中文
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateChinese(String pString) {
		String regex = "^[\u4e00-\u9fa5]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配中英文
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateEnglishChinese(String pString) {
		String regex = "^[A-Za-z\u4e00-\u9fa5]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配中英文、数字
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateEnglishChineseNum(String pString) {
		String regex = "^[A-Za-z0-9\u4e00-\u9fa5]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配中文或者数字
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateChineseNum(String pString) {
		String regex = "^[0-9\u4e00-\u9fa5]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配由数字、中文或者下划线组成的字符串
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateChineseNumUnderline(String pString) {
		String regex = "^[0-9\u4e00-\u9fa5_]+$";
		return validateString(regex, pString);
	}



	/**
	 * 
	 * 匹配由数字、英文、中文或者下划线组成的字符串
	 * 
	 * @param pString
	 * @return
	 */
	public static boolean validateEnglishChineseNumUnderline(String pString) {
		String regex = "^[A-Za-z0-9\u4e00-\u9fa5_]+$";
		return validateString(regex, pString);
	}

}
