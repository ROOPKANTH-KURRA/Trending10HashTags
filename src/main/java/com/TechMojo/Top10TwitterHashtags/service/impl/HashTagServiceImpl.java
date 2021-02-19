package com.TechMojo.Top10TwitterHashtags.service.impl;

import com.TechMojo.Top10TwitterHashtags.Exception.TweetException;
import com.TechMojo.Top10TwitterHashtags.model.Errors;
import com.TechMojo.Top10TwitterHashtags.model.HashTag;
import com.TechMojo.Top10TwitterHashtags.model.Tweet;
import com.TechMojo.Top10TwitterHashtags.model.TweetResponse;
import com.TechMojo.Top10TwitterHashtags.repository.HashtagRepository;
import com.TechMojo.Top10TwitterHashtags.service.DBCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TechMojo.Top10TwitterHashtags.service.HashTagService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class HashTagServiceImpl implements HashTagService {
    private DBCacheService dbCacheService;
    private HashtagRepository hashtagRepository;

    /*The Hashtags obey the following conditions:

    -> It starts with a hashtag: #
    -> It has to contain at least 1 letter: [a-zA-Z]
    -> It can contain any of the characters from the class [a-zA-Z0-9_]
    -> It cannot be preceded by a character of the class [a-zA-Z0-9_]*/
    private static final Pattern hashTagPattern = Pattern.compile("(?<![a-zA-Z0-9_])#(?=[0-9_]*[a-zA-Z])[a-zA-Z0-9_]+");

    @Autowired
    public HashTagServiceImpl(DBCacheService dbCacheService, HashtagRepository hashtagRepository) {
        this.dbCacheService = dbCacheService;
        this.hashtagRepository = hashtagRepository;
    }

    @Transactional
    @Override
    public List<String> getTopTenHashTags() throws TweetException{
        try {
            return dbCacheService.getTop10HashTags();
        }catch(Exception e){
            throw new TweetException(Errors.FETCHING_ERR_001,Errors.messages.get(Errors.FETCHING_ERR_001));
        }
    }

    @Transactional
    @Override
    public TweetResponse saveHashTag(Tweet tweet){
        TweetResponse tweetResponse = new TweetResponse().builder().build();
        tweetResponse.setTweet(tweet.getTweet());
        try {
            Matcher m = hashTagPattern.matcher(tweet.getTweet());
            while (m.find()) {
                String tag = m.group();
                hashtagRepository.insertOrUpdateHashTag(tag);
            }
            tweetResponse.setResponseMessage("Tweet successfully posted");
        }catch(Exception e){
            log.error("Exception occured while saving the tweet");
            tweetResponse.setErrorCode(Errors.SAVING_ERR_001);
            tweetResponse.setResponseMessage(Errors.messages.get(Errors.SAVING_ERR_001));
        }
        finally{
            return tweetResponse;
        }
    }


}
