package com.dingdong.lastdance_s.service;

import com.dingdong.lastdance_s.entity.Class;
import com.dingdong.lastdance_s.entity.User;
import com.dingdong.lastdance_s.repository.ClassRepository;
import com.dingdong.lastdance_s.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    private final UserRepository userRepository;
    private final ClassRepository classRepository;

    @Autowired
    public ClassService(UserRepository userRepository, ClassRepository classRepository) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
    }

    public String createClass(String email, String schoolName, int grade, int classNo, String classNickname, Timestamp createTime)
    {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty())
        {
            return "Error: Teacher with the provided email does not exist.";
        }

        User teacher = userOptional.get();

        int classCount = classRepository.countByUserId(teacher.getId());
        if (classCount >= 2) {
            return "Error: You cannot create more than 2 classes.";
        }

        Class newClass = new Class();
        newClass.setSchoolName(schoolName);
        newClass.setGrade(grade);
        newClass.setClassNo(classNo);
        newClass.setClassNickname(classNickname);
        newClass.setId(teacher);
        newClass.setClassCreated(createTime);

        Timestamp classExpire;
        if (createTime.toLocalDateTime().getMonthValue() > 3)
        {
            classExpire = Timestamp.valueOf(createTime.toLocalDateTime().plusYears(2).withMonth(3).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0));
        }
        else
        {
            classExpire = Timestamp.valueOf(createTime.toLocalDateTime().plusYears(1).withMonth(3).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0));
        }
        newClass.setClassExpire(classExpire);

        classRepository.save(newClass);

        return "Class created successfully.";
    }

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(int id) {
        return classRepository.findById(id);
    }

    public List<Class> getClassesBySchoolName(String schoolName) {
        return classRepository.findBySchoolName(schoolName);
    }

    public List<Class> getClassesByGradeAndClassNo(int grade, int classNo) {
        return classRepository.findByGradeAndClassNo(grade, classNo);
    }

    public List<Class> getClassesByTeacherId(int teacherId) {
        return classRepository.findByUserId(teacherId);
    }

    public void deleteClassByUserId(int teacherId) {
        classRepository.deleteByUserId(teacherId);
    }

    public int getClassCountByTeacherId(int teacherId) {
        return classRepository.countByUserId(teacherId);
    }

    public void deleteClassByUserIdAndId(int teacherId, int classId) {
        classRepository.deleteClassByUserIdAndId(teacherId, classId);
    }
}
