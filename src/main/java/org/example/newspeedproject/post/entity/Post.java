package org.example.newspeedproject.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newspeedproject.commo.BaseEntity;
import org.example.newspeedproject.user.entity.User;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    @NotBlank
    private String contents;

    //연관관계 설정 시 작성자 id값을 받아오면 그 값으로 교체 예정
    //생성자, service, controller 에서도 값 변경 필요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void updatePost(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
