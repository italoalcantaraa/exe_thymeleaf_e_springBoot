package io.github.italoalcantaraa.treino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.italoalcantaraa.treino.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
