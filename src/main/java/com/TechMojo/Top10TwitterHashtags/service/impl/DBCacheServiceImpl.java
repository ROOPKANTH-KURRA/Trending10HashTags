package com.TechMojo.Top10TwitterHashtags.service.impl;

import com.TechMojo.Top10TwitterHashtags.Exception.TweetException;
import com.TechMojo.Top10TwitterHashtags.repository.HashtagRepository;
import com.TechMojo.Top10TwitterHashtags.scheduler.SynchronizeService;
import com.TechMojo.Top10TwitterHashtags.model.HashTag;
import com.TechMojo.Top10TwitterHashtags.service.DBCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DBCacheServiceImpl implements SynchronizeService, DBCacheService {

    private final HashtagRepository hashtagRepository;
    private List<HashTag> hashTagsCache = new ArrayList<>();

    @Autowired
    public DBCacheServiceImpl(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    @PostConstruct
    private void init(){
        try{
            log.info("Initialising the caching of HashTag DB data");
            loadHashTagData();
        }catch(Exception e){
            log.error("Exception caused while loading HashTag Repo data:",e);
        }
    }


    public void loadHashTagData(){
        try{
            hashTagsCache = hashtagRepository.getTop10HashTags();
            if(hashTagsCache==null||hashTagsCache.isEmpty()){
                log.info("No data in HashTag Repo");
            }
        }catch (Exception e){
            log.info(" Exception while loading HashTag Repo data:", e);
            throw e;
        }
    }

    public void sync(){
        refresh();
    }

    private void refresh(){
        loadHashTagData();
    }

    public List<String> getTop10HashTags() {
        try {
            List<String> top10HashTags = new ArrayList<>(10);
            List<HashTag> finalHashTagObjects = hashTagsCache.parallelStream().filter(Objects::nonNull).collect(Collectors.toList());
            for (HashTag hashTag : finalHashTagObjects) {
                top10HashTags.add(hashTag.getHashtag() + " ->" + " Appeared  " + hashTag.getHashTagCount() + " times");
            }
            final List<String> top10hashTagsList = new ArrayList<>(top10HashTags);
            top10HashTags.clear();
            return top10hashTagsList;
        }catch(Exception e){
            log.error("Exception occured while fetching top trending tweets",e);
            throw e;
        }
    }

}
