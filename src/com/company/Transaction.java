package com.company.com.company;

import com.company.com.company.Artefact;
import com.company.com.company.Stakeholder;

import java.time.LocalDateTime;

public class Transaction {
    private Artefact artefact;
    private LocalDateTime timestamp;
    private Stakeholder seller;
    private Stakeholder buyer;
    private Stakeholder AuctionHouse;
    private double price;

    public Transaction(Artefact a, Stakeholder sell, Stakeholder buy, Stakeholder auction, double p){
        artefact = a;
        timestamp = LocalDateTime.now();
        seller = sell;
        buyer = buy;
        AuctionHouse = auction;
        price = p;
    }
    public Artefact getArtefact(){
        return this.artefact;
    }
    public void setArtefact(Artefact a){
        this.artefact = a;
    }
    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }
    public void setTimestamp(LocalDateTime t){
        this.timestamp = t;
    }
    public Stakeholder getSeller(){
        return this.seller;
    }
    public void setSeller(Stakeholder s){
        this.seller = s;
    }
    public Stakeholder getBuyer(){
        return this.buyer;
    }
    public void setBuyer(Stakeholder b){
        this.buyer = b;
    }
    public Stakeholder getAuctionHouse(){
        return this.AuctionHouse;
    }
    public void setAuctionHouse(Stakeholder ah){
        this.AuctionHouse = ah;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double p){
        this.price = p;
    }

    public String toString() {
        return "Artefact: " + artefact + " " +
                "Time Stamp: " + timestamp + " " +
                "Seller: " + seller + " " +
                "Buyer: " + buyer + " " +
                "Auction House: " + AuctionHouse + " " +
                "Price: " + price;
    }

}
