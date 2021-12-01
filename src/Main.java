package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static boolean verify_Blockchain(ArrayList<Block> BC){
        int value = 0;
        for(int i = 0; i < BC.size(); i++){
            if(BC.get(i).calculateBlockHash().equals(BC.get(i).getHash())){
                value++;
            }
            if(BC.get(i).getPreviousHash().equals(BC.get(i-1).getHash())){
                value++;
            }
            //still need to add for if it has been mined
        }
        if(value == BC.size()*3){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args) {
        //Stakeholders for GUI
        Stakeholder buyer1 = new Stakeholder("buyer1","Buyer 1","111 buyer street",0.0);
        Stakeholder buyer2 = new Stakeholder("buyer2","Buyer 2","222 buyer street",0.0);
        Stakeholder seller1 = new Stakeholder("seller1","Seller 1","111 seller street",0.0);
        Stakeholder seller2 = new Stakeholder("seller2","Seller 2","222 seller street",0.0);
        Stakeholder country1 = new Stakeholder("usaID","United States","USA",0.0);
        Stakeholder country2 = new Stakeholder("ukID","United Kingdom","UK",0.0);
        Stakeholder auctionHouse1 = new Stakeholder("auctionHouse1","Auction House 1","123 Auction street",0.0);
        Stakeholder auctionHouse2 = new Stakeholder("auctionHouse2","Auction House 2","456 Auction street",0.0);
        //Artefacts for GUI
        Artefact art1 = new Artefact("art1","oil lamp",country1,seller1);
        Artefact art2 = new Artefact("art2","antique cabinet",country1,seller2);
        Artefact art3 = new Artefact("art3","antique table",country1,seller2);

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        Scanner scnr = new Scanner(System.in);
        System.out.println("Hello. Please create a transaction.");
        //artefact info
        Artefact artefact;
        System.out.println("Choose an artefact and write it's ID.");
        String artefactID = scnr.nextLine();
        /*while(!artefactID.contains("art1") || !artefactID.contains("art2") || !artefactID.contains("art3")){
            System.out.println("Sorry, that ID does not match an artefact in our system. Please write another.");
            artefactID = scnr.nextLine();
        }*/
        if(artefactID.contains("art1")){
            artefact = art1;
        }
        else if(artefactID.contains("art2")){
            artefact = art2;
        }
        else{
            artefact = art3;
        }
        //seller info
        Stakeholder seller;
        System.out.println("Choose a seller and write their ID.");
        String sellerID = scnr.nextLine();
        //removed while
        if(sellerID.contains("seller1")){
            seller = seller1;
        }
        else{
            seller = seller2;
        }
        //buyer info
        Stakeholder buyer;
        System.out.println("Choose a buyer and write their ID.");
        String buyerID = scnr.nextLine();
        //removed while
        if(buyerID.contains("buyer1")){
            buyer = buyer1;
        }
        else{
            buyer = buyer2;
        }
        //auction info
        Stakeholder ah;
        System.out.println("Choose an Auction House and write it's ID.");
        String ahID = scnr.nextLine();
        //removed while
        if(ahID.contains("auctionHouse1")){
            ah = auctionHouse1;
        }
        else{
            ah = auctionHouse2;
        }
        //price
        System.out.println("Finally, write an agreed upon price.");
        double price = scnr.nextDouble();

        Transaction data1 = new Transaction(artefact,seller,buyer,ah,price);
        Transaction data2 = new Transaction(artefact,seller,buyer,ah,price);
        Transaction data3 = new Transaction(artefact,seller,buyer,ah,price);


        //data1-data3 should be filled by the user
        Block genesisBlock = new Block(data1, "null", new Date().getTime());
        genesisBlock.mineBlock(prefix); //changed from newBlock
        if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) {
            blockchain.add(genesisBlock); //changed from newBlock
        }
        else {
            System.out.println("Malicious block, not added to the chain");
        }
        Block secondBlock = new Block(data2, blockchain.get(blockchain.size() - 1).getHash(),new
                Date().getTime());
        secondBlock.mineBlock(prefix); //changed from newBlock
        if (secondBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) { //changed from ArrayList<Block> BC
            blockchain.add(secondBlock);
        }
        else {
            System.out.println("Malicious block, not added to the chain");
        }
        Block newBlock = new Block(data3,blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.mineBlock(prefix);
        if (newBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) {
            blockchain.add(newBlock);
        }
        else {
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

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }



}
