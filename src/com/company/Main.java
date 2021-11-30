package com.company.com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {




    public boolean verify_Blockchain(ArrayList<Block> BC){
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
/*
    public Transaction retrieveProvenance(String id){
        if(this.data.getArtefact().getId() == id){
            return this.data;
        }
        else{
            return null;
        }
    }
    public Transaction retrieveProvenance(String id, long time){
        //if(this.data.getArtefact().getId() == id && this.getTimeStamp() > time){
            return this.getData();
        }
        else{
            return null;
        }
    }


*/







    public static void main(String[] args) {
        Stakeholder stake = new Stakeholder("stakeID","John Doe","123 compsci street",0.0);
        Artefact art = new Artefact("artID","John","USA",stake);

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter the first data:");
        /*
        String data1 = scnr.nextLine();
        //data1-data3 should be filled by the user
        Block genesisBlock = new Block(data1, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        newBlock.mineBlock(prefix); //I think this is supposed to be genesisBLock but I'm not 100% sure
        if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(ArrayList<Block> BC)) {
            blockchain.add(newBlock);
        }
        else {
            System.out.println("Malicious block, not added to the chain");
        }
        Block secondBlock = new Block(data2, blockchain.get(blockchain.size() - 1).getHash(),new
                Date().getTime());
        newBlock.mineBlock(prefix);
        if (secondBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(ArrayList<Block> BC)) {
            blockchain.add(newBlock);
        }
        else {
            System.out.println("Malicious block, not added to the chain");
        }
        Block newBlock = new Block(data3,blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.mineBlock(prefix);
        if (newBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(ArrayList<Block> BC)) {
            blockchain.add(newBlock);
        }
        else {
            System.out.println("Malicious block, not added to the chain");
        }

         */
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
