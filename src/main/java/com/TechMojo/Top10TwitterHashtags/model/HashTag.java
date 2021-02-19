package com.TechMojo.Top10TwitterHashtags.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
@Table(name = "Hashtag_repo")
public class HashTag {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="count")
    @NotNull
    private Long hashTagCount;

    @Column(name="hashtag")
    @NotNull
    private String hashtag;

}
