package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;


public class Block {
    private String previousHash;
    private Transaction data;
    private long timeStamp;
    private int nonce;
    private String hash;

    public Block(String ph, Transaction d, String h){
        previousHash = ph;
        data = d;
        timeStamp = System.currentTimeMillis();
        //Long.parseLong(LocalDateTime.now().toString());
        nonce = new Random(10).nextInt();
    }

    public String getPreviousHash(){
        return this.previousHash;
    }
    public void setPreviousHash(String ph){
        this.previousHash = ph;
    }
    public Transaction getData(){
        return this.data;
    }
    public void setData(Transaction d){
        this.data = d;
    }
    public long getTimeStamp(){
        return this.timeStamp;
    }
    public void setTimeStamp(long t){
        this.timeStamp = t;
    }
    public int getNonce(){
        return this.nonce;
    }
    public void setNonce(int n){
        this.nonce = n;
    }
    public String getHash(){
        return this.hash;
    }
    public void setHash(String h){
        this.hash = h;
    }

    public String calculateBlockHash(){
        String dataToHash = previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + data.toString();
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("The encoding is not supported");
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
    public Transaction retrieveProvenance(String id){
        if(this.data.getArtefact().getId() == id){
            return this.data;
        }
        else{
            return null;
        }
    }
    public Transaction retrieveProvenance(String id, long time){
        if(this.data.getArtefact().getId() == id && this.getTimeStamp() > time){
            return this.getData();
        }
        else{
            return null;
        }
    }

    public void mineBlock(int prefix) {

        //If the transaction meets the stakeholders agreement in TreatySC, mine the block;
        if (TreatySC(data) == true) {
            setHash(mineBlock(prefix));
        } else {
            //otherwise abort the transaction and display a proper message.
            System.out.println("The transaction does not meet the stakeholders agreement! Abort!");
        }

        //define prefix desire to find
        //check whether found solution
        while (TreatySC(data) == true) {

        }
        //loop goes on

        //Note: you should start with a random value of nonce and increment it by one. However, there
        //are more sophisticated strategies to start and increment a nonce in real-world applications.

    }




    public boolean TreatySC (Transaction t) {
        //The sale must not happen if the artefact doesn’t have at least 2 transactions after 2001
        //already logged in the blockchain


        //must have enough money to cover the price:
        if (t.getBuyer().getBalance() >= t.getPrice()) {
            //10%
            t.getAuctionHouse().setBalance((t.getArtefact().getOwner().getBalance()) + (.10 * t.getPrice()));
            //20%
            //need country artefact balance
            double ArtefactBal = t.getArtefact().getOwner().getBalance();
            t.getArtefact().getOwner().setBalance(ArtefactBal + (.20 * t.getPrice()));
            //70%
            t.getSeller().setBalance((t.getSeller().getBalance()) + (.70 * t.getPrice()));

            return true;
        } else {
            return false;
        }
    }
}
