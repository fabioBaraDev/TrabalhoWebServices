package br.com.fiap.trabalho.dto.pagseguro;

public class Shipping {
    Address AddressObject;
    private String type;
    private String cost;


    // Getter Methods

    public Address getAddress() {
        return AddressObject;
    }

    public String getType() {
        return type;
    }

    public String getCost() {
        return cost;
    }

    // Setter Methods

    public void setAddress(Address addressObject) {
        this.AddressObject = addressObject;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
