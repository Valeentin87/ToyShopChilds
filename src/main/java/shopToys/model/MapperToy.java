package shopToys.model;

/**
 * Класс MapperToy
 * Позволяет парсить строку в игрушку и экзкмпляр класса Toy в строку перегрузкой метода map
 */
public class MapperToy {

        public static String map(Toy toy) {
            double priceLast = toy.getPrice();
            String s;
            s = String.format("%f",priceLast);
            String[] spl = s.split(",");
            String newFormatPrice = spl[0].trim()+"."+spl[1].trim();
            return String.format("%3d|%6d|%25s|%25s|%12s|%3d", toy.getId(),toy.getUin(), toy.getName(), toy.getType(),
                                                                                newFormatPrice,toy.getQuantity());
        }

        public Toy map(String line) {
            String[] lines = line.split("\\|");
            return new Toy(Integer.parseInt(lines[0].trim()), Long.parseLong(lines[1].trim()), lines[2].trim(), lines[3].trim(), Double.parseDouble(lines[4].trim()),
                    Integer.parseInt(lines[5].trim()));
        }
    }


