package peaksoft.school.restjava23.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseRequest {
    @NotEmpty(message = "Название курса обязательно")
    private String name;

    private String image;

    @FutureOrPresent(message = "Дата начала не может быть в прошлом")
    private LocalDate dateOfStart;

    @NotEmpty(message = "Описание курса обязательно")
    private String description;
}
