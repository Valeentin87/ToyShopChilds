package shopToys.model;

/**
 * Класс игрушка
 * */
public class Toy {
    public String name;
    public String type;
    public long id;
    double price;

    /**
     * Конструктор
     * @param id поле: уникальный номер товара (игрушки)
     * @param name поле: название игрушки
     * @param type поле: тип игрушки
     * @param price поле: цена товара
     */
    public Toy(long id, String name, String type, double price){
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }



    // геттеры
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    // сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
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
        return id == toy.id;
    }

}
