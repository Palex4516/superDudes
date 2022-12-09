package se.alex.super_heroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.alex.super_heroes.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}
