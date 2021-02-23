package com.ase.application.dto;

import com.ase.application.entity.PostType;
import com.ase.application.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Setter
@Getter
public class PostDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private String author;

    private String edition;

    private String title;

    private MultipartFile uploadedCoverPhoto;

    private User uploader;

    private String blog;

    private LocalDate date;
}