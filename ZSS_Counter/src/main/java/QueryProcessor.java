import org.apache.commons.collections4.ArrayStack;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QueryProcessor {
    static void process(String cardId){
        switch(Menu.getFlag()){
            case 1://Проверка работоспособности
                //Вывести id карты на экран
                System.out.println(cardId);
                break;
            case 2://Создание отчета

                break;
            case 3://Регистрация студента
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Введите ФИО:");
                    String name = in.readLine();
                    System.out.println("Введите группу:");
                    String group = in.readLine();

                    StudentDB.add(cardId, name, group);

                    System.out.println("Регистрация завершена");
                } catch (IOException ioe) {
                    System.out.println("Не удалось зарегистрировать");
                }
                break;
            case 4://Проверка регистрации студента
                ArrayList<String> result = StudentDB.search(cardId);
                if (result.isEmpty()) {
                    System.out.println("Студент не зарегистрирован");
                } else {
                    System.out.println("Имя: " + result.get(0));
                    System.out.println("Группа: " + result.get(1));
                }
                break;
            case 5://Редактирование данных студента

                break;
            default:
                System.out.println("хуй");
                break;
        }
    }
}

