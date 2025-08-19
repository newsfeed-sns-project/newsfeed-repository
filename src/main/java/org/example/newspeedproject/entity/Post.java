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

    //연관관계 설정 시 작성자 id값을 받아오면 그 값으로 교체 예정
    //생성자, service, controller 에서도 값 변경 필요
    //@Column(nullable = false)
    private Long authorId;

    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void updatePost(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

//    public void updatePost(String title, String contents, Long authorId) {
//        this.title = title;
//        this.contents = contents;
//        this.authorId = authorId;
//    }
}
