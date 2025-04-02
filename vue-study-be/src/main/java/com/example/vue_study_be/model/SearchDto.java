package com.example.vue_study_be.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDto {
    private String sk;  //search Key
    private String sv;  //search Value
}