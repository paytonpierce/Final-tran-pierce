package com.company;

public class Artefact {
    private String Id;
    private String name;
    private String country;
    private Stakeholder owner;
    public Artefact(String i, String n, String c, Stakeholder s){
        Id = i;
        name = n;
        country = c;
        owner = s;
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
    public String getCountry(){
        return this.country;
    }
    public void setCountry(String c){
        this.country = c;
    }
    public Stakeholder getOwner(){
        return this.owner;
    }
    public void setOwner(Stakeholder s){
        this.owner = s;
    }
}
