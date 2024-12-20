package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.Teacher;
import com.dingdong.lastdance_s.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService
{
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository)
    {
        this.teacherRepository = teacherRepository;
    }

    public Teacher processGoogleLogin(String email, String name, String picture, String googleToken) {
        Optional<Teacher> existingTeacher = teacherRepository.findByTeacherEmail(email);

        if (existingTeacher.isPresent()) {
            Teacher teacher = existingTeacher.get();
            teacher.setGoogleToken(googleToken);
            return teacherRepository.save(teacher);
        }

        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherName(name);
        newTeacher.setTeacherImg(picture);
        newTeacher.setTeacherEmail(email);
        newTeacher.setGoogleToken(googleToken);

        return teacherRepository.save(newTeacher);
    }
}
