package org.example.Utils;


import org.example.Student.Teacher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TeacherUtils {
    public static void readTeachersFile(Teacher[] teachers, Scanner scanner){
        try {
            File file = new File("teachers.txt");
            scanner = new Scanner(file);
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(!line.isEmpty()){
                    String[] elements = line.split(" ");
                    teachers[num] = new Teacher();
                    teachers[num].setFullName(elements[0], elements[1], elements[2]);
                    Byte sex = Byte.valueOf(elements[3]);
                    teachers[num].setSex(sex);
                    teachers[num].setAge(Integer.valueOf(elements[4]));
                    teachers[num].setDegree(elements[5]);
                    teachers[num].setSubject(elements[6]);
                    teachers[num].setMail(elements[7]);
                    teachers[num].setGroupNumber(Integer.valueOf(elements[8]));
                    num++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static void readTeacherRecord(Teacher teacher) {
        System.out.print(teacher.getFullName() + " " + teacher.getSex() + " " + teacher.getAge() + " " + teacher.getDegree());
        System.out.print(" " + teacher.getSubject() + " " + teacher.getMail() + " " + teacher.getGroupNumber());
    }
}
