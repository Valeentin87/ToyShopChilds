package shopToys.model;

import java.util.ArrayList;
import java.util.List;

public class ShowcaseImpl implements  Showcase {
    private MapperToy mapper = new MapperToy();
    private ToysOperation toysOperation;

    public ShowcaseImpl(ToysOperation toysOperation) {
        this.toysOperation = toysOperation;
    }

    @Override
    public List<Toy> getAllToys() {
        List<String> lines = toysOperation.readAllToys();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public int CreateToy(Toy toy) {
        List<Toy> toys = getAllToys();
        int max = 0;
        for (Toy item : toys) {
            int id = item.getId();
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        toy.setId(newId);
        toys.add(toy);
        List<String> lines = new ArrayList<>();
        for (Toy item: toys) {
            lines.add(mapper.map(item));
        }
        toysOperation.saveAllToys(lines);
        return newId;
    }

    @Override
    public void delToyShowcase(String id) {

    }
}
