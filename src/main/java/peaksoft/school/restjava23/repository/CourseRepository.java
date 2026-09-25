package peaksoft.school.restjava23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.school.restjava23.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
