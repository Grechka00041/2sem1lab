package org.example.Student;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Student extends Person {
    private Byte studentsNumber;
    private Integer groupNumber;
    private double markAverage;
    private Integer[] examMarks = new Integer[3];
    private Integer[] testsMarks = new Integer[5];

}
