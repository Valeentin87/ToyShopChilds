package shopToys.model;

import java.util.List;

public interface Showcase {
    List<Toy> getAllToys();
    int CreateToy(Toy toy);
    //void NewVersionNote(Note note);

    void delToyShowcase(String id);
}
