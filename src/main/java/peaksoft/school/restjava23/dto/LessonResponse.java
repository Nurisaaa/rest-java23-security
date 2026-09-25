package peaksoft.school.restjava23.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LessonResponse {
    private Long id;
    private String name;
    private LocalDate dateOfPublication;

    public LessonResponse(Long id, String name, LocalDate dateOfPublication) {
        this.id = id;
        this.name = name;
        this.dateOfPublication = dateOfPublication;
    }

    public LessonResponse() {
    }
}
