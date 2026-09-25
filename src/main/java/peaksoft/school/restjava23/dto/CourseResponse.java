package peaksoft.school.restjava23.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseResponse {
    private Long id;
    private String name;
    private String image;
    private LocalDate date;
    private String description;
}
