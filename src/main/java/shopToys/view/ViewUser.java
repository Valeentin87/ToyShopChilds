package shopToys.view;

import shopToys.controller.UserController;
import shopToys.model.Toy;

import java.util.Scanner;

public class ViewUser {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt(ANSI_YELLOW + "************** Добро пожаловать в записную книжку *************\n" + ANSI_RESET + ANSI_BLUE +
                    "Введите команду из нижеперечисленных (регистр не важен):" + ANSI_RESET + "\nДобавить заметку:\n\t\t\t\t - " + ANSI_RED + "CREATE" + ANSI_RESET + "\nПрочитать заметку:\n\t\t\t\t" +
                    " -" + ANSI_RED + " READ" + ANSI_RESET + "\nУдалить заметку:\n\t\t\t\t -" + ANSI_RED + " DELETE" + ANSI_RESET + "\n" +
                    "Подготовить к отправке:\n\t\t\t\t -" + ANSI_RED + " SAVE\n" + ANSI_RESET +
                    "Выйти:\n\t\t\t\t - " + ANSI_RED + "EXIT\n" + ANSI_RESET +
                    "Отредактировать заметку:\n\t\t\t\t -" + ANSI_RED + " UPDATE\n" + ANSI_RESET + ANSI_BLUE +
                    "Поле для ввода команды: " + ANSI_RESET);
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String id = prompt(ANSI_BLUE + "Порядковый номер в списке товаров: " + ANSI_RESET);
                    String uin = prompt(ANSI_BLUE + "Уникальный номер(штрих-код) товара: " + ANSI_RESET);
                    String name = prompt(ANSI_BLUE + "Название игрушки: " + ANSI_RESET);
                    String type = prompt(ANSI_BLUE + "Тип игрушки: " + ANSI_BLUE);
                    String price = prompt(ANSI_BLUE + "Цена игрушки: " + ANSI_BLUE);
                    userController.saveToy(new Toy(Integer.parseInt(id), Long.parseLong(uin), name, type, Double.parseDouble(price)));
                    break;
                case READ:
                    String uIn = prompt(ANSI_BLUE + "Игрушку под каким UIN вы хотите посмотреть: " + ANSI_RESET);
                    try {
                        Toy toy = userController.readToy(Long.parseLong(uIn));
                        System.out.println(toy);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

/*
                case UPDATE:
                    System.out.println(ANSI_BLUE+"Ниже указан список имеющихся заметок: "+ANSI_RESET);
                    userController.viewAllNotes();
                    String id1 = prompt(ANSI_BLUE+"наберите порядковый номер какую хотите изменить: "+ANSI_RESET);
                    try {
                        Note note = userController.readNote(id1);
                        System.out.println(note);
                        userController.updateNote(note);
                        System.out.println(ANSI_RED+"\t\t\t\t\t\t\t\t\tЗаметка отредактирована"+ANSI_RESET);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

 */
                case DELETE:
                    System.out.println(ANSI_BLUE + "Ниже указан список имеющихся товаров: " + ANSI_RESET);
                    userController.viewAllТoys();
                    String id2 = prompt(ANSI_BLUE + "наберите UIN товара, который хотите удалить: " + ANSI_RESET);
                    try {
                        userController.deleteToy(id2);
                        System.out.println(ANSI_RED + "Товар с UIN: " + id2 + " успешно удален c витрины" + ANSI_RESET);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    //break;

            }
        }
    }
    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}

