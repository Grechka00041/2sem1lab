package org.example.Student;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Person {
    private String firstName;
    private String secondName;
    private String fathersName;
    Byte sex;
    public String getFullName(){
        return firstName + " " + secondName + " " + fathersName;
    }

    public void setFullName(String firstName, String secondName, String fathersName ){
        this.firstName = firstName;
        this.secondName = secondName;
        this.fathersName = fathersName;
    }
}
