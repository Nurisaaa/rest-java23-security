package peaksoft.school.restjava23.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.school.restjava23.dto.CourseRequest;
import peaksoft.school.restjava23.dto.CourseResponse;
import peaksoft.school.restjava23.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getAll(){
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id){
        return courseService.getById(id);
    }

    @PostMapping
    public String create(@Valid @RequestBody CourseRequest courseRequest){
        return courseService.create(courseRequest);
    }

    @PutMapping("/{id}")
    public String update(@Valid @RequestBody CourseRequest courseRequest, @PathVariable Long id){
        return courseService.update(courseRequest, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return courseService.delete(id);
    }
}
