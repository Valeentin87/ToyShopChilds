package shopToys.controller;

import shopToys.model.Showcase;
import shopToys.model.Toy;

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

    public void putInBasket(String uinToy, String numberToys) {
        showcase.returnToyShowcase(uinToy,numberToys);

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

}
