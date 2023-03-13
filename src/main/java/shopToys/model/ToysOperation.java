package shopToys.model;

import java.util.List;

public interface ToysOperation {
    List<String> readAllToys();

    List<String> readPrizeToys();

    void saveAllToys(List<String> lines);

    void saveNewToy(String newLine);
}
