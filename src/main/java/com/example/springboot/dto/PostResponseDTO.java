package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PostResponseDTO {
    private long id;
    private AuthorDTO author;
    private String name;
    private String content;
    private List<String> tags;
    private GeoDTO geo;
}
