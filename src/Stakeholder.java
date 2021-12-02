package com.company;

public class Stakeholder {
    private String Id;
    private String name;
    private String address;
    private double balance;
    public Stakeholder(String i, String n, String a, double b){
        Id = i;
        name = n;
        address = a;
        balance = b;
    }
    public Stakeholder(Stakeholder s){
        Id = s.Id;
        name = s.name;
        address = s.address;
        balance = s.balance;
    }
    public String getId(){
        return this.Id;
    }
    public void setId(String i){
        this.Id = i;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name = n;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String a){
        this.address = a;
    }
    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double b){
        this.balance = b;
    }

    public String toString() {
        return "ID: " + Id + " " +
                "Name: " + name + " " +
                "Address: " + address + " " +
                "Balance: " + balance;
    }
}
