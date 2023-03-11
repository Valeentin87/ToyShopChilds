package shopToys.model;

/**
 * Класс игрушка
 * */
public class Toy {
    public String name;
    public String type;
    public int id;
    public long uin;
    double price;

    /**
     * Конструктор
     * @param uin поле: уникальный номер товара (штрих-код)
     * @param id поле: порядковый номер в списке игрушек
     * @param name поле: название игрушки
     * @param type поле: тип игрушки
     * @param price поле: цена товара
     */
    public Toy(int id, long uin, String name, String type, double price){
        this.uin = uin;
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }



    // геттеры
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public double getPrice() {
        return price;
    }

    // сеттеры
    public void setName(String name) {
        this.name = name;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s: %s Цена: %f",type,name,price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return uin == toy.uin;
    }

    public int getId() {
        return id;
    }
}
