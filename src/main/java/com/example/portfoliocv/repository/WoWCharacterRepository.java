package com.example.portfoliocv.repository;

import com.example.portfoliocv.entity.WoWCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoWCharacterRepository extends JpaRepository<WoWCharacter, Long> {
}
