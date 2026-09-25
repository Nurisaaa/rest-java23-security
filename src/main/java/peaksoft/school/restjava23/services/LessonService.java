package peaksoft.school.restjava23.services;

import peaksoft.school.restjava23.dto.LessonRequest;
import peaksoft.school.restjava23.dto.LessonResponse;

import java.util.List;

public interface LessonService {
    List<LessonResponse> getAllByCourseId(Long id);

    String create(LessonRequest lessonRequest);

    String udpate(LessonRequest lessonRequest, Long id);

    String delete(Long id);

    List<LessonResponse> getByName(String name);
}

