package shopToys.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ToyOperationImpl
 * Описывает действия над игрушками, имплементируя интерфейс ToyOperation
 */
public class ToysOperationImpl implements ToysOperation {
    public ToysOperationImpl(String fileName) {
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private String fileName;
    @Override
    public List<String> readAllToys() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("showcase.txt");
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
        return lines;
    }

    @Override
    public List<String> readPrizeToys() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File("prizeBasket.txt");
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
        return lines;
    }

    @Override
    public void saveAllToys(List<String> lines) {
        try (FileWriter writer = new FileWriter("showcase.txt", false)) {
            for (String line : lines) {
                writer.write(line);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void saveNewToy(String newLine) {

    }
}
