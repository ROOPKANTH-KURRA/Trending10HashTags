package com.TechMojo.Top10TwitterHashtags.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tweet {

    @NotNull(message = "Tweet cannot be null")
    @NotEmpty(message = "Tweet cannot be empty")
    private String tweet;

}
