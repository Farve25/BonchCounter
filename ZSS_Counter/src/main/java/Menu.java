import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu {
    private static int flag;

    public static void menu() throws Exception {
        System.out.println("Меню:");
        System.out.println("1. Проверка работы");
        System.out.println("2. Создание отчета");
        System.out.println("3. Регистрация студента");
        System.out.println("4. Проверка регистрации");
        System.out.println("0. Выход");

        boolean stop = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        switch (Integer.parseInt(in.readLine())) {
            case 1:
                System.out.println("Проверка работоспособности");
                setFlag(1);
                readCard();
                break;

            case 2:/*
                    System.out.println("Имя преподователя:");
                    String nameOfPrepod = in.readLine();
                    System.out.println("Пердмет:");
                    String subject = in.readLine();
                    System.out.println("Форма занятия:");
                    String form0 = in.readLine();
                    System.out.println("Дата:");
                    String date = in.readLine();*/
                break;

            case 3:
                System.out.println("Регистрация студента:");
                setFlag(3);
                readCard();
                break;

            case 4:
                System.out.println("Поверки регистрации");
                setFlag(4);
                HardReader.startReading();
                break;

            case 0://Выход
                System.out.println("Завершение работы");
                PortFinder.setStop();
                StudentDB.closeDB();
                break;

            default:
                break;
        }
    }

    static void readCard() throws Exception{
        if (PortFinder.getStatus() != false) {
            System.out.println("Приподнесите карту к считывателю.");
            HardReader.startReading();
        } else {
            System.out.println("Считыватель не найден!!! Проверьте подключение.");
            menu();
        }
    }

    static void setFlag(int newFlag) {
        flag = newFlag;
    }

    static int getFlag() {
        return flag;
    }
}
