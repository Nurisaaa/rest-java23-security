package peaksoft.school.restjava23.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.school.restjava23.dto.StudentRequest;
import peaksoft.school.restjava23.dto.StudentResponse;
import peaksoft.school.restjava23.entities.Student;
import peaksoft.school.restjava23.exception.NotFoundException;
import peaksoft.school.restjava23.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : students) {
            studentResponses.add(toResponse(student));
        }
        return studentResponses;
    }

    @Override
    public StudentResponse getById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student with id " + id + " not found")
        );
        return toResponse(student);
    }

    @Override
    public String create(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        studentRepository.save(student);
        return "Student created";
    }

    @Override
    public String update(StudentRequest studentRequest, Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student with id " + id + " not found")
        );
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        studentRepository.save(student);
        return "Student updated";
    }

    @Override
    public String delete(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student with id " + id + " not found")
        );
        studentRepository.delete(student);
        return "Student deleted";
    }

    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getStudyFormat()
        );
    }
}
