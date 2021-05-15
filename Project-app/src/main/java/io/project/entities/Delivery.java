package io.project.entities;

import java.time.LocalDate;

public class Delivery {
    private int id;
    private Supplier supplier;
    private LocalDate date;
    private int paymentDelay;
    private int amountToPay;
    private boolean isPaid;
    private Facility facility;

    private String supplierName;
    private String status;

    public Delivery() {
    }
    public Delivery(Supplier supplier, LocalDate date, int paymentDelay, int amountToPay, boolean isPaid, Facility facility) {
        this.supplier = supplier;
        this.date = date;
        this.paymentDelay = paymentDelay;
        this.amountToPay = amountToPay;
        this.isPaid = isPaid;
        this.facility = facility;
        this.supplierName = supplier.getName() + "\t" + supplier.getEmail();
        this.status = isPaid ? "opłacono" : "nie opłacono";
    }

    public Delivery(int id, Supplier supplier, LocalDate date, int paymentDelay, int amountToPay, boolean isPaid, Facility facility) {
        this.id = id;
        this.supplier = supplier;
        this.date = date;
        this.paymentDelay = paymentDelay;
        this.amountToPay = amountToPay;
        this.isPaid = isPaid;
        this.facility = facility;
        this.supplierName = supplier.getName() + "\t" + supplier.getEmail();
        this.status = isPaid ? "opłacono" : "nie opłacono";
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
        status = isPaid ? "opłacono" : "nie opłacono";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPaymentDelay() {
        return paymentDelay;
    }

    public void setPaymentDelay(int paymentDelay) {
        this.paymentDelay = paymentDelay;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(int amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
