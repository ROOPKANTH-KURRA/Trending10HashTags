package com.TechMojo.Top10TwitterHashtags.service;

import com.TechMojo.Top10TwitterHashtags.Exception.TweetException;

import java.util.List;

public interface DBCacheService {
    List<String> getTop10HashTags() ;

}
