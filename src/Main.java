package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {


    public static boolean verify_Blockchain(ArrayList<Block> BC) {
        int value = 0;
        for (int i = 0; i < BC.size(); i++) {
            if (BC.get(i).calculateBlockHash().equals(BC.get(i).getHash())) {
                value++;
            }
            if (BC.get(i).getPreviousHash().equals(BC.get(i - 1).getHash())) {
                value++;
            }
            //still need to add for if it has been mined
        }
        if (value == BC.size() * 3) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        //Stakeholders for GUI
        ArrayList<Stakeholder> buyerList = new ArrayList<>();
        ArrayList<Stakeholder> sellerList = new ArrayList<>();
        ArrayList<Stakeholder> countryList = new ArrayList<>();
        ArrayList<Stakeholder> auctionList = new ArrayList<>();
        ArrayList<Artefact> artefactList = new ArrayList<>();

        //buyers
        Stakeholder buyer1 = new Stakeholder("buyer1", "Buyer 1", "111 buyer street", 0.0);
        buyerList.add(buyer1);
        Stakeholder buyer2 = new Stakeholder("buyer2", "Buyer 2", "222 buyer street", 0.0);
        buyerList.add(buyer2);
        //sellers
        Stakeholder seller1 = new Stakeholder("seller1", "Seller 1", "111 seller street", 0.0);
        sellerList.add(seller1);
        Stakeholder seller2 = new Stakeholder("seller2", "Seller 2", "222 seller street", 0.0);
        sellerList.add(seller2);
        //countries
        Stakeholder country1 = new Stakeholder("usaID", "United States", "USA", 0.0);
        countryList.add(country1);
        Stakeholder country2 = new Stakeholder("ukID", "United Kingdom", "UK", 0.0);
        countryList.add(country2);
        //auction houses
        Stakeholder auctionHouse1 = new Stakeholder("auctionHouse1", "Auction House 1", "123 Auction street", 0.0);
        auctionList.add(auctionHouse1);
        Stakeholder auctionHouse2 = new Stakeholder("auctionHouse2", "Auction House 2", "456 Auction street", 0.0);
        auctionList.add(auctionHouse2);

        //Artefacts for GUI
        Artefact art1 = new Artefact("art1", "oil lamp", country1, seller1);
        artefactList.add(art1);
        Artefact art2 = new Artefact("art2", "antique cabinet", country1, seller2);
        artefactList.add(art2);
        Artefact art3 = new Artefact("art3", "antique table", country1, seller2);
        artefactList.add(art3);

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');




        //Adding Components to the frame.
        Artefact artefact = null;
        Stakeholder seller = null;
        Stakeholder buyer = null;
        Stakeholder auctionHouse = null;
        double cost;

        Scanner scnr = new Scanner(System.in);
        System.out.println("Hello. Please create a transaction.");
        //artefact info
        System.out.println("Choose an artefact from the list and write it's ID.");
        for(int i = 0; i < artefactList.size(); i++){
            System.out.println(artefactList.get(i).getName() + ": " + artefactList.get(i).getId());
        }
        String artefactID = scnr.nextLine();
        for(int i = 0; i < artefactList.size(); i++){
            if(artefactList.get(i).getId().equals(artefactID)){
                artefact = new Artefact(artefactList.get(i));
            }
        }
        while(artefact == null){
            for(int i = 0; i < artefactList.size(); i++){
                if(artefactList.get(i).getId().equals(artefactID)){
                    artefact = new Artefact(artefactList.get(i));
                }
            }
            if(artefact == null) {
                System.out.println("That ID is not in our system. Try again.");
                artefactID = scnr.nextLine();
            }
        }
        //seller info
        System.out.println("Choose a selle from the list and write it's ID.");
        for(int i = 0; i < sellerList.size(); i++){
            System.out.println(sellerList.get(i).getName() + ": " + sellerList.get(i).getId());
        }
        String sellerID = scnr.nextLine();
        for(int i = 0; i < sellerList.size(); i++){
            if(sellerList.get(i).getId().equals(sellerID)){
                seller = new Stakeholder(sellerList.get(i));
            }
        }
        while(seller == null){
            for(int i = 0; i < sellerList.size(); i++){
                if(sellerList.get(i).getId().equals(sellerID)){
                    seller = new Stakeholder(sellerList.get(i));
                }
            }
            if(seller == null) {
                System.out.println("That ID is not in our system. Try again.");
                sellerID = scnr.nextLine();
            }
        }
        //buyer info
        System.out.println("Choose a buyer from the list and write it's ID.");
        for(int i = 0; i < buyerList.size(); i++){
            System.out.println(buyerList.get(i).getName() + ": " + buyerList.get(i).getId());
        }
        String buyerID = scnr.nextLine();
        for(int i = 0; i < buyerList.size(); i++){
            if(buyerList.get(i).getId().equals(buyerID)){
                buyer = new Stakeholder(buyerList.get(i));
            }
        }
        while(buyer == null){
            for(int i = 0; i < buyerList.size(); i++){
                if(buyerList.get(i).getId().equals(buyerID)){
                    buyer = new Stakeholder(buyerList.get(i));
                }
            }
            if(buyer == null) {
                System.out.println("That ID is not in our system. Try again.");
                buyerID = scnr.nextLine();
            }
        }
        //auction info
        System.out.println("Choose an auction house from the list and write it's ID.");
        for(int i = 0; i < auctionList.size(); i++){
            System.out.println(auctionList.get(i).getName() + ": " + auctionList.get(i).getId());
        }
        String auctionID = scnr.nextLine();
        for(int i = 0; i < auctionList.size(); i++){
            if(auctionList.get(i).getId().equals(auctionID)){
                auctionHouse = new Stakeholder(auctionList.get(i));
            }
        }
        while(auctionHouse == null){
            for(int i = 0; i < auctionList.size(); i++){
                if(auctionList.get(i).getId().equals(auctionID)){
                    auctionHouse = new Stakeholder(auctionList.get(i));
                }
            }
            if(auctionHouse == null) {
                System.out.println("That ID is not in our system. Try again.");
                auctionID = scnr.nextLine();
            }
        }
        //price
        System.out.println("Finally, write an agreed upon price.");
        double price = scnr.nextDouble();

        Transaction data1 = new Transaction(artefact, seller, buyer, auctionHouse, price);
        Transaction data2 = new Transaction(artefact, seller, buyer, auctionHouse, price);
        Transaction data3 = new Transaction(artefact, seller, buyer, auctionHouse, price);


        //data1-data3 should be filled by the user
        Block genesisBlock = new Block(data1, "null", new Date().getTime());
        genesisBlock.mineBlock(prefix); //changed from newBlock
        if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) {
            blockchain.add(genesisBlock); //changed from newBlock
        } else {
            System.out.println("Malicious block, not added to the chain");
        }
        Block secondBlock = new Block(data2, blockchain.get(blockchain.size() - 1).getHash(), new
                Date().getTime());
        secondBlock.mineBlock(prefix); //changed from newBlock
        if (secondBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) { //changed from ArrayList<Block> BC
            blockchain.add(secondBlock);
        } else {
            System.out.println("Malicious block, not added to the chain");
        }
        Block newBlock = new Block(data3, blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.mineBlock(prefix);
        if (newBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) {
            blockchain.add(newBlock);
        } else {
            System.out.println("Malicious block, not added to the chain");
        }

        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("BLOCKCHAIN GUI");
        JMenu m2 = new JMenu("Selinna + Payton");
        mb.add(m1);
        mb.add(m2);


        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Transaction");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
