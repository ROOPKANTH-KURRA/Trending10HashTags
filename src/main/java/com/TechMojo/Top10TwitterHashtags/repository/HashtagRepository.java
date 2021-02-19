package com.TechMojo.Top10TwitterHashtags.repository;

import com.TechMojo.Top10TwitterHashtags.model.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<HashTag,Long> {

    @Query(value = "SELECT * FROM hashtag_repo ORDER BY count DESC LIMIT 10",nativeQuery = true)
    List<HashTag> getTop10HashTags();

    @Query(value="INSERT INTO hashtag_repo (hashtag) VALUES (:tweet) ON DUPLICATE KEY UPDATE count=count+1", nativeQuery = true)
    void insertOrUpdateHashTag(@Param("tweet") String tweet);



}
