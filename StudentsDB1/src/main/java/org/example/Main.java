package org.example;
import org.example.Student.Student;
import org.example.Student.Teacher;

import java.util.Scanner;

import static org.example.Utils.DataUtils.*;
import static org.example.Utils.FileUtils.readFile;
import static org.example.Utils.MenuUtils.printMainMenu;
import static org.example.Utils.TeacherUtils.readTeachersFile;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[500];
        readFile(students, scanner);
        if (students[0] == null){
            System.out.println("В базе нет ни одной записи. Пожалуйста добавьте новую.");
            createRecord(scanner, students);
        }
        printMainMenu();
        int choice = 0;
        while(choice != 11){
            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();
            doChoice(choice, scanner, students);
        }
        scanner.close();
    }
}