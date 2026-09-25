package peaksoft.school.restjava23.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.school.restjava23.dto.LessonRequest;
import peaksoft.school.restjava23.dto.LessonResponse;
import peaksoft.school.restjava23.services.LessonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{id}")
    public List<LessonResponse> getAllByCourseId(@PathVariable Long id){
        return lessonService.getAllByCourseId(id);
    }

    @PostMapping
    public String create(@RequestBody LessonRequest lessonRequest){
        return lessonService.create(lessonRequest);
    }

    @PutMapping("/{id}")
    public String update(@Valid @RequestBody LessonRequest lessonRequest, @PathVariable Long id){
        return lessonService.udpate(lessonRequest, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return lessonService.delete(id);
    }

    @GetMapping("/get")
    public List<LessonResponse> getLessonByName(@RequestParam("name") String name){
        return lessonService.getByName(name);
    }
}
