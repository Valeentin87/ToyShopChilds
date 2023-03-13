package shopToys.view;

import shopToys.controller.UserController;
import shopToys.model.FortuneToy;
import shopToys.model.Toy;

import java.util.ArrayList;
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
        Commands com1 = Commands.NONE;

        while (true) {
            String command1 = prompt(ANSI_YELLOW + "************** Добро пожаловать в интернет магазин HappyChildren *************\n" + ANSI_RESET +
                    "Если вы являетесь менеджером введите команду (регистр не важен):" + ANSI_RESET + "\n\t\t\t\t\t\t\t - " + ANSI_RED + "MANAGER" + ANSI_RESET +
                    "\n\nЕсли вы посетитель магазина введите (регистр не важен): \n\t\t\t\t\t\t\t" +
                    " -" + ANSI_RED + " USER" + ANSI_RESET + "\n\nЕсли хотите выйти введите (регистр не важен): \n\t\t\t\t\t\t\t" + " -" + ANSI_RED + " EXIT" + ANSI_RESET +
                    ANSI_BLUE + "\nПоле для ввода команды: " + ANSI_RESET);
            com1 = Commands.valueOf(command1.toUpperCase());
            if (com1 == Commands.EXIT) return;
            switch (com1) {
                case MANAGER:
                    while (true) {
                        String commandManager = prompt(ANSI_RED + "***** Введите пароль ******\n" + ANSI_RESET +
                                ANSI_BLUE + "Поле для ввода пароля (для проверки преподавателем GB пароль - shop): " + ANSI_RESET);
                        if (commandManager.equals("shop")) {
                            commandManager = prompt(ANSI_YELLOW + "************** Уважаемый сотрудник магазина HappyChildren" +
                                    " для работы с каталогом товаров выберите команду *************\n" + ANSI_RESET +
                                    ANSI_RED+"не забудте сформировать призовую корзину и разыграть её!!!"+ANSI_RESET+
                                    "локация файлов:\n" +
                                    "showcase.txt   -  каталог имеющихся в магазине товаров (динамически изменяется при совершении заказов)\n"+
                                    "prizeBasket.txt   - содержимое призовой корзины, на основании количества имеющихся товаров\n"+
                                    "uinOrders.txt  -  список уникальных номеров оформленных заказов\n"+
                                    "userBasket.txt  -  информация о последнем оформленном заказе\n"+
                                    "fortunes.txt  - список победителей розыгрыша\n"+
                                    ANSI_BLUE +
                                    "Введите команду из нижеперечисленных (регистр не важен):" + ANSI_RESET + "\nДобавить товар на витрину:\n\t\t\t\t - " + ANSI_RED + "CREATE" + ANSI_RESET + "\nНайти товар по названию: \n\t\t\t\t" +
                                    " -" + ANSI_RED + " READ" + ANSI_RESET + "\nСформировать призовую корзину: \n\t\t\t\t" + ANSI_RED + "PRIZEBASKET\n" + ANSI_RESET +"\nРазыграть призовую корзину: \n\t\t\t\t" + ANSI_RED + "CASTBASKET\n" + ANSI_RESET + "\nУбрать товар с витрины:\n\t\t\t\t -" + ANSI_RED + " DELETE" + ANSI_RESET + "\n" +
                                    "Выйти:\n\t\t\t\t - " + ANSI_RED + "EXIT\n" + ANSI_RESET + ANSI_BLUE + "Поле для ввода команды: " + ANSI_RESET);
                            com1 = Commands.valueOf(commandManager.toUpperCase());
                            switch (com1) {
                                case CREATE:
                                    String id = prompt(ANSI_BLUE + "Порядковый номер в списке товаров: " + ANSI_RESET);
                                    String uin = prompt(ANSI_BLUE + "Уникальный номер(штрих-код) товара: " + ANSI_RESET);
                                    String name = prompt(ANSI_BLUE + "Название игрушки: " + ANSI_RESET);
                                    String type = prompt(ANSI_BLUE + "Тип игрушки: " + ANSI_BLUE);
                                    String price = prompt(ANSI_BLUE + "Цена игрушки: " + ANSI_BLUE);
                                    String quantity = prompt(ANSI_BLUE + "Количество указанного товара: " + ANSI_BLUE);
                                    userController.saveToy(new Toy(Integer.parseInt(id.trim()), Long.parseLong(uin.trim()), name.trim(), type.trim(),
                                            Double.parseDouble(price.trim()), Integer.parseInt(quantity.trim())));
                                    continue;
                                case READ:
                                    String uIn = prompt(ANSI_BLUE + "Введите название игрушки, которую ищете: " + ANSI_RESET);
                                    try {
                                        Toy toy = userController.readToy(uIn.trim());
                                        System.out.println(toy);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    continue;
                                case DELETE:
                                    System.out.println(ANSI_BLUE + "Ниже указан список имеющихся товаров: " + ANSI_RESET);
                                    userController.viewAllToys();
                                    String id2 = prompt(ANSI_BLUE + "наберите UIN товара, который хотите удалить: " + ANSI_RESET);
                                    try {
                                        userController.deleteToy(id2);
                                        System.out.println(ANSI_RED + "Товар с UIN: " + id2 + " успешно удален c витрины" + ANSI_RESET);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    continue;
                                case PRIZEBASKET:
                                    System.out.println(ANSI_BLUE + "Сформирована корзина призовых товаров: " + ANSI_RESET);
                                    userController.CreatePrizeBasket();
                                    continue;
                                case CASTBASKET:
                                    System.out.println(ANSI_BLUE + "Представляем список пользователей, которые выиграли призовые игрушки: " + ANSI_RESET);
                                    for (FortuneToy user:userController.castPrizeBasket())
                                    {
                                        System.out.println(user);
                                    }
                                    continue;
                            }
                        }
                        System.out.println(ANSI_RED+"ПАРОЛЬ НЕ ВЕРНЫЙ - ПЕРЕВОЖУ ВАС В РЕЖИМ ОБЫЧНОГО ПОСЕТИТЕЛЯ!");
                        break;
                    }
                case USER:
                    while (true) {
                            String commandManager = prompt(ANSI_YELLOW + "************** ДОБРО ПОЖАЛОВАТЬ в интернет магазин HappyChildren" +
                                    " для работы с каталогом товаров выберите команду *************\n" + ANSI_RESET + ANSI_BLUE +
                                    "Введите команду из нижеперечисленных (регистр не важен):" + ANSI_RESET + "\nПросмотреть весь имеющийся ассортимент:\n\t\t\t\t - " + ANSI_RED + "VIEW" + ANSI_RESET + "\nНайти товар по названию: \n\t\t\t\t" +
                                    " -" + ANSI_RED + " READ" + ANSI_RESET + "\nОформить заказ: \n\t\t\t\t" +" -"+ ANSI_RED + "  PUT" + ANSI_RESET + "\nПосмотреть победителей розыгрыша призовой корзины \n\t\t\t\t -" + ANSI_RED + " FORTUNE" + ANSI_RESET+
                                    "\nПосмотреть информацию о магазине\n\t\t\t\t - " + ANSI_RED + "HELP\n" + ANSI_RESET+"\nВернуться назад:\n\t\t\t\t - " + ANSI_RED + "BACK\n" + ANSI_RESET  + ANSI_BLUE + "Поле для ввода команды: " + ANSI_RESET);
                            com1 = Commands.valueOf(commandManager.toUpperCase());
                            if (com1 == Commands.BACK) break;
                            switch (com1) {
                                case VIEW:
                                    System.out.println(ANSI_BLUE + "Следующие товары имеются в магазине: " + ANSI_RESET);
                                    userController.viewAllToys();
                                    continue;
                                case READ:
                                    String uIn = prompt(ANSI_BLUE + "Введите название игрушки, которую ищете: " + ANSI_RESET);
                                    try {
                                        Toy toy = userController.readToy(uIn.trim());
                                        System.out.println(toy);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    continue;

                                case PUT:
                                    userController.viewAllToys();
                                    ArrayList<String> basketUIN = new ArrayList<>();
                                    ArrayList<String> basketNumbers = new ArrayList<>();
                                    String endBasket = " ";
                                    do {
                                        String uinToy = prompt(ANSI_BLUE + "Наберите UIN товара, который хотите добавить в заказ: \n " + ANSI_RESET);
                                        String numberToys = prompt(ANSI_BLUE + "Введите количество товара сколько хотите приобрести: \n" + ANSI_RESET);
                                        basketUIN.add(uinToy);
                                        basketNumbers.add(numberToys);
                                        endBasket = prompt("Вы завершили формировать заказ (Да/Нет)?\nПоле для ввода: ");
                                    }
                                    while (!endBasket.toUpperCase().equals("ДА"));
                                    try {
                                        userController.getUserBasket(basketUIN,basketNumbers);
                                        userController.delFromShowcase();
                                        System.out.printf(ANSI_RED + "\n сформирован заказ под номером " + userController.getNumberOrder()+ "\n содержимое в файле userBasket.txt\n Оплатить заказ необходимо на пункте выдачи\n"+ ANSI_RESET);
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    continue;
                                case FORTUNE:
                                    System.out.println(ANSI_BLUE + "************ПОЛЬЗОВАТЕЛИ,ПОБЕДИВШИЕ В РОЗЫГРЫШЕ ПРИЗОВОЙ КОРЗИНЫ*************** " + ANSI_RESET);
                                    userController.showFortunes();
                                    continue;
                                case HELP:
                                    String help ="Уважаемый посетитель!!!\n" +
                                            "Каждый, кто оформил заказ хотя бы на одно наименование будет участвовать в конце недели в розыгрыше призовой корзины\n" +
                                            "При оформлении заказа вам будут отправлен его уникальный номер, по которому можно будет получить заказ на пункте выдачи\n" +
                                            "Список победителей можно проверить нажав кнопку FORTUNE\n" +
                                            "оформите зазаз и ждите ФОРТУНЫ!!!!";
                                    System.out.println(help);
                                    continue;
                            }

                        break;
                    }
            }
        }
    }

    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}

