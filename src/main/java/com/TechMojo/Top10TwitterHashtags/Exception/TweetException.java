package com.TechMojo.Top10TwitterHashtags.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetException extends Exception{
    private String errorCode;

    public TweetException(){

    }
    public TweetException(String message){
        super(message);
    }
    public TweetException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
