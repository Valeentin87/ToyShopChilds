package shopToys.model;

public class FortuneToy extends Toy{
    public String fortuneUser;

    public FortuneToy(int id, long uin, String name, String type, double price, int quantity, String fortuneUser) {
        super(id, uin, name, type, price, quantity);
        this.fortuneUser = fortuneUser;
    }

    public FortuneToy(String fortuneUser) {
        this.fortuneUser = fortuneUser;
    }

    public String getFortuneUser() {
        return fortuneUser;
    }

    public void setFortuneUser(String fortuneUser) {
        this.fortuneUser = fortuneUser;
    }

    @Override
    public String toString() {
        return "Покупатель, оформивший заказ под номером: "+ fortuneUser+" выиграл " + super.toString() + '\'' ;
    }
}
