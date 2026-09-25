package peaksoft.school.restjava23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.school.restjava23.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
