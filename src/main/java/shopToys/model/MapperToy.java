package shopToys.model;

/**
 * Класс MapperToy
 * Позволяет парсить строку в игрушку и экзкмпляр класса Toy в строку перегрузкой метода map
 */
public class MapperToy {

        public static String map(Toy toy) {
            return String.format("%-3d,%-6d,%-25s,%-25s,%-12f,%-3d", toy.getId(),toy.getUin(), toy.getName(), toy.getType(),
                                                                                toy.getPrice(),toy.getQuantity());
        }

        public Toy map(String line) {
            String[] lines = line.split(",");
            return new Toy(Integer.parseInt(lines[0]), Long.parseLong(lines[1]), lines[2], lines[3], Double.parseDouble(lines[4]),
                    Integer.parseInt(lines[5]));
        }
    }


