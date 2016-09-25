package freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	/**
	 * 获取模板
	 * @param name:模板名称
	 * @return
	 */
	  public Template getTemplate(String name) {
	        try {
	            // 通过Freemaker的Configuration读取相应的ftl
	            Configuration cfg = new Configuration();
	            // 设定去哪里读取相应的ftl模板文件
	            cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
	            // 在模板文件目录中找到名称为name的文件
	            Template temp = cfg.getTemplate(name);
	            return temp;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * 控制台输出
	     * 
	     * @param name
	     * @param root
	     */
	    public void print(String name, Map<String, Object> root) {
	        try {
	            // 通过Template可以将模板文件输出到相应的流
	            Template temp = this.getTemplate(name);
	            temp.process(root, new PrintWriter(System.out));
	        } catch (TemplateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	  /**
	   * 输出HTML文件
	   * @param name:模板名称
	   * @param root：存储数据
	   * @param outFile:输出的文件名
	   * @param basePathPackage:包路径
	   */
	    public void fprint(String name, Map<String, Object> root, String outFile,String basePathPackage) {
	        FileWriter out = null;
	        try {
	            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
	         /*   out = new FileWriter(new File("E:/page/" + outFile));*/
	        	File page=new File(basePathPackage);
	        	if(!page.exists()){
	        		page.mkdir();
	        	}
	        	System.out.println(page.getAbsolutePath());		
	        	
	        	out = new FileWriter(basePathPackage+File.separator + outFile);
	            Template temp = this.getTemplate(name);
	            temp.process(root, out);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (TemplateException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (out != null)
	                    out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
