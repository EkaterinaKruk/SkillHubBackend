package by.bsuir.skillhub.repo;

import by.bsuir.skillhub.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    List<UserProgress> findByLesson(Lessons lesson);
    List<UserProgress> findByUser(Users user);
    List<UserProgress> findByUserAndCourse(Users user,Courses course);
}
