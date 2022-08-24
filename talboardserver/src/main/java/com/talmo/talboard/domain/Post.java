package com.talmo.talboard.domain;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id @GeneratedValue
    private Long post_no;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "member_no")
    private Member member;

    @NotNull
    private String title;

    @NotNull
    private String context;

    @NotNull
    private String delete_yn;

    @NotNull
    private LocalDateTime create_date;

    public Post(Member member, String title, String context, String delete_yn, LocalDateTime create_date) {
        this.member = member;
        this.title = title;
        this.context = context;
        this.delete_yn = delete_yn;
        this.create_date = create_date;
    }
}
