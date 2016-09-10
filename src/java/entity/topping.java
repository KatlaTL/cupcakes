package entity;

public class topping {
    private int id;
    private String topping;
    private double price;

    public topping(int id, String topping, double price) {
        this.id = id;
        this.topping = topping;
        this.price = price;
    }

    public topping(String topping, double price) {
        this.topping = topping;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
