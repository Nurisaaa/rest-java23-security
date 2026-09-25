package peaksoft.school.restjava23.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LessonRequest {
    @Positive
    private Long courseId;
    @NotEmpty
    private String name;
    @FutureOrPresent
    private LocalDate dateOfPublication;
    @Pattern(
            regexp = "^(?:\\+996|0)(?:22[0-79]|312|50[0-57-9]|55\\d|70\\d|755|77\\d|99[05-9])\\d{6}$",
            message = "Неверный формат кыргызского номера телефона. Пример: +996770123456 или 0550123456"
    )
    private String number;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Пароль должен быть не менее 8 символов и содержать: одну заглавную букву, одну строчную букву, одну цифру и один спецсимвол (@$!%*?&)"
    )
    private String password;
}
