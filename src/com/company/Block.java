package com.company.com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Random;
import java.io.UnsupportedEncodingException;


public class Block {
    private String previousHash;
    private Transaction data;
    private long timeStamp;
    private int nonce;
    private String hash;

    public Block(String ph, Transaction d, String h){
        previousHash = ph;
        data = d;
        timeStamp = Long.parseLong(LocalDateTime.now().toString());
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
}
