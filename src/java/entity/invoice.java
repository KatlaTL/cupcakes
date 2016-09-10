package entity;

public class invoice {
    private String topping;
    private String bottom;
    private double totalPrice;

    public invoice(String topping, String bottom, double totalPrice) {
        this.topping = topping;
        this.bottom = bottom;
        this.totalPrice = totalPrice;
    }

    public invoice(String topping, String bottom) {
        this.topping = topping;
        this.bottom = bottom;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
