package com.TechMojo.Top10TwitterHashtags.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SynchronizeServiceImpl {

    private final List<SynchronizeService> synchronizeServiceList;

    @Autowired
    public SynchronizeServiceImpl(List<SynchronizeService> synchronizeServiceList){
        this.synchronizeServiceList = synchronizeServiceList;
    }

    //Caching cronjob scheduled for every 1 minute
    @Scheduled(fixedDelay = 1000 * 60 * 1 ,initialDelay = 1000 * 60 * 1)
    public void sync() {
        for(SynchronizeService synchronizeService: synchronizeServiceList){
            synchronizeService.sync();
        }
    }
}
