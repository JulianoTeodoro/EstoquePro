package estacio.API.EstoquePro.models;

public class Stock {
    private int id;
    private String name;
    private float priceUnitary;
    private int quantity;
    private float priceBox;
    private boolean status;

    public Stock() {}

    public Stock(int id, String name, float priceUnitary, int quantity, boolean status) {
        this.id = id;
        this.name = name;
        this.priceUnitary = priceUnitary;
        this.quantity = quantity;
        this.status = status;
        this.priceBox = priceUnitary * quantity; // Calculado automaticamente
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriceUnitary() {
        return priceUnitary;
    }

    public void setPriceUnitary(float priceUnitary) {
        this.priceUnitary = priceUnitary;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPriceBox() {
        return priceBox;
    }

    public void setPriceBox(float priceBox) {
        this.priceBox = priceBox;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
   
}
