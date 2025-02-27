package org.example.Student;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Teacher extends Person {
    Integer age;
    String degree;
    String subject;
    String mail;
    Integer groupNumber;
}
