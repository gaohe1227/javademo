package com.cache.ehcache;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EhcacheController {
	Logger logger=Logger.getLogger(EhcacheController.class);
	 @RequestMapping(value = "ehcache/save.do" ,method=RequestMethod.GET)
	    public String index(Locale locale, Model model,HttpServletRequest request,
	            HttpServletResponse response) throws Exception{
	        logger.info("welcome  to  daotie home ,the client locale is "+ locale.toString());
	       
	       
	         
	        return "ehcache/show";
	    }
}
