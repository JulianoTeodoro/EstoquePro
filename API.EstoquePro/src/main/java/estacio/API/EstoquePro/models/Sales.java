package estacio.API.EstoquePro.models;

import java.util.Date;

public class Sales {
    private int id;
    private Date dateSale;
    private int clientId;
    private String clientCpf;
    private int employeeId;
    private String employeeCpf;
    private int productId;
    private int quantity;
    private double valorTotal;

    // Construtor vazio
    public Sales() {}

    // Construtor completo
    public Sales(int id, Date dateSale, int clientId, String clientCpf, int employeeId, 
    		String employeeCpf, int productId, int quantity, double valorTotal) {
        this.id = id;
        this.dateSale = dateSale;
        this.clientId = clientId;
        this.clientCpf = clientCpf;
        this.employeeId = employeeId;
        this.employeeCpf = employeeCpf;
        this.productId = productId;
        this.quantity = quantity;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCpf() {
        return employeeCpf;
    }

    public void setEmployeeCpf(String employeeCpf) {
        this.employeeCpf = employeeCpf;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getValor() {
        return valorTotal;
    }

    public void setValor(double valorTotal) {
        this.valorTotal = valorTotal;
    }

}
