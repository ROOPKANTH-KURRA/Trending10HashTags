package com.TechMojo.Top10TwitterHashtags.service;

import com.TechMojo.Top10TwitterHashtags.model.HashTag;
import com.TechMojo.Top10TwitterHashtags.model.Tweet;
import com.TechMojo.Top10TwitterHashtags.model.TweetResponse;

import java.util.List;

public interface HashTagService {

    List<String> getTopTenHashTags() throws Exception;

    TweetResponse saveHashTag(Tweet tweet);
}
