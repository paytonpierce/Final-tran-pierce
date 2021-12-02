package com.company;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Block {
    private String previousHash;
    public Transaction data;
    private long timeStamp;
    public int nonce;
    public String hash;
    public boolean hasBeenMined;
    public boolean hasBeenCalculated;

    private static final String DATE_FORMATTER= "yyyy-MM-ddTHH:mm:ss";

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

    //for verification
    public boolean getHasBeenMined() {
        return this.hasBeenMined;
    }

    public void setHasBeenMined(boolean b) {
        this.hasBeenMined = b;
    }

    public boolean getHasBeenCalculated() {
        return this.hasBeenCalculated;
    }

    public void setHasBeenCalculated(boolean b) {
        this.hasBeenCalculated= b;
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
        this.hasBeenCalculated = true;
        return buffer.toString();
    }

    public void mineBlock(int prefix) {

        //If the transaction meets the stakeholders' agreement in TreatySC, mine the block;
        String hashValue = "";
        for(int i = 0; i < prefix; i++){
            hashValue.concat("0");
        }
        Transaction data = this.data;
        if (TreatySC(data) == true) {
            hashValue = new String(new char[prefix]).replace('\0', '0');
            //define prefix desire to find
            //check whether found solution
            while (!this.hash.substring(0, prefix).equals(hashValue)) {
                this.nonce++;
                this.hash = calculateBlockHash();
            }
            this.hasBeenMined = true;
        } else {
            //otherwise abort the transaction and display a proper message.
            System.out.println("The transaction does not meet the stakeholders agreement! Abort!");
            this.hasBeenMined = false;
        }
    }

    public ArrayList<Block> transactionCounter = new ArrayList<>();


    public boolean TreatySC(Transaction t) {
        //The sale must not happen if the artefact doesn’t have at least 2 transactions after 2001
        boolean value = true;
        int loggedCount = 0;
        if (retrieveProvenance(t.getArtefact().getId(), (t.getTimestamp().getSecond())) < 2) {
            System.out.println("The artefact does not have at least two transactions after 2001. Cannot run!");
            //return false;
        }
        //already logged in the blockchain
        for (int i = 0; i < Main.blockchain.size(); i++) {
            if (Main.blockchain.get(i).getData().getArtefact().getId().equals(t.getArtefact().getId())) {
                loggedCount++;
            }
        }
        if (loggedCount < 1) {
            System.out.println("The artefact is not logged in blockchain. Cannot run!");
            return false;
        }
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

    public int retrieveProvenance(String id) {
        int count = 0;
        for (int i = 0; i < Main.transactions.size(); i++) {
            if ( Main.transactions.get(i).getArtefact() == this.data.getArtefact()) {
                count++;
            }
        }
        return count;
    }

    public int retrieveProvenance(String id, long timestamp) {
        int count = 0;
        LocalDateTime time2001 = LocalDateTime.of(2001, Month.DECEMBER,31,23,59,59);
        for (int i = 0; i < Main.blockchain.size(); i++) {
            boolean time = Main.blockchain.get(i).getData().getTimestamp().isAfter(time2001);
            if ( Main.blockchain.get(i).getData().getArtefact().getId().equals(this.data.getArtefact().getId()) && time) {
                count++;
            }
        }
        return count;
    }
}












