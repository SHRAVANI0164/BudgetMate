package com.example.finalbudgetmate;

public class Model
{
  String note,amount,category;

    public Model(String note, String amount, String category) {
        this.note = note;
        this.amount = amount;
        this.category = category;
    }

    public String getName() {
        return note;
    }

    public void setName(String name) {
        this.note = name;
    }

    public String getContact() {
        return amount;
    }

    public void setContact(String contact) {
        this.amount = contact;
    }

    public String getEmail() {
        return category;
    }

    public void setEmail(String email) {
        this.category = email;
    }
}
