package com.talmo.talboard.domain;

import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    private Long member_no;

    @NotNull
    private String id;

    @NotNull
    private String password;

    @NotNull
    private String emailAddress;

    @NotNull
    private boolean adminYn;

    @NotNull
    private boolean resignYn;

    @NotNull
    private LocalDateTime registDate;

//    @OneToMany(mappedBy = "member")
//    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Notice> notices = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    protected Member() {
        this.resignYn = false;
    }

    public Member(String id, String password, String emailAddress) {
        this.id = id;
        this.password = password;
        this.emailAddress = emailAddress;
        this.adminYn = false;
        this.resignYn = false;
        this.registDate = LocalDateTime.now();
    }

    //==비즈니스 로직==//
    public void resign() {
        this.resignYn = true;
    }
}
