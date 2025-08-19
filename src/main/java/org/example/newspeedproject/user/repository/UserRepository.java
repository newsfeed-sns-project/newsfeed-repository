package org.example.newspeedproject.user.repository;

import org.example.newspeedproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
