package com.ha.java.testing.service;

import com.ha.java.testing.client.StudentClient;
import com.ha.java.testing.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final StudentClient studentClient;

    StudentDto createStudent(StudentDto studentDto) {
        return studentClient.createStudent(studentDto);
    }

//    StudentDto getStudent(long id) {
//        return studentClient.getStudentById(id);
//    }

    List<StudentDto> getStudentByName(String name) {
        return studentClient.getStudentByName(name);
    }

    List<StudentDto> getAllStudent() {
        return studentClient.getAllStudent();
    }

    StudentDto addCourse(long studentId, long courseId) {
        return studentClient.addCourse(studentId, courseId);
    }


}
