package com.example.springboot.dto;

import com.example.springboot.entity.Geo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PostResponseDTO {
    private long id;
    private String name;
    private String content;
    private List<String> tags;
    private Geo geo;
}
