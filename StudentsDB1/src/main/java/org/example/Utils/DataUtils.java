package org.example.Utils;

import org.example.Student.Person;
import org.example.Student.Student;
import org.example.Student.Teacher;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static org.example.Utils.FileUtils.writeToFile;
import static org.example.Utils.MenuUtils.printMenuForChange;
import static org.example.Utils.TeacherUtils.readTeacherRecord;
import static org.example.Utils.TeacherUtils.readTeachersFile;

public class DataUtils {
    public static int female = 0;
    public static int male = 0;
    public static void createRecord(Scanner scanner, Student[] students) {
        Student student = new Student();
        System.out.print("Введите имя студента: ");
        String firstName = scanner.nextLine();
        student.setFirstName(firstName);
        System.out.print("Введите фамилию студента: ");
        String secondName = scanner.nextLine();
        student.setSecondName(secondName);
        System.out.print("Введите отчество студента: ");
        String fathersName = scanner.nextLine();
        student.setFathersName(fathersName);
        System.out.print("Введите пол студента(1 - М, 0 - Ж): ");
        byte sex = scanner.nextByte();
        while(!(sex == 0 || sex == 1)){
            System.out.println("\nПожалуйста введите корректное значение");
            sex = scanner.nextByte();
        }
        int res = (sex==1) ? male++: female++;
        student.setSex(scanner.nextByte());
        System.out.print("Укажите номер группы студента(в виде четырехзначного числа):");
        int group = scanner.nextInt();
        while (!(group > 0 && (int) Math.log10(group) + 1 == 4)) {
            System.out.println("\nПожалуйста введите корректное значение");
            group = scanner.nextInt();
        }
        student.setGroupNumber(group);
        System.out.print("Укажите номер студента: ");
        student.setStudentsNumber(scanner.nextByte());
        System.out.print("Введите оценки за экзамены(АиГ, Матан, Программирование): ");
        Integer[] examMarks = new Integer[3];
        for (int i = 0; i < 3; i++) {
            examMarks[i] = scanner.nextInt();
        }
        scanner.nextLine();
        student.setExamMarks(examMarks);
        System.out.print("Введите оценки за диф. зачеты (физика, информатика, иностранный язык, философия, ОРГ): ");
        Integer[] testMarks = new Integer[5];
        for (int i = 0; i < 5; i++) {
            testMarks[i] = scanner.nextInt();
        }
        student.setTestsMarks(testMarks);
        boolean accepted = true;
        for(int i = 0; i < countRecords(students); i++){
            if((students[i].getFirstName() == fathersName) && (students[i].getSecondName() == secondName) && (students[i].getFathersName() == fathersName)){
                System.out.println("Студент уже был добавлен ранее. Эти данные не будут сохранены, пожалуйста отредактируйте студента, а не добавляете заново.");
                accepted = false;
                break;
            }
        }
        if (accepted){
            students[countRecords(students)] = student;
        }
        scanner.nextLine();
    }
    public static void readRecord(Student student) {
        System.out.print(student.getFullName() + " " + student.getSex() + " " + student.getGroupNumber() + " " + student.getStudentsNumber());
        System.out.print(" " + Arrays.toString(student.getExamMarks()) + " " + Arrays.toString(student.getTestsMarks()) + "\n");
    }
    public static void showGroup(Student[] students, int group){
        for(Student student: students){
            if (student == null){
                break;
            }
            if (student.getGroupNumber() == group){
                readRecord(student);
            }
        }
        System.out.println("Вывод закончен.");
    }
    public static void showStudentsOfNumber(Student[] students, int num){
        for(Student student: students){
            if (student == null){
                break;
            }
            if (student.getStudentsNumber() == num){
                readRecord(student);
            }
        }
        System.out.println("Вывод закончен.");
    }
    public static void editRecord(Student[] students, Scanner scanner){
        byte choice;
        boolean accepted = true;
        printMenuForChange();
        scanner.nextLine();
        System.out.print("Введите имя студента: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию студента: ");
        String surname = scanner.nextLine();
        System.out.print("Введите отчество студента: ");
        String lastName = scanner.nextLine();
        int i;
        for(i = 0; i < countRecords(students); i++){
            if((Objects.equals(students[i].getFirstName(), firstName)) && (Objects.equals(students[i].getSecondName(), surname)) && (Objects.equals(students[i].getFathersName(), lastName))){
                accepted = true;
                break;
            }
            else{

                accepted = false;
            }
        }

        do{
            System.out.print("Введите номер нужного действия: ");
            if (accepted){
                choice = scanner.nextByte();
            }
            else{
                System.out.println("Студент не был найден. Редактирование невозможно.");
                choice=7;
            }
            switch(choice){
                case 1:
                    System.out.println("Введите в каждой новой строке имя, фамилию и отчество:");
                    scanner.nextLine();
                    students[i].setFullName(scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Введите значение(1 - М, 0 - Ж): ");
                    byte sex = scanner.nextByte();
                    while(!(sex == 0 || sex == 1)){
                        System.out.println("\nПожалуйста введите корректное значение");
                        sex = scanner.nextByte();
                    }
                    int res = (sex == 1) ? (male++ & female--): (male-- & female++);
                    students[i].setSex(scanner.nextByte());
                    break;
                case 3:
                    System.out.print("Введите номер студента: ");
                    students[i].setStudentsNumber(scanner.nextByte());
                    break;
                case 4:
                    System.out.print("Введите группы студента: ");
                    students[i].setGroupNumber(scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Введите оценки за экзамены(АиГ, Матан, Программирование): ");
                    Integer[] examMarks = new  Integer[3];
                    for (int j = 0; j < 3; j++) {
                        examMarks[j] = scanner.nextInt();
                    }
                    students[i].setExamMarks(examMarks);
                    break;
                case 6:
                    System.out.print("Введите оценки за диф. зачеты (физика, информатика, иностранный язык, философия, ОРГ): ");
                    Integer[] testMarks = new Integer[5];
                    for (int j = 0; j < 5; j++) {
                        testMarks[j] = scanner.nextInt();
                    }
                    students[i].setExamMarks(testMarks);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Пожалуйста выберите число из предложенных вариантов.");
            }
        }while(choice != 7);
    }
    public static void doChoice(int choice, Scanner scanner, Student[] students){
        switch(choice){
            case 1:
                scanner.nextLine();
                createRecord(scanner, students);
                break;
            case 2:
                editRecord(students, scanner);
                break;
            case 3:
                for(Student student: students){
                    if (student == null){
                        break;
                    }
                    readRecord(student);

                }
                break;
            case 4:
                System.out.print("Выберите нужную группу: ");
                int group  = scanner.nextInt();
                while (!(group > 0 && (int) Math.log10(group) + 1 == 4)) {
                    System.out.println("\nПожалуйста введите корректное значение");
                    group = scanner.nextInt();
                }
                showGroup(students, group);
                break;
            case 5:
                System.out.print("Выберите нужный номер студентов: ");
                int num = scanner.nextInt();
                showStudentsOfNumber(students, num);
                break;
            case 6:
                showMarksStatistics(students, scanner);
                break;
            case 7:
                System.out.print("Выберите количество студентов для вывода: ");
                int numberForPrint = scanner.nextInt();
                for (int i = 0; i < countRecords(students); i++){
                    findAverage(students[i]);
                }
                Student[] studentsSortedByMarks = new Student[countRecords(students)];
                sortByAverage(students, studentsSortedByMarks);
                for(int i = 0; i < numberForPrint; i++){
                    readRecord(studentsSortedByMarks[i]);
                }

                break;
            case 8:
                System.out.println("Женщин - " + DataUtils.female + ", мужчин - " + DataUtils.male);
                break;
            case 9:
                boolean appendFile = false;
                for(Student student: students){
                    if (student == null){
                        break;
                    }
                    else{
                        writeToFile(students);
                    }
                }
                System.out.println("Сохранено");
                break;
            case 10:
                System.out.println("Опции: 1 - поиск по фамилии, 2 - поис по номеру группы, 3 - поиск по дисциплине, 4 - выход ");
                Teacher[] teachers = new Teacher[500];
                readTeachersFile(teachers, scanner);
                int reply;
                do{
                    System.out.print("Введите номер нужного действия: ");
                    reply = scanner.nextInt();
                    switch (reply){
                        case 1:
                            System.out.print("Введите фамилию нужного преподавателя: ");
                            scanner.nextLine();
                            String secondName = scanner.nextLine();
                            for(int i=0; i < countRecords(teachers); i++){
                                if(Objects.equals(teachers[i].getSecondName(), secondName)){
                                    readTeacherRecord(teachers[i]);
                                }
                            }
                            System.out.println("\nКонец поиска.");

                            break;
                        case 2:
                            System.out.print("Введите группу нужного преподавателя: ");
                            scanner.nextInt();
                            Integer groupNumber = scanner.nextInt();
                            for(int i=0; i < countRecords(teachers); i++){
                                if(Objects.equals(teachers[i].getGroupNumber(), groupNumber)){
                                    readTeacherRecord(teachers[i]);
                                }
                            }
                            System.out.println("\nКонец поиска.");
                            break;
                        case 3:
                            System.out.print("Введите предмет нужного преподавателя: ");
                            scanner.nextLine();
                            String subject = scanner.nextLine();
                            for(int i=0; i < countRecords(teachers); i++){
                                if(Objects.equals(teachers[i].getSubject(), subject)){
                                    readTeacherRecord(teachers[i]);
                                }
                            }
                            System.out.println("\nКонец поиска.");
                            break;
                        case 4:
                            System.out.println("Вы вернулись в основное меню");
                            break;
                    }
                }while(choice != 4);

                break;
            case 11:
                break;
            default:
                System.out.println("Пожалуйста выберите число из предложенных вариантов.");
        }
    }
    public static void showMarksStatistics(Student[] students, Scanner scanner) {
        System.out.println("Выберите каких студентов показать(1 - отличники, 2 - хорошисты, 3 - без стипендии, 4 - со стипендией, 5 - выход): ");
        int choice;
        do {
            System.out.print("Пункт: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Отличники: ");
                    for (int i = 0; i < countRecords(students); i++) {
                        boolean hasNotFive = false;
                        for (Integer mark : students[i].getTestsMarks()) {
                            if (mark == 3 || mark == 4) {
                                hasNotFive = true;
                                break;
                            }
                        }
                        for (Integer mark : students[i].getExamMarks()) {
                            if (mark == 3 || mark == 4) {
                                hasNotFive = true;
                                break;
                            }
                        }
                        if (!hasNotFive) {
                            readRecord(students[i]);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Хорошисты: ");
                    for (int i = 0; i < countRecords(students); i++) {
                        boolean hasThree = false;
                        boolean hasFour = false;
                        for (Integer mark : students[i].getTestsMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                            if (mark == 4) {
                                hasFour = true;
                            }
                        }
                        for (Integer mark : students[i].getExamMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                            if (mark == 4) {
                                hasFour = true;
                            }
                        }
                        if (hasFour && !hasThree) {
                            readRecord(students[i]);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Без стипендии: ");
                    for (int i = 0; i < countRecords(students); i++) {
                        boolean hasThree = false;
                        for (Integer mark : students[i].getTestsMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        for (Integer mark : students[i].getExamMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        if (hasThree) {
                            readRecord(students[i]);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Со стипендией: ");
                    for (int i = 0; i < countRecords(students); i++) {
                        boolean hasThree = false;
                        for (Integer mark : students[i].getTestsMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        for (Integer mark : students[i].getExamMarks()) {
                            if (mark == 3) {
                                hasThree = true;
                                break;
                            }
                        }
                        if (!hasThree) {
                            readRecord(students[i]);
                        }
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Введите корректное значение.");
            }

        }while(choice != 5);
    }
    public static void findAverage(Student student){
        int sum = 0;
        for(Integer mark: student.getExamMarks()){
            sum += mark;
        }
        for(Integer mark: student.getTestsMarks()){
            sum += mark;
        }
        double average = (double) sum / 8;
        student.setMarkAverage(average);

    }
    public static void sortByAverage(Student[] students, Student[] studentsSortedByMarks){
        for (int i = 0; i < countRecords(students); i++) {
            studentsSortedByMarks[i] = students[i];
        }
        for (int i = 0; i < studentsSortedByMarks.length; i++){
            for (int j = 0; j < studentsSortedByMarks.length - i - 1; j++){
                if (studentsSortedByMarks[j].getMarkAverage() < studentsSortedByMarks[j+1].getMarkAverage()){
                    Student temp = studentsSortedByMarks[j];
                    studentsSortedByMarks[j] = studentsSortedByMarks[j+1];
                    studentsSortedByMarks[j+1] = temp;

                }
            }
        }

    }
    public static int countRecords(Person[] people){
        int count;
        for (count = 0; count < people.length; count++){
            if (people[count] == null){
                break;
            }
        }
        return count;
    }
}
