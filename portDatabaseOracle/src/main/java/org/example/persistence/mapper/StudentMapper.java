package org.example.persistence.mapper;

import org.example.domain.student.models.Student;
import org.example.persistence.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student entityToModel(StudentEntity entity);
    StudentEntity modelToEntity(Student student);
    //List<Student> entitiesToModels(List<StudentEntity> studentEntityList);
}
