package com.example.student.config;

import com.example.student.Repository.StudentRepository;
import com.example.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
@Component
public class StudentAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        List<Student> student=studentRepository.findByName(username);
        if(student.size()>0)
        {
            if(passwordEncoder.matches(password,student.get(0).getPassword()))
            {
                List<GrantedAuthority> authorities=new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(student.get(0).getAuthority()));
                return new UsernamePasswordAuthenticationToken(username,password,authorities);

            }
            else {
                throw new BadCredentialsException("Invalid Password!!");
            }
        }
        else
        {
            throw new BadCredentialsException("No User Registered with the details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
