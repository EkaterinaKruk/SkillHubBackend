package by.bsuir.skillhub.repo;

import by.bsuir.skillhub.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextLessonsRepository extends JpaRepository<TextLessons, Long> {
    List<TextLessons> findByLesson(Lessons lesson);
}