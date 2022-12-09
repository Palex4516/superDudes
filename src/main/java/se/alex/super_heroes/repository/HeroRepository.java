package se.alex.super_heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.alex.super_heroes.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
}
