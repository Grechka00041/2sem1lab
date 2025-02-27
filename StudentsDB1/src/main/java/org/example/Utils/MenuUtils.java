package org.example.Utils;

public class MenuUtils {
    public static void printMainMenu(){
        System.out.println("Выберите действие:");
        System.out.println("1. Создать новую запись");
        System.out.println("2. Редактирование записи");
        System.out.println("3. Вывод данных о всех студентах ");
        System.out.println("4. Вывод данных о всех студентах из группы");
        System.out.println("5. Вывод данных о всех студентах с определенным номером");
        System.out.println("6. Вывод студентов по оценкам");
        System.out.println("7. Вывод топа студентов");
        System.out.println("8. Вывод информации о поле студентов");
        System.out.println("9. Сохранить данные о студентах в файл");
        System.out.println("10. Вывод информации о преподавателе");
        System.out.println("11. Выход");
    }
    public static void printMenuForChange(){
        System.out.println("1. ФИО");
        System.out.println("2. Пол");
        System.out.println("3. Номер студента ");
        System.out.println("4. Номер группы");
        System.out.println("5. Оценки за экзамены");
        System.out.println("6. Оценки за тесты");
        System.out.println("7. Выход");
    }

}
