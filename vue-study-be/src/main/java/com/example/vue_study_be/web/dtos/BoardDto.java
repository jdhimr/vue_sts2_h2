package com.example.vue_study_be.web.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto implements Serializable{
    private Long idx;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
}
