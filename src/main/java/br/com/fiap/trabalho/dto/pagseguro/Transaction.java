package br.com.fiap.trabalho.dto.pagseguro;

public class Transaction {
    private String date;
    private String code;
    private String reference;
    private String type;
    private String status;
    private String lastEventDate;
    PaymentMethod PaymentMethodObject;
    private String grossAmount;
    private String discountAmount;
    private String feeAmount;
    private String netAmount;
    private String extraAmount;
    private String installmentCount;
    private String itemCount;
    Items ItemsObject;
    Sender SenderObject;
    Shipping ShippingObject;
    GatewaySystem GatewaySystemObject;


    // Getter Methods

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getReference() {
        return reference;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getLastEventDate() {
        return lastEventDate;
    }

    public PaymentMethod getPaymentMethod() {
        return PaymentMethodObject;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public String getExtraAmount() {
        return extraAmount;
    }

    public String getInstallmentCount() {
        return installmentCount;
    }

    public String getItemCount() {
        return itemCount;
    }

    public Items getItems() {
        return ItemsObject;
    }

    public Sender getSender() {
        return SenderObject;
    }

    public Shipping getShipping() {
        return ShippingObject;
    }

    public GatewaySystem getGatewaySystem() {
        return GatewaySystemObject;
    }

    // Setter Methods

    public void setDate(String date) {
        this.date = date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastEventDate(String lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    public void setPaymentMethod(PaymentMethod paymentMethodObject) {
        this.PaymentMethodObject = paymentMethodObject;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public void setExtraAmount(String extraAmount) {
        this.extraAmount = extraAmount;
    }

    public void setInstallmentCount(String installmentCount) {
        this.installmentCount = installmentCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public void setItems(Items itemsObject) {
        this.ItemsObject = itemsObject;
    }

    public void setSender(Sender senderObject) {
        this.SenderObject = senderObject;
    }

    public void setShipping(Shipping shippingObject) {
        this.ShippingObject = shippingObject;
    }

    public void setGatewaySystem(GatewaySystem gatewaySystemObject) {
        this.GatewaySystemObject = gatewaySystemObject;
    }
}
