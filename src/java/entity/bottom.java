package entity;

public class bottom {
    private int id;
    private String bottom;
    private double price;

    public bottom(int id, String bottom, double price) {
        this.id = id;
        this.bottom = bottom;
        this.price = price;
    }

    public bottom(String bottom, double price) {
        this.bottom = bottom;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
