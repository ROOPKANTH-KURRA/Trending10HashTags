package com.TechMojo.Top10TwitterHashtags.controller;

import com.TechMojo.Top10TwitterHashtags.Exception.BadRequestException;
import com.TechMojo.Top10TwitterHashtags.Exception.TweetException;
import com.TechMojo.Top10TwitterHashtags.model.Tweet;
import com.TechMojo.Top10TwitterHashtags.model.TweetResponse;
import com.TechMojo.Top10TwitterHashtags.repository.HashtagRepository;
import com.TechMojo.Top10TwitterHashtags.model.HashTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.TechMojo.Top10TwitterHashtags.service.HashTagService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class HashtagsController {
    private HashTagService hashTagService;

    @Autowired
    public HashtagsController( HashTagService hashTagService){
        this.hashTagService=hashTagService;
    }

    @PostMapping(produces = "application/json",value = "/addTweet")
    public ResponseEntity<TweetResponse> addTweet(@RequestBody Tweet tweet, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getFieldErrors().iterator().next().getDefaultMessage());
        }
        return ResponseEntity.ok(hashTagService.saveHashTag(tweet));
    }

    @GetMapping(value="/getTopTenHashTags")
    public ResponseEntity<List<String>> getTopTenHashTags(){
        List<String> hasTags = new ArrayList<>();
        try {
            hasTags = hashTagService.getTopTenHashTags();
            return ResponseEntity.ok(hasTags);
        }catch(Exception e){
            return ResponseEntity.ok(Arrays.asList("Exception occured while fetching the HashTags. Please try again after some time:"+e));
        }
    }

}
