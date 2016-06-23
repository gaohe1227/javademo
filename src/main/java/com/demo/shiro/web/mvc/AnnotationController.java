package com.demo.shiro.web.mvc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-12
 * <p>Version: 1.0
 */
@Controller
public class AnnotationController {

    @RequestMapping("/hello1")
    public String hello1() {
    	System.err.println("--------------------------------------hello1");
        SecurityUtils.getSubject().checkRole("admin");
        System.err.println("--------------------------------------hello1");
        
        
        return "success";
    }
   /**
    * shiro注解案例
    * @return
    */
    @RequiresRoles("admin")
    @RequestMapping("/hello2")
    public String hello2() {
    	System.err.println("--------------------------------------hello2");
        return "success";
    }
}
