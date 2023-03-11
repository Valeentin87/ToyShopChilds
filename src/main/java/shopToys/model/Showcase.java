package shopToys.model;

import java.util.ArrayList;
import java.util.List;

public interface Showcase {
    List<Toy> getAllToys();
    int CreateToy(Toy toy);
    //void NewVersionNote(Note note);

    void delToyShowcase(String id);

    ArrayList<Toy> PrizeBasket();
    void savePrizeBasket(List<Toy> toys);
}
