package com.example.student;

import com.example.student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    DAO dao;
    @Autowired
    StudentRepository studentRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

@PostMapping("/add_student")
    public Student saveStudent(@RequestBody Student student)
    {
       // System.out.println("postmapping");
//        dao.saveStudent(student);
        String hashPwd=passwordEncoder.encode(student.getPassword());
        student.setPassword(hashPwd);
        studentRepo.save(student);
        return student;
    }

    @GetMapping("/show")
    public void showSpeed()
    {
        System.out.println("text to displayed");
    }









    @GetMapping("/getStudent/{roll}")
    public List<Student> getStudent(@PathVariable("roll") Integer roll)
    {
        List<Student> s=dao.getStudentByRoll(roll);
       // System.out.println(s);
        return s;
    }

    @GetMapping("/getStudentByName/{name}")
    public List<Student> getStudentByName(@PathVariable("name") String name)
    {
        List<Student> s=dao.getStudentByName(name);
        // System.out.println(s);
        return s;
    }

    @PutMapping("/updateStudent/{roll}")
    public ResponseEntity<Student> UpdateStudent(@PathVariable("roll") Integer roll, @RequestBody Student updatedStudent)
    {
        List<Student> myStudent=dao.getStudentByRoll(roll);
        Student ss=myStudent.get(0);
        if(ss==null)
        {
            return ResponseEntity.notFound().build();
        }
        ss.setName(updatedStudent.getName());
        ss.setAge(updatedStudent.getAge());
        ss.setCls(updatedStudent.getCls());
        dao.updateStudentByName(ss);
        return ResponseEntity.ok(ss);
    }

    @DeleteMapping("/deleteStudent/{name}")
    public void deleteStudent(@PathVariable("name") String name) {
        // Logic to delete the resource with the specified id
        List<Student> s=dao.getStudentByName(name);
        dao.deleteStudent(s.get(0));
    }

}
