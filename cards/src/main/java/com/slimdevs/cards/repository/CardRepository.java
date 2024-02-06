package com.slimdevs.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slimdevs.cards.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    
}
