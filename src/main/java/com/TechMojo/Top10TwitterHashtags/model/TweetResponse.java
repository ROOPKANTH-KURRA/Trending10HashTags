package com.TechMojo.Top10TwitterHashtags.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TweetResponse {

    @Column(name="count")
    @NotNull
    private String errorCode;

    @Column(name="tweet")
    @NotNull
    private String tweet;

    @Column(name="tweet_count")
    @NotNull
    private String responseMessage;

}
