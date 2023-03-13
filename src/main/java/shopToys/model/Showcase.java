package shopToys.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface Showcase {
    List<Toy> getAllToys();
    List<Toy> getPrizeToys();
    ArrayList<String> fortuneUsers();
    int CreateToy(Toy toy);
    //void NewVersionNote(Note note);

    void delToyShowcase(String id);

    void delToyShowcase();

    ArrayList<Toy> PrizeBasket();
    void savePrizeBasket(List<Toy> toys);

    Toy returnToyShowcase(String uinToy,String numberToys);

    List<Toy> getBasket(ArrayList<String> allUin, ArrayList<String> allNumbers);

    String getUinOrder();

    ArrayList<FortuneToy> castPrizeBasket();


}
