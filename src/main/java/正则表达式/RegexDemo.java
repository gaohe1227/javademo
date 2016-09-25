package 正则表达式;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 
	/*	Pattern pattern1 = Pattern.compile("Java");
		String[] strs = pattern1.split("Java Hello World  Java,Hello,,World|Sun");
		for (int i=0;i<strs.length;i++) {
		    System.out.println(strs[i]);
		} 
		*/
		
		
/*		Pattern pattern2 = Pattern.compile("href=\"(.+)\"");
		Matcher matcher = pattern2.matcher("<a href=\"index.html\">主页</a>");
		if(matcher.find()){
		  System.out.println(matcher.group());
		} */
		/*Matcher matcher = pattern1.matcher("<a href=/"index.html/">主页</a>");
		if(matcher.find())
		  System.out.println(matcher.group(1));
		}*/
/*		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher3 = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
		StringBuffer sbr = new StringBuffer();
		while (matcher3.find()) {
			matcher3.appendReplacement(sbr, "Java");
		}
		matcher3.appendTail(sbr);
		System.out.println(sbr.toString());*/
		String str1="asssssas";
		str1.replaceFirst("as", "12");
		System.out.println(str1);

		
		
		
	}

}
