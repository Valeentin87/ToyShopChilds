package shopToys;

import shopToys.controller.UserController;
import shopToys.model.*;
import shopToys.view.ViewUser;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ToysOperation toysOperation = new ToysOperationImpl("showcase.txt");
        Showcase showcase = new ShowcaseImpl(toysOperation);

        UserController controller = new UserController(showcase);
        ViewUser view = new ViewUser(controller);
        view.run();


/*
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> quantitys = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
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
        System.out.println(names);
        System.out.println(quantitys);
        test.add("4");
        test.add("1");
        System.out.println(test);

 */
        /*
        Toy toy = new Toy(2,23456,"Ball","Мячи",2345.45,23);
        double priceLast = toy.getPrice();
        String s;
        s = String.format("%f",priceLast);
        String[] spl = s.split(",");
        //String newFormatPrice = spl[0].trim()+"."+spl[1].trim();
        for (String it:spl) {
            System.out.println(it);
        }
        //System.out.println(newFormatPrice);
        */

    }
}