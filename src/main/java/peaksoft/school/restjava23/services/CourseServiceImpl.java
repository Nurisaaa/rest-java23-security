package peaksoft.school.restjava23.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.school.restjava23.dto.CourseRequest;
import peaksoft.school.restjava23.dto.CourseResponse;
import peaksoft.school.restjava23.entities.Course;
import peaksoft.school.restjava23.exception.NotFoundException;
import peaksoft.school.restjava23.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;

    @Override
    public List<CourseResponse> getAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course : courses) {
            courseResponses.add(toResponse(course));
        }
        return courseResponses;
    }

    @Override
    public CourseResponse getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course with id " + id + " not found")
        );
        return toResponse(course);
    }

    @Override
    public String create(CourseRequest courseRequest) {
        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
        return "Course created";
    }

    @Override
    public String update(CourseRequest courseRequest, Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course with id " + id + " not found")
        );
        course.setName(courseRequest.getName());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
        return "Course updated";
    }

    @Override
    public String delete(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Course with id " + id + " not found")
        );
        courseRepository.delete(course);
        return "Course deleted";
    }

    private CourseResponse toResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setName(course.getName());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setImage(course.getImage());
        courseResponse.setDate(course.getDateOfStart());
        return courseResponse;
    }
}
