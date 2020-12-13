package com.studymouse.studymouseserver.word;

import com.studymouse.studymouseserver.user.User;
import lombok.*;

import javax.persistence.*;

/**
 * Created by jyami on 2020/07/14
 */
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Word extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long id;

    private String english;

    private String korean;

    private String description;

    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Builder.Default
    private String color = "grey";

    @Setter
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ArchiveTag archiveTag = ArchiveTag.NOT_ARCHIVE;
}
