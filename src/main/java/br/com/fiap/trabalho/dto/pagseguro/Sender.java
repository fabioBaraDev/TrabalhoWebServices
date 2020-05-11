package br.com.fiap.trabalho.dto.pagseguro;

public class Sender {
    private String name;
    private String email;
    Phone PhoneObject;
    Documents DocumentsObject;


    // Getter Methods

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Phone getPhone() {
        return PhoneObject;
    }

    public Documents getDocuments() {
        return DocumentsObject;
    }

    // Setter Methods

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Phone phoneObject) {
        this.PhoneObject = phoneObject;
    }

    public void setDocuments(Documents documentsObject) {
        this.DocumentsObject = documentsObject;
    }
}
