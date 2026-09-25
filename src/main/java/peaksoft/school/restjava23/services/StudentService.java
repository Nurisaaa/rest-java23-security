package peaksoft.school.restjava23.services;

import peaksoft.school.restjava23.dto.StudentRequest;
import peaksoft.school.restjava23.dto.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAll();

    StudentResponse getById(Long id);

    String create(StudentRequest studentRequest);

    String update(StudentRequest studentRequest, Long id);

    String delete(Long id);
}
