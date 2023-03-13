package shopToys.controller;

import shopToys.model.FortuneToy;
import shopToys.model.Showcase;
import shopToys.model.Toy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class UserController
 * Связующее звено между блоками model и view
 */
public class UserController {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private final Showcase showcase;

    /**
     * Конструктор
     * @param showcase поле экземпляр интерфейса Showcase
     */
    public UserController(Showcase showcase) {
        this.showcase = showcase;

    }

    /**
     * Связующий метод для добавления новой игрушки
     * @param toy передаваемый в параметр экземпляр класса Toy
     */
    public void saveToy(Toy toy) {
        showcase.CreateToy(toy);
    }

    /**
     * Связующий метод для удаления игрушки
     * @param uin идентефикатор удаляемой игрушки
     */
    public void deleteToy(String uin) {
        showcase.delToyShowcase(uin);
    }

    /**
     * Метод, рассчитывающий заказ пользователя
     * @param allUin список идентефикаторов заказанных игрушек
     * @param allNumbers количество заказанного наименования игрушки
     * @return Список игрушек, оформленных в заказ
     */
    public List<Toy> getUserBasket(ArrayList<String> allUin, ArrayList<String> allNumbers){
        List<Toy> userBasket = new ArrayList<>(showcase.getBasket(allUin,allNumbers));
        try (FileWriter writer = new FileWriter("userBasket.txt", false)) {
            double pricePosition = 0;
            double priceBasket =0;

            for (int i=0;i<userBasket.size();i++) {
                pricePosition = userBasket.get(i).getPrice()*userBasket.get(i).getQuantity();
                priceBasket+=pricePosition;
                writer.write(String.valueOf(i+1)+";"+userBasket.get(i).getName()+";"+String.valueOf(userBasket.get(i).getQuantity())+";штуки"+
                        ";На сумму:" + String.valueOf(pricePosition));
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return userBasket;
    }

    public Toy putInBasket(String uinToy, String numberToys) {
       return showcase.returnToyShowcase(uinToy,numberToys);

    }

    /**
     * Связующий метод для показа всех игрушек имеющихся в магазине
     */
    public void viewAllToys() {
        List<Toy> toys = showcase.getAllToys();
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }

    /**
     * Метод позволяющий отобразить пользователю конкретный список игрушек
     * @param toys список отображаемых игрушек
     */
    public void viewТoys(ArrayList<Toy> toys) {
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }

    /**
     * Связующий метод для формирования призовой корзины
     */
    public  void CreatePrizeBasket(){
        ArrayList<Toy> prizeToys = showcase.PrizeBasket();
        showcase.savePrizeBasket(prizeToys);
        viewТoys(prizeToys);
    }

    /**
     * Метод, позволяющий найти игрушку в магазине по имени
     * @param name имя отыскиваемой игрушки
     * @return экземпляр искомой игрушки
     * @throws Exception выдает исключение если такой игрушки нет в магазине
     */
    public Toy readToy(String name) throws Exception {
        List<Toy> toys = showcase.getAllToys();
        for (Toy toy : toys) {
            if (toy.getName().equals(name)) {
                return toy;
            }
        }
        throw new Exception("Toy not found");
    }

    /**
     * Связующий метод, позволяющий получить уникальный номер оформленного заказа
     * @return уникальный номер заказа типа String
     */
    public String getNumberOrder(){
        return showcase.getUinOrder();
    }

    /**
     * Связующий метод, позволяющий удалить из магазина оформленный в заказ товар
     */
    public void delFromShowcase(){
         showcase.delToyShowcase();
    }

    /**
     * Связующий метод, позволяющий разыграть призовую корзину
     * @return список победителей
     */
    public ArrayList<FortuneToy> castPrizeBasket(){return showcase.castPrizeBasket();}

    /**
     * Связующий метод, позволяющий отобразить список победителей
     */
    public void showFortunes(){
        showcase.showFortunes();
    }
}
