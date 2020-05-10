package br.com.fiap.trabalho.dto.pagseguro;

public class CheckoutDTO {
    Transaction TransactionObject;


    // Getter Methods
    public Transaction getTransaction() {
        return TransactionObject;
    }

    // Setter Methods

    public void setTransaction(Transaction transactionObject) {
        this.TransactionObject = transactionObject;
    }
}