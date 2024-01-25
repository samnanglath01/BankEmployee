package com.ha.java.mapper;

import com.ha.java.dto.StudentsDto;
import com.ha.java.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toStudent(StudentsDto studentsDto);

    StudentsDto toStudentsDto(Student student);

    List<StudentsDto> toStudentDtos(List<Student> students);


}
