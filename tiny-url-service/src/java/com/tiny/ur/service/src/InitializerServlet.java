/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiny.ur.service.src;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author cb-raghu
 */
public class InitializerServlet extends HttpServlet {
    
    private static final TinyUrlService tinyUrlService;
    
    static {
         tinyUrlService = new TinyUrlService();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
          
        } catch (Throwable th) {
            throw new ServletException(th);
        }
    }
    
    
    public static TinyUrlService getTinyUrlService() {
        return tinyUrlService;
    }

    @Override
    public void destroy() {
    }    
}
