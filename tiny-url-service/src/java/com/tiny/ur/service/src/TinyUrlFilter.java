/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiny.ur.service.src;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cb-raghu
 */
public class TinyUrlFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.config = fc;
    }

    @Override
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
            FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sRequest;
        HttpServletResponse resp = (HttpServletResponse) sResponse;
        resp.setContentType("text/json");
        String response = "";
        try {
            String path = req.getServletPath();
            String method = req.getMethod().toLowerCase();

            if (path.equals("/shortenedURL") && method.equals("get")) {
                response = InitializerServlet.getTinyUrlService().getShortenedURL(req.getParameter("long_url"), req.getParameter("client_id"));
            } else if (path.equals("/getOriginalURL") && method.equals("get")) {
                InitializerServlet.getTinyUrlService().getLongURL(req.getParameter("short_url"))
                        .ifPresent((longUrl) -> {
                            try {
                                redirectTo(req, resp, longUrl);
                            } catch (Exception ex) {
                                Logger.getLogger(TinyUrlFilter.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

            } else if (path.equals("/getHitCount") && method.equals("get")) {
                response = InitializerServlet.getTinyUrlService().getHitCount(req.getParameter("short_url")).orElse(0l).toString();
            } else {
                response = "Invaid url";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        } finally {
            resp.getWriter().append(response);

        }
    }

    private void redirectTo(HttpServletRequest req, HttpServletResponse resp, String url) throws Exception {
        resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        resp.setHeader("Location", url);
    }

    @Override
    public void destroy() {
    }
}
