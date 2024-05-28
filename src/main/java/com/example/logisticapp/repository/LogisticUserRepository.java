package com.example.logisticapp.repository;

import com.example.logisticapp.model.LogisticUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogisticUserRepository extends JpaRepository<LogisticUser, String> {
}
