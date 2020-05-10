 package br.com.fiap.trabalho.dto.pagseguro;

public class RawMessage {
    private String _xmlns;
    private String _xsi;


    // Getter Methods

    public String get_xmlns() {
        return _xmlns;
    }

    public String get_xsi() {
        return _xsi;
    }

    // Setter Methods

    public void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    public void set_xsi(String _xsi) {
        this._xsi = _xsi;
    }
}
public class RawCode {
    private String _xmlns;
    private String _xsi;


    // Getter Methods

    public String get_xmlns() {
        return _xmlns;
    }

    public String get_xsi() {
        return _xsi;
    }

    // Setter Methods

    public void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    public void set_xsi(String _xsi) {
        this._xsi = _xsi;
    }
}
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
public class Address {
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String country;
    private String postalCode;


    // Getter Methods

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    // Setter Methods

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
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
public class Documents {
    Document DocumentObject;


    // Getter Methods

    public Document getDocument() {
        return DocumentObject;
    }

    // Setter Methods

    public void setDocument(Document documentObject) {
        this.DocumentObject = documentObject;
    }
}
public class Document {
    private String type;
    private String value;


    // Getter Methods

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    // Setter Methods

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
public class Phone {
    private String areaCode;
    private String number;


    // Getter Methods

    public String getAreaCode() {
        return areaCode;
    }

    public String getNumber() {
        return number;
    }

    // Setter Methods

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
public class Items {
    ArrayList < Object > item = new ArrayList < Object > ();


    // Getter Methods



    // Setter Methods


}
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