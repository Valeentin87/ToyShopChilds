package shopToys.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public void delToyShowcase(String uin) {
        long uinDel = Long.parseLong(uin);
        long base;
        List<Toy> toys = getAllToys();
        for(int i = 0;i<toys.size();i++){
            base = toys.get(i).getUin();
            if(base == uinDel){
                toys.remove(toys.get(i));
            }


            /*
            else if(base > id){
                note.setId(String.valueOf(base-1));
            }

             */
        }
        List<String> lines = new ArrayList<>();
        for (Toy toy: toys) {
            lines.add(mapper.map(toy));
        }
        toysOperation.saveAllToys(lines);
    }

    @Override
    public ArrayList<Toy> PrizeBasket() {
        ArrayList<Toy> allToys = new ArrayList<>(getAllToys());
        ArrayList<Toy> allToysInString = new ArrayList<>();

        ArrayList<Toy> prizeToys = new ArrayList<>();
        int allToysNumber = 0;
        int toyCount;
        for(int i = 0; i<allToys.size(); i++){
            toyCount = allToys.get(i).getQuantity();
            allToysNumber+=toyCount;
            for(int j=0;j<toyCount;j++){
                allToysInString.add(allToys.get(i));
            }
        }
        int prizeNumber;
        if (allToysNumber>100) prizeNumber=allToysNumber/20;
        else prizeNumber=allToysNumber/10;
        Random rnd = new Random();
        int rndNumber;
        for (int j = 0; j<prizeNumber;j++){
            rndNumber = rnd.nextInt(0,allToysInString.size());
            prizeToys.add(allToysInString.get(rndNumber));
            allToysInString.remove(rndNumber);
        }
        return prizeToys;
    }
    @Override
    public void savePrizeBasket(List<Toy> toys) {
        ArrayList<String> lines = new ArrayList<>();
        for(int i = 0; i<toys.size();i++){
            toys.get(i).setQuantity(1);
            lines.add(mapper.map(toys.get(i)));
        }
        try (FileWriter writer = new FileWriter("prizeBasket.txt", false)) {
            for (String line : lines) {
                // запись всей строки
                writer.write(line);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
