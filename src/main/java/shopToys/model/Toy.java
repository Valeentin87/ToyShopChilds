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

    int quantity;

    /**
     * Конструктор
     * @param uin поле: уникальный номер товара (штрих-код)
     * @param id поле: порядковый номер в списке игрушек
     * @param name поле: название игрушки
     * @param type поле: тип игрушки
     * @param price поле: цена товара
     * @param quantity поле: количество товара данного наименования
     */
    public Toy(int id, long uin, String name, String type, double price, int quantity){
        this.uin = uin;
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public Toy() {

    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        return String.format("UIN: %8d Тип: %25s: Название: %20s Цена: %13.2f Количество: %4d",uin, type,name,price,quantity);
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
