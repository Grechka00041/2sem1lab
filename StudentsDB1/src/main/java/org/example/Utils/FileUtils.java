package org.example.Utils;

import org.example.Student.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {
    public static void writeToFile(Student[] students) {
        boolean appendFile = false;
        for(Student student: students) {
            if (student == null) {
                break;
            } else {
                try (FileWriter writer = new FileWriter("students.txt", appendFile)) {
                    writer.write(student.getFullName() + " ");
                    writer.write(student.getSex() + " ");
                    writer.write(student.getGroupNumber().toString() + " ");
                    writer.write(student.getStudentsNumber().toString() + " ");
                    Integer[] examMarks = student.getExamMarks();
                    writer.write(examMarks[0].toString() + " " + examMarks[1].toString() + " " + examMarks[2].toString() + " ");
                    Integer[] testMarks = student.getTestsMarks();
                    writer.write(testMarks[0].toString() + " " + testMarks[1].toString() + " " + testMarks[2].toString() + " " + testMarks[3].toString() + " " + testMarks[4].toString() + '\n');
                    writer.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                appendFile = true;
            }
        }
    }
    public static void readFile(Student[] students, Scanner scanner){
        try {
            File file = new File("students.txt");
            scanner = new Scanner(file);
            int num = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(!line.isEmpty()){
                    String[] elements = line.split(" ");
                    students[num] = new Student();
                    students[num].setFullName(elements[0], elements[1], elements[2]);
                    int sex = Byte.valueOf(elements[3]);
                    int i = (sex==1) ? DataUtils.male++ :  DataUtils.female++;
                    students[num].setSex(Byte.valueOf(elements[3]));
                    students[num].setGroupNumber(Integer.valueOf(elements[4]));
                    students[num].setStudentsNumber(Byte.valueOf(elements[5]));
                    Integer[] examMarks = {Integer.parseInt(elements[6]), Integer.parseInt(elements[7]), Integer.parseInt(elements[8])};
                    students[num].setExamMarks(examMarks);
                    Integer[] testMarks = {Integer.parseInt(elements[9]), Integer.parseInt(elements[10]), Integer.parseInt(elements[11]), Integer.parseInt(elements[12]),Integer.parseInt(elements[13])};
                    students[num].setTestsMarks(testMarks);
                    num++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
