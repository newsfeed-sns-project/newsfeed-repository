package org.example.newspeedproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updatePost(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
