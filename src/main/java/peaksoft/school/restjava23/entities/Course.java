package peaksoft.school.restjava23.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
    private LocalDate dateOfStart;
    private String description;
    @OneToMany
    private List<Student> students;
    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();
}
