package com.talmo.talboard.domain;

import com.sun.istack.NotNull;
import com.talmo.talboard.domain.vo.PostCreateVO;
import com.talmo.talboard.domain.vo.PostUpdateVO;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private Member member;

    @NotNull
    private String title;

    @NotNull
    private String context;

    @NotNull
    private String deleteYn;

    @NotNull
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "post")
    private Set<Likes> likesList = Collections.synchronizedSet(new HashSet<>());

    @OneToMany(mappedBy = "reportPostId.reportedPost")
    private List<Report> reports = Collections.synchronizedList(new ArrayList<>());

    protected Post() {}

    private Post(Member member, String title, String context) {
        this.member = member;
        this.title = title;
        this.context = context;
        this.deleteYn = "N";
        this.createDate = LocalDateTime.now();
    }

    public static Post create(PostCreateVO vo, Member member) {
        Post post = new Post(member, vo.getTitle(), vo.getContext());
        member.getPosts().add(post);
        return post;
    }

    public void update(PostUpdateVO vo) {
        this.title = vo.getTitle();
        this.context = vo.getContext();
    }

    public void delete() {
        this.deleteYn = "Y";
    }

    public Map<String, Integer> getLikesDislikesCnt() {
        Map<String, Integer> likesDislikes = new HashMap<>();

        int likes = (int) this.likesList.stream().filter(Likes::isLikeYn).count();
        int dislikes = this.likesList.size() - likes;

        likesDislikes.put("likes", likes);
        likesDislikes.put("dislikes", dislikes);

        return likesDislikes;
    }

}
