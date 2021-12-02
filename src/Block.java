package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Random;


public class Block {
    private String previousHash;
    public Transaction data;
    private long timeStamp;
    public int nonce;
    public String hash;

    public Block(Transaction d, String ph, long t) {
        previousHash = ph;
        data = d;
        timeStamp = t;
        nonce = new Random(10).nextInt();
    }
    //Copy Constructor
    public Block(Block block) {
        previousHash = block.previousHash;
        data = block.data;
        timeStamp = block.timeStamp;
        nonce = block.nonce;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public void setPreviousHash(String ph) {
        this.previousHash = ph;
    }

    public Transaction getData() {
        return this.data;
    }

    public void setData(Transaction d) {
        this.data = d;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long t) {
        this.timeStamp = t;
    }

    public int getNonce() {
        return this.nonce;
    }

    public void setNonce(int n) {
        this.nonce = n;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String h) {
        this.hash = h;
    }

    public String toString() {
        return "Previous Hash: " + this.previousHash + " " +
                "Data: " + this.data + " " +
                "TimeStamp: " + this.timeStamp + " " +
                "Nonce: " + this.nonce + " " +
                "Hash: " + this.hash;
    }

    public String calculateBlockHash() {
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

    public void mineBlock(int prefix) {

        //If the transaction meets the stakeholders' agreement in TreatySC, mine the block;
        String hashValue = null;
        Transaction data = null;
        if (TreatySC(data) == true) {
            hashValue = new String(new char[prefix]).replace('\0', '0');
            //define prefix desire to find
            //check whether found solution
            while (!hash.substring(0, prefix).equals(hashValue)) {
                nonce++;
                hash = calculateBlockHash();
            }
        } else {
            //otherwise abort the transaction and display a proper message.
            System.out.println("The transaction does not meet the stakeholders agreement! Abort!");
        }


    }

    public boolean TreatySC(Transaction t) {
        //The sale must not happen if the artefact doesn’t have at least 2 transactions after 2001

        //already logged in the blockchain


        //must have enough money to cover the price:
        if (t.getBuyer().getBalance() >= t.getPrice()) {
            //10%
            t.getAuctionHouse().setBalance((t.getArtefact().getOwner().getBalance()) + (.10 * t.getPrice()));
            //20%
            t.getArtefact().getCountry().setBalance(t.getArtefact().getCountry().getBalance() + .20 * t.getPrice());
            double ArtefactBal = t.getArtefact().getOwner().getBalance();
            t.getArtefact().getOwner().setBalance(ArtefactBal + (.20 * t.getPrice()));
            //70%
            t.getSeller().setBalance((t.getSeller().getBalance()) + (.70 * t.getPrice()));

            return true;
        } else {
            return false;
        }
    }

    public Transaction retrieveProvenance(String id) {
        if (this.data.getArtefact().getId() == id) {
            return this.data;
        } else {
            return null;
        }
    }

    public Transaction retrieveProvenance(String id, long time) {
        if (this.data.getArtefact().getId() == id && this.getTimeStamp() > time) {
            return this.getData();
        } else {
            return null;
        }
    }

}












