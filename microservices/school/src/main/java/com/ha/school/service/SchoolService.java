package com.ha.school.service;

import com.ha.school.client.StudentClient;
import com.ha.school.entity.FullSchoolResponse;
import com.ha.school.entity.School;
import com.ha.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentClient studentClient;

    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    public School findSchoolById(long schoolId) {
        return schoolRepository.findById(schoolId).orElseThrow(() -> new RuntimeException("School not found"));
    }

    public List<School> findAllSchool() {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolWithStudents(long schoolId) {

        var school = schoolRepository.findById(schoolId).orElseThrow(() -> new RuntimeException("School not found"));

        var student = studentClient.findAllStudentsBySchoolId(schoolId);

        FullSchoolResponse fullSchoolResponse = new FullSchoolResponse(school.getName(), school.getEmail(), student);

        return fullSchoolResponse;
    }
}
