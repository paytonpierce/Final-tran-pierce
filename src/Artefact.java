package com.company;

public class Artefact {
    private String Id;
    private String name;
    private Stakeholder country;
    private Stakeholder owner;
    public Artefact(String i, String n, Stakeholder c, Stakeholder s){
        Id = i;
        name = n;
        country = c;
        owner = s;
    }
    public Artefact(Artefact a){
        Id = a.Id;
        name = a.name;
        country = a.country;
        owner = a.owner;
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
    public Stakeholder getCountry(){
        return this.country;
    }
    public void setCountry(Stakeholder c){
        this.country = c;
    }
    public Stakeholder getOwner(){
        return this.owner;
    }
    public void setOwner(Stakeholder s){
        this.owner = s;
    }

    public String toString() {
        return "Id: " + Id + " " +
                "Name: " + name + " " +
                "Country: " + country + " " +
                "Owner: " + owner;
    }

}
