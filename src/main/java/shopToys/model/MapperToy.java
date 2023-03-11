package shopToys.model;

/**
 * Класс MapperToy
 * Позволяет парсить строку в игрушку и экзкмпляр класса Toy в строку перегрузкой метода map
 */
public class MapperToy {

        public static String map(Toy toy) {
            return String.format("%d,%s,%s,%s", toy.getId(), toy.getName(), toy.getType(), toy.getPrice());
        }

        public Toy map(String line) {
            String[] lines = line.split(";");
            return new Toy(Long.parseLong(lines[0]), lines[1], lines[2], Double.parseDouble(lines[3]));
        }
    }


