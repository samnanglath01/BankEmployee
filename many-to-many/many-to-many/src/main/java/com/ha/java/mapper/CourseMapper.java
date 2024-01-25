package com.ha.java.mapper;

import com.ha.java.dto.CourseDto;
import com.ha.java.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toCourse(CourseDto courseDto);

    CourseDto toCourseDto(Course course);

    List<CourseDto> toCourseDto(List<Course> courses);


}
