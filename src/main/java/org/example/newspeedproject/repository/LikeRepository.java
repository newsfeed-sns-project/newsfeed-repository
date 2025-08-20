package org.example.newspeedproject.repository;

import org.example.newspeedproject.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
