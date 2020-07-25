/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiny.ur.service.test;

import com.tiny.ur.service.src.TinyUrlService;

/**
 *
 * @author cb-raghu
 */
public class TinyUrlTest {
    
    private static final TinyUrlService tinyUrlService = new TinyUrlService();
    
    public static void main(String[] args) {
        testShortenUrl();
        testLongUrl();
        testHitCount();
        System.out.println("All test cases passed");
    }
    
    public static void testShortenUrl() {
        String shortenedUrl = tinyUrlService.getShortenedURL("https://google.com", "1");
        if(!shortenedUrl.equals("3tybsc")) {
            throw new RuntimeException("Test case failed");
        }
    }
    
    public static void testLongUrl() {
        String longUrl = tinyUrlService.getLongURL("3tybsc").orElse("Invlid URL");
        if(!longUrl.equals("https://google.com")) {
            throw new RuntimeException("Test case failed");
        }
    }
    
    public static void testHitCount() {
        Long count = tinyUrlService.getHitCount("3tybsc").get();
        if(!count.equals(1l)) {
            throw new RuntimeException("Test case failed");
        }
    }
}
