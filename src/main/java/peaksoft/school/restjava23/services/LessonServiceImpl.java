package peaksoft.school.restjava23.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.school.restjava23.dto.LessonRequest;
import peaksoft.school.restjava23.dto.LessonResponse;
import peaksoft.school.restjava23.entities.Course;
import peaksoft.school.restjava23.entities.Lesson;
import peaksoft.school.restjava23.exception.NotFoundException;
import peaksoft.school.restjava23.repository.CourseRepository;
import peaksoft.school.restjava23.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    @Override
    public List<LessonResponse> getAllByCourseId(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found")
        );
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson : course.getLessons()) {
            LessonResponse lessonResponse = new LessonResponse();
            lessonResponse.setId(lesson.getId());
            lessonResponse.setName(lesson.getName());
            lessonResponse.setDateOfPublication(lesson.getDateOfPublication());
            lessonResponses.add(lessonResponse);
        }
        return lessonResponses;
    }

    @Override
    public String create(LessonRequest lessonRequest) {
        Course course = courseRepository.findById(lessonRequest.getCourseId()).orElseThrow(
                () -> new NotFoundException("Course not found")
        );
        Lesson lesson = new Lesson();
        lesson.setName(lessonRequest.getName());
        lesson.setDateOfPublication(lessonRequest.getDateOfPublication());
        course.getLessons().add(lesson);
        lessonRepository.save(lesson);
        return "Lesson created";
    }

    @Override
    public String udpate(LessonRequest lessonRequest, Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson not found")
        );
        lesson.setName(lessonRequest.getName());
        lesson.setDateOfPublication(lessonRequest.getDateOfPublication());
        lessonRepository.save(lesson);
        return "Lesson updated";
    }

    @Override
    public String delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lesson not found")
        );
        lesson.setCourse(null);
        lessonRepository.delete(lesson);
        return "Lesson deleted";
    }

    @Override
    public List<LessonResponse> getByName(String name) {
        return lessonRepository.findAllByName(name);
    }
}
