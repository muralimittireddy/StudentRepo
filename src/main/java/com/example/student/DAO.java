package com.example.student;

import java.util.List;

public interface DAO {
    public void saveStudent(Student student);
    public List<Student> getStudentByRoll(Integer roll);
    public void updateStudentByName(Student s);
    List<Student> getStudentByName(String name);
    public void deleteStudent(Student s);
}
