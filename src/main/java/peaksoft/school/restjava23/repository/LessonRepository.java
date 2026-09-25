package peaksoft.school.restjava23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.school.restjava23.dto.LessonResponse;
import peaksoft.school.restjava23.entities.Lesson;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select new peaksoft.school.restjava23.dto.LessonResponse(l.id, l.name, l.dateOfPublication) from Lesson l where l.name ilike  CONCAT('%', :name, '%')")
    List<LessonResponse> findAllByName(String name);
}
