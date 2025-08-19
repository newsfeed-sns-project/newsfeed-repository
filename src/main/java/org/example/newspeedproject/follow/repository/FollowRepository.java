package org.example.newspeedproject.follow.repository;

import org.example.newspeedproject.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {
}
