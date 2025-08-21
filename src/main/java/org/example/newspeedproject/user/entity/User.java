package org.example.newspeedproject.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newspeedproject.comment.entity.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk, autoincrement

    @Column(length = 20)
    private String username;

    @Column(length = 30)
    private String email;

    @Column(length = 100)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void updateUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    private User(Long id) {
        this.id = id;
    }

    public static User fromUserId(Long id) {
        return new User(id);
    }
}
