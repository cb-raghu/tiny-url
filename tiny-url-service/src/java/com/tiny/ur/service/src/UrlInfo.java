/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiny.ur.service.src;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author cb-raghu
 */
public class UrlInfo {
    
    private String longUrl,clientId;
    private AtomicLong hitCount;
    
    public UrlInfo(String longUrl,String clientId) {
        this.longUrl = longUrl;
        this.clientId = clientId;
        hitCount = new AtomicLong(0);
    }
    
    public Long incrementCounter() {
        return this.hitCount.incrementAndGet();
    }
    
    public String clientId() {
        return this.clientId;
    }
    
    public String longUrl() {
        return this.longUrl;
    }
    
    public long hitCount() {
        return this.hitCount.get();
    }
}
