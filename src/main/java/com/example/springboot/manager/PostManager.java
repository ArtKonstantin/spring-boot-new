package com.example.springboot.manager;

import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;
import com.example.springboot.entity.PostEntity;
import com.example.springboot.exception.PostNotFoundException;
import com.example.springboot.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class PostManager {
    private final PostRepository postRepository;
    private final Function<PostEntity, PostResponseDTO> postEntityToPostResponseDTO = postEntity -> new PostResponseDTO(postEntity.getId(), postEntity.getName(), postEntity.getContent());

    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream()
                .map(postEntityToPostResponseDTO)
                .collect(Collectors.toList());
    }

    public PostResponseDTO getById(final long id) {
        return postRepository.findById(id)
                .map(postEntityToPostResponseDTO)
                .orElseThrow(PostNotFoundException::new);
    }

    public PostResponseDTO create(final PostRequestDTO requestDTO) {
        final PostEntity postEntity = new PostEntity(0, requestDTO.getName(), requestDTO.getContent());
        final var savedEntity = postRepository.save(postEntity);
        return postEntityToPostResponseDTO.apply(savedEntity);
    }

    public PostResponseDTO update(final PostRequestDTO requestDTO) {
        final PostEntity postEntity = postRepository.getReferenceById(requestDTO.getId());
        postEntity.setName(requestDTO.getName());
        postEntity.setContent(requestDTO.getContent());
        return postEntityToPostResponseDTO.apply(postEntity);
    }

    public void deleteById(final long id) {
        postRepository.deleteById(id);
    }
}
