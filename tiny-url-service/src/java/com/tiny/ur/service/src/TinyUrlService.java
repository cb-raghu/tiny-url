/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiny.ur.service.src;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author cb-raghu
 */
public class TinyUrlService {

    private Map<String, UrlInfo> urlHolder = new ConcurrentHashMap<>();

    public String getShortenedURL(String longUrl, String clientId) {
        String shortUrl = UrlShortner.shorten(longUrl, clientId);
        if (urlHolder.containsKey(shortUrl)) {
            return shortUrl;
        }

        this.urlHolder.computeIfAbsent(shortUrl, (url) -> new UrlInfo(longUrl, clientId));
        return shortUrl;
    }

    public Optional<String> getLongURL(String shortUrl) {
        UrlInfo urlInfo = this.urlHolder.get(shortUrl);
        if (urlInfo != null) {
            urlInfo.incrementCounter();
            return Optional.of(urlInfo.longUrl());
        } 
        
        return Optional.empty();
    }
    
    public Optional<Long> getHitCount(String shortUrl) {
        UrlInfo urlInfo = this.urlHolder.get(shortUrl);
        return urlInfo != null ? Optional.of(urlInfo.hitCount()) : Optional.empty();
    }
}
