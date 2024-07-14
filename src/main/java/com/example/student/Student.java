package com.example.student;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roll")
    private int roll;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name="class")
    private int cls;
    @Column(name="password")
    private String password;

    public Student(int roll, String name, int age, int cls, String password, String email, String authority) {
        this.roll = roll;
        this.name = name;
        this.age = age;
        this.cls = cls;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Column(name="email")
    private  String email;
    @Column(name="authority")
    private String authority;

    public int getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public Student() {
    }

    public int getAge() {
        return age;
    }

    public int getCls() {
        return cls;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthority() {
        return authority;
    }
}
