package com.team5.on_stage.theme.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "theme")
public class Theme {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String backgroundImage;

    private String buttonColor;

    private String profileColor;

    private String fontColor;

    private String iconColor;

    private int borderRadius;

    @Builder
    public Theme(String username) {
        this.username = username;
        this.backgroundImage = "https://s3-on-stage.s3.ap-northeast-2.amazonaws.com/backgroundImages/2.png";
        this.buttonColor = "#ffffff";
        this.profileColor = "#ffffff";
        this.fontColor = "#000000";
        this.iconColor = "#ffffff";
        this.borderRadius = 25;
    }
}
