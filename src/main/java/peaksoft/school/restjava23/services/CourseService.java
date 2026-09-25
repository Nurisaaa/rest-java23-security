package peaksoft.school.restjava23.services;

import peaksoft.school.restjava23.dto.CourseRequest;
import peaksoft.school.restjava23.dto.CourseResponse;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAll();

    CourseResponse getById(Long id);

    String create(CourseRequest courseRequest);

    String update(CourseRequest courseRequest, Long id);

    String delete(Long id);
}
