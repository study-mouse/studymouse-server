package com.studymouse.studymouseserver.word;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jyami on 2020/07/14
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Word extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String english;

    private String korean;

    private String url;
}
