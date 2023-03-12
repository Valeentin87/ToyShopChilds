package shopToys.controller;

import shopToys.model.Showcase;
import shopToys.model.Toy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private final Showcase showcase;

    public UserController(Showcase showcase) {
        this.showcase = showcase;

    }

    public void saveToy(Toy toy) {
        showcase.CreateToy(toy);
    }
/*
    public void updateNote(Note note) {
        repository.NewVersionNote(note);
    }
*/
    public void deleteToy(String uin) {
        showcase.delToyShowcase(uin);
    }

    public List<Toy> getUserBasket(ArrayList<String> allUin, ArrayList<String> allNumbers){
        List<Toy> userBasket = new ArrayList<>(showcase.getBasket(allUin,allNumbers));
        try (FileWriter writer = new FileWriter("userBasket.txt", false)) {
            double pricePosition = 0;
            double priceBasket =0;
            for (int i=0;i<userBasket.size();i++) {
                // запись всей строки
                pricePosition = userBasket.get(i).getPrice()*userBasket.get(i).getQuantity();
                priceBasket+=pricePosition;
                writer.write(String.valueOf(i+1)+".\t\t\t"+userBasket.get(i).getName()+".\t\t\t"+String.valueOf(userBasket.get(i).getQuantity())+" штуки "+
                        ".\t\t\tНа сумму: " + String.valueOf(pricePosition));
                // запись по символам
                writer.append('\n');
            }
            writer.write("Общая стоимость заказа составляет - "+String.valueOf(priceBasket));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return userBasket;
    }

    public Toy putInBasket(String uinToy, String numberToys) {
       return showcase.returnToyShowcase(uinToy,numberToys);

    }


    public void viewAllТoys() {
        List<Toy> toys = showcase.getAllToys();
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }

    public void viewAllТoys(ArrayList<Toy> toys) {
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }


    public  void CreatePrizeBasket(){
        ArrayList<Toy> prizeToys = showcase.PrizeBasket();
        showcase.savePrizeBasket(prizeToys);
        viewAllТoys(prizeToys);
    }


    public Toy readToy(String name) throws Exception {
        List<Toy> toys = showcase.getAllToys();
        for (Toy toy : toys) {
            if (toy.getName().equals(name)) {
                return toy;
            }
        }
        throw new Exception("Toy not found");
    }

    public String getNumberOrder(){
        return showcase.getUinOrder();
    }

}
