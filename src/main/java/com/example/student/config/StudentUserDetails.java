package com.example.student.config;

import com.example.student.Repository.StudentRepository;
import com.example.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentUserDetails implements UserDetailsService {

    @Autowired
    StudentRepository studentRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName,password=null;
        List<GrantedAuthority> authorities=null;
        List<Student> student=studentRepo.findByName(username);
        if(student.size()==0)
        {
            throw new UsernameNotFoundException("Userdetails not found for the user : "+ username);
        }
        else {
            userName=student.get(0).getName();
            password=student.get(0).getPassword();
            authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(student.get(0).getAuthority()));
        }
    return new User(username,password,authorities);
    }
}
