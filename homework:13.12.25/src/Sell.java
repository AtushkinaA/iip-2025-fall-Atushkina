import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Sell {
    private int id;
    private String productName;
    private int quantity;
    private double totalPrice;
    private LocalDate saleDate;

    public Sell(int id, String productName, int quantity, double totalPrice, LocalDate saleDate) {
        this.id = id;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.saleDate = saleDate;
    }
    public int getId() {
        return id;
    }
    public String getProductName() {
        return productName;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public LocalDate getSaleDate() {
        return saleDate;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("ID:" + id);
        System.out.println("ProductName:" + productName);
        System.out.println("TotalPrice:" + totalPrice);
        System.out.println("Quantity:" + quantity);
        System.out.println("Date" + saleDate.format(formatter));
        return null;
    }
}
