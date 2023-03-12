package shopToys.model;

import java.io.*;
import java.util.*;

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
        }
        List<String> lines = new ArrayList<>();
        for (Toy toy: toys) {
            lines.add(mapper.map(toy));
        }
        toysOperation.saveAllToys(lines);
    }

    public void delToyShowcase(String name, String quantity) {
        String  base;
        int newQuantity = 0;
        List<Toy> toys = getAllToys();
        for(int i = 0;i<toys.size();i++){
            base = toys.get(i).getName();
            if((base.equals(name))){
                if (String.valueOf(toys.get(i).getQuantity()).equals(quantity)){
                    toys.remove(toys.get(i));
                }
                else {
                    newQuantity = toys.get(i).getQuantity();
                    newQuantity-=Integer.parseInt(quantity.trim());
                    toys.get(i).setQuantity(newQuantity);
                }
            }
        List<String> lines = new ArrayList<>();
        for (Toy toy: toys) {
            lines.add(mapper.map(toy));
        }
        toysOperation.saveAllToys(lines);
    }
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

    @Override
    public Toy returnToyShowcase(String uinToy, String numberToys) {
        ArrayList<Toy> allToys = new ArrayList<>(getAllToys());
        Toy userBasket = new Toy();
        boolean flag = false;
        for (int i=0; i<allToys.size();i++){
            if (Long.parseLong(uinToy.trim())==allToys.get(i).getUin()){
                if (Integer.parseInt(numberToys.trim())<=allToys.get(i).getQuantity()) {
                    userBasket.setId(1);
                    userBasket.setUin(allToys.get(i).getUin());
                    userBasket.setName(allToys.get(i).getName());
                    userBasket.setType(allToys.get(i).getType());
                    userBasket.setPrice(allToys.get(i).getPrice());
                    userBasket.setQuantity(Integer.parseInt(numberToys.trim()));

                    break;
                }
                else {
                    flag = true;
                    System.out.printf("Ошибка формирования заказа: Товар с UIN: %s в наличии только %s штук\n ",uinToy,allToys.get(i).getQuantity());
                }
            }

        }
        if ((userBasket.getQuantity() == 0) & !flag) System.out.printf("Ошибка формирования заказа: Товар с UIN: %s в магазине отсутствует\n ",uinToy);
        return userBasket;
    }
    @Override
    public List<Toy> getBasket(ArrayList<String> allUin, ArrayList<String> allNumbers){
        List<Toy> basketUser = new ArrayList<>();
        for(int i=0;i<allUin.size();i++){
            basketUser.add(returnToyShowcase(allUin.get(i),allNumbers.get(i)));
        }
        return basketUser;
    }

    @Override
    public String getUinOrder() {
        Random rndUinOrder = new Random();
        int uinOrder = 0;
        String uinOrderStr = null;
        Set<String> lastUinOrders = new HashSet<>();
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("uinOrders.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lastUinOrders.add(line.trim());
            }
            while (line != null) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line != null) {
                    lastUinOrders.add(line.trim());
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        do{
            uinOrder = rndUinOrder.nextInt(1000);
            uinOrderStr = Integer.toString(uinOrder);
        }
        while (!lastUinOrders.add(uinOrderStr));
        try (FileWriter writer = new FileWriter("uinOrders.txt", false)) {
                for (String line : lastUinOrders) {
                // запись всей строки
                writer.write(line);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return uinOrderStr;

    }
}
