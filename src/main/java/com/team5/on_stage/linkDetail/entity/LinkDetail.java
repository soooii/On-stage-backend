package com.team5.on_stage.linkDetail.entity;

import com.team5.on_stage.global.constants.Platform;
import com.team5.on_stage.link.entity.Link;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "link_detail")
public class LinkDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private String url;

    private boolean isDeleted;

    @Builder
    public LinkDetail(Long id, Link link, Platform platform, String url) {
        this.id = id;
        this.link = link;
        this.platform = platform;
        this.url = url;
        this.isDeleted = false;
    }
}
