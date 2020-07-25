/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiny.ur.service.src;

import java.util.Objects;

/**
 *
 * @author cb-raghu
 */
public class UrlShortner {
    
    private static final char BASE_CHARACTERS[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); 
    private static final int BASE = 62;
    
    public static String shorten(String longUrl, String clientId) {
        StringBuffer shortUrl = new StringBuffer();  
        int hashCode = Math.abs(Objects.hash(longUrl,clientId));
        Character encodedChar = null;
        while (hashCode > 0)  
        {  
            encodedChar = BASE_CHARACTERS[hashCode%BASE];
            shortUrl.append(encodedChar); 
            hashCode /= BASE;
        }  
        return shortUrl.toString();
    }
    
}
