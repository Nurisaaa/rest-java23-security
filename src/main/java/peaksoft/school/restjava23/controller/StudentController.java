package peaksoft.school.restjava23.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.school.restjava23.dto.StudentRequest;
import peaksoft.school.restjava23.dto.StudentResponse;
import peaksoft.school.restjava23.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public String create(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.create(studentRequest);
    }

    @PutMapping("/{id}")
    public String update(@Valid @RequestBody StudentRequest studentRequest, @PathVariable Long id) {
        return studentService.update(studentRequest, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return studentService.delete(id);
    }
}
