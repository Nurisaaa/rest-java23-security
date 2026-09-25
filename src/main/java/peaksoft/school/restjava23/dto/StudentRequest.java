package peaksoft.school.restjava23.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentRequest {
    @NotEmpty(message = "Имя обязательно")
    private String firstName;

    @NotEmpty(message = "Фамилия обязательна")
    private String lastName;

    @Email(message = "Неверный формат email")
    private String email;

    @Pattern(
            regexp = "^(?:\\+996|0)(?:22[0-79]|312|50[0-57-9]|55\\d|70\\d|755|77\\d|99[05-9])\\d{6}$",
            message = "Неверный формат кыргызского номера телефона. Пример: +996770123456 или 0550123456"
    )
    private String phoneNumber;

    @NotEmpty(message = "Укажите формат обучения (ONLINE/OFFLINE)")
    private String studyFormat;
}
