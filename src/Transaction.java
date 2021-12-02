package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {
    private Artefact artefact;
    private LocalDateTime timestamp;
    private Stakeholder seller;
    private Stakeholder buyer;
    private Stakeholder AuctionHouse;
    private double price;

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";



    public Transaction(Artefact a, Stakeholder sell, Stakeholder buy, Stakeholder auction, double p){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        SimpleDateFormat f = new SimpleDateFormat(DATE_FORMATTER);
        String time = LocalDateTime.now().format(formatter);
        try {
            Date d = f.parse(time);
            timestamp = d.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        artefact = a;

        seller = sell;
        buyer = buy;
        AuctionHouse = auction;
        price = p;
    }
    //copy constructor
    public Transaction(Transaction transaction){
        artefact = transaction.artefact;
        timestamp = transaction.timestamp;
        seller = transaction.seller;
        buyer = transaction.buyer;
        AuctionHouse = transaction.AuctionHouse;
        price = transaction.price;
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
