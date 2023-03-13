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
    public List<Toy> getPrizeToys() {
        List<String> lines = toysOperation.readPrizeToys();
        List<Toy> toys = new ArrayList<>();
        for (String line : lines) {
            toys.add(mapper.map(line));
        }
        return toys;
    }

    @Override
    public ArrayList<String> fortuneUsers() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File("uinOrders.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  lines;
    }

    @Override
    public int CreateToy(Toy toy) {
        List<Toy> toys = getAllToys();
        int idCreate;
        idCreate = toy.getQuantity();
        toys.add(toy);
        List<String> lines = new ArrayList<>();
        for (Toy item: toys) {
            lines.add(mapper.map(item));
        }
        toysOperation.saveAllToys(lines);
        return idCreate;
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
    @Override
    public void delToyShowcase() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> quantitys = new ArrayList<>();
        try {
            File file = new File("userBasket.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null ) {
                String[] lines = line.split(";");
                names.add(lines[1].trim());
                quantitys.add(lines[2].trim());
            }
            while (line != null ) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line != null ) {
                    String[] lines = line.split(";");
                    names.add(lines[1].trim());
                    quantitys.add(lines[2].trim());
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> quant = new ArrayList<>();
        int value = 0;
        for(int m = 0; m<quantitys.size();m++){
            value = Integer.parseInt(quantitys.get(m).trim());
            quant.add(value);
        }



        for(int j=0;j<names.size();j++) {

            String base;
            int newQuantity = 0;
            ArrayList<Toy> allToys = new ArrayList<>(getAllToys());
            for (int i = 0; i < allToys.size(); i++) {
                base = allToys.get(i).getName().trim();
                if ((base.equals(names.get(j)))) {
                    if (allToys.get(i).getQuantity() == (int)(quant.get(j))) {
                        allToys.remove(allToys.get(i));
                    }

                    else {
                        newQuantity = allToys.get(i).getQuantity();
                        newQuantity -= (int)(quant.get(j));
                        allToys.get(i).setQuantity(newQuantity);
                    }

                }
                List<String> lines = new ArrayList<>();
                for (Toy toy : allToys) {
                    lines.add(mapper.map(toy));
                }
                toysOperation.saveAllToys(lines);
            }
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

    @Override
    public ArrayList<FortuneToy> castPrizeBasket() {
        ArrayList<Toy> prizeBasketToys = new ArrayList<>(getPrizeToys());
        ArrayList<String> allUsers = new ArrayList<>(fortuneUsers());
        ArrayList<FortuneToy> fortunesUsers = new ArrayList<>();
        Random rndUser = new Random();
        int numberFortuneUser;
        for(int i = 0; i<prizeBasketToys.size();i++){
            numberFortuneUser = rndUser.nextInt(allUsers.size());
            FortuneToy fortuneToy = new FortuneToy(allUsers.get(numberFortuneUser));
            fortuneToy.setId(prizeBasketToys.get(i).getId());
            fortuneToy.setName(prizeBasketToys.get(i).getName());
            fortuneToy.setType(prizeBasketToys.get(i).getType());
            fortuneToy.setQuantity(prizeBasketToys.get(i).getQuantity());
            fortuneToy.setPrice(prizeBasketToys.get(i).getPrice());
            fortuneToy.setUin(prizeBasketToys.get(i).getUin());
            fortunesUsers.add(fortuneToy);
            allUsers.remove(numberFortuneUser);
        }

        try (FileWriter writer = new FileWriter("fortunes.txt", false)) {


            for (int j = 0; j<fortunesUsers.size(); j++) {
                String s;
                s = String.format("Пользователь, оформивший заказ под номером: %s выиграл %s ПОЗДРАВЛЯЕМ!!!",
                        fortunesUsers.get(j).getFortuneUser(),fortunesUsers.get(j).getName());
                // запись всей строки
                writer.write(s);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return fortunesUsers;
    }

    @Override
    public void showFortunes() {
        try {
            File file = new File("fortunes.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                System.out.println(line);
            }
            while (line != null) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
