package br.com.fiap.trabalho.dto.pagseguro;

public class PaymentMethod {
    private String type;
    private String code;


    // Getter Methods

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    // Setter Methods

    public void setType(String type) {
        this.type = type;
    }

    public void setCode(String code) {
        this.code = code;
    }
}