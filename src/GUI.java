package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class GUI {

    //transaction variables
    public static Artefact artefact = null;
    public static Stakeholder seller = null;
    public static Stakeholder buyer = null;
    public static Stakeholder country = null;
    public static Stakeholder auctionHouse = null;
    public static double price = 0;
    public static int transactionCounter = 0;

    //stakeholder variables
    public static String type = null;
    public static String id = null;
    public static String name;
    public static String address;
    public static double balance;

    public GUI() {
        //info from MAIN
        //buyers
        Stakeholder mainBuyer1 = new Stakeholder("buyer1", "Buyer 1", "111 buyer street", 100);
        Stakeholder mainBuyer2 = new Stakeholder("buyer2", "Buyer 2", "222 buyer street", 150);
        Stakeholder mainSeller1 = new Stakeholder("seller1", "Seller 1", "111 seller street", 125);
        Stakeholder mainSeller2 = new Stakeholder("seller2", "Seller 2", "222 seller street", 120);
        Stakeholder mainCountry1 = new Stakeholder("usaID", "United States", "USA", 300);
        Stakeholder mainCountry2 = new Stakeholder("ukID", "United Kingdom", "UK", 450);
        Stakeholder mainAuctionHouse1 = new Stakeholder("auctionHouse1", "Auction House 1", "123 Auction street", 575);
        Stakeholder mainAuctionHouse2 = new Stakeholder("auctionHouse2", "Auction House 2", "456 Auction street", 700);
        Artefact mainArt1 = new Artefact("art1", "oil lamp", mainCountry1, mainSeller1);
        Artefact mainArt2 = new Artefact("art2", "antique cabinet", mainCountry1, mainSeller2);
        Artefact mainArt3 = new Artefact("art3", "antique table", mainCountry2, mainSeller2);

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
        JPanel buttons = new JPanel();
        JLabel label = new JLabel("Transactions still needed:" + ((transactionCounter == 3) ? 0 : (3 - transactionCounter)));
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        JLabel instructions = new JLabel("<html>Hello! Please choose a type below. Notice: you must create 3 transactions</html>");
        instructions.setPreferredSize(new Dimension(frame.getBounds().width, 50));
        JTextArea userInput = new JTextArea();
        JButton priceSend = new JButton("submit");
        JButton idSend = new JButton("submit");
        JButton nameSend = new JButton("submit");
        JButton addressSend = new JButton("submit");
        JButton balanceSend = new JButton("submit");
        //buttons
        JButton stakeholder = new JButton("Stakeholder");
        JButton sellerStakeholder = new JButton("Seller");
        JButton buyerStakeholder = new JButton("Buyer");
        JButton countryStakeholder = new JButton("Country");
        JButton auctionHouseStakeholder = new JButton("Auction House");
        JButton transaction = new JButton("Transaction");
        JButton artefact1 = new JButton("Oil Lamp");
        JButton artefact2 = new JButton("Antique Cabinet");
        JButton artefact3 = new JButton("Antique Table");
        JButton buyer1 = new JButton("Buyer 1");
        JButton buyer2 = new JButton("Buyer 2");
        JButton seller1 = new JButton("Seller 1");
        JButton seller2 = new JButton("Seller 2");
        JButton country1 = new JButton("United States");
        JButton country2 = new JButton("United Kingdom");
        JButton auctionHouse1 = new JButton("Auction House 1");
        JButton auctionHouse2 = new JButton("Auction House 2");
        //sizing
        transaction.setPreferredSize(new Dimension(200, 50));
        stakeholder.setPreferredSize(new Dimension(200, 50));
        sellerStakeholder.setPreferredSize(new Dimension(200, 50));
        buyerStakeholder.setPreferredSize(new Dimension(200, 50));
        countryStakeholder.setPreferredSize(new Dimension(200, 50));
        auctionHouseStakeholder.setPreferredSize(new Dimension(200, 50));
        artefact1.setPreferredSize(new Dimension(200, 50));
        artefact2.setPreferredSize(new Dimension(200, 50));
        artefact3.setPreferredSize(new Dimension(200, 50));
        buyer1.setPreferredSize(new Dimension(200, 50));
        buyer2.setPreferredSize(new Dimension(200, 50));
        seller1.setPreferredSize(new Dimension(200, 50));
        seller2.setPreferredSize(new Dimension(200, 50));
        country1.setPreferredSize(new Dimension(200, 50));
        country2.setPreferredSize(new Dimension(200, 50));
        auctionHouse1.setPreferredSize(new Dimension(200, 50));
        auctionHouse2.setPreferredSize(new Dimension(200, 50));
        userInput.setPreferredSize(new Dimension(200, 20));
        panel.add(label);

        buttons.add(instructions);

        //TRANSACTIONS
        //first set
        buttons.add(transaction);
        buttons.add(stakeholder);
        //second set
        buttons.add(artefact1);
        buttons.add(artefact2);
        buttons.add(artefact3);
        artefact1.setVisible(false);
        artefact2.setVisible(false);
        artefact3.setVisible(false);
        //third set
        buttons.add(seller1);
        buttons.add(seller2);
        seller1.setVisible(false);
        seller2.setVisible(false);
        //fourth set
        buttons.add(buyer1);
        buttons.add(buyer2);
        buyer1.setVisible(false);
        buyer2.setVisible(false);
        //fifth set
        buttons.add(country1);
        buttons.add(country2);
        country1.setVisible(false);
        country2.setVisible(false);
        //sixth set
        buttons.add(auctionHouse1);
        buttons.add(auctionHouse2);
        auctionHouse1.setVisible(false);
        auctionHouse2.setVisible(false);
        //seventh set
        buttons.add(userInput);
        userInput.setVisible(false);
        buttons.add(priceSend);
        priceSend.setVisible(false);

        //STAKEHOLDERS
        //first set
        buttons.add(sellerStakeholder);
        buttons.add(buyerStakeholder);
        buttons.add(countryStakeholder);
        buttons.add(auctionHouseStakeholder);
        buttons.add(idSend);
        buttons.add(nameSend);
        buttons.add(addressSend);
        buttons.add(balanceSend);
        sellerStakeholder.setVisible(false);
        buyerStakeholder.setVisible(false);
        countryStakeholder.setVisible(false);
        auctionHouseStakeholder.setVisible(false);
        idSend.setVisible(false);
        nameSend.setVisible(false);
        addressSend.setVisible(false);
        balanceSend.setVisible(false);

        transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>First, choose an artefact for the transaction.</html>");
                artefact1.setVisible(true);
                artefact2.setVisible(true);
                artefact3.setVisible(true);
                transaction.setVisible(false);
                stakeholder.setVisible(false);
            }
        });
        stakeholder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>First, choose a stakeholder type.</html>");
                stakeholder.setVisible(false);
                transaction.setVisible(false);
                sellerStakeholder.setVisible(true);
                buyerStakeholder.setVisible(true);
                countryStakeholder.setVisible(true);
                auctionHouseStakeholder.setVisible(true);
            }
        });
        //STAKEHOLDER BUTTONS
        sellerStakeholder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, input an ID for your stakeholder.</html>");
                sellerStakeholder.setVisible(false);
                buyerStakeholder.setVisible(false);
                countryStakeholder.setVisible(false);
                auctionHouseStakeholder.setVisible(false);
                type = "seller";
                userInput.setVisible(true);
                idSend.setVisible(true);
            }
        });
        buyerStakeholder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, input an ID for your stakeholder.</html>");
                sellerStakeholder.setVisible(false);
                buyerStakeholder.setVisible(false);
                countryStakeholder.setVisible(false);
                auctionHouseStakeholder.setVisible(false);
                type = "buyer";
                userInput.setVisible(true);
                idSend.setVisible(true);
            }
        });
        countryStakeholder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, input an ID for your stakeholder.</html>");
                sellerStakeholder.setVisible(false);
                buyerStakeholder.setVisible(false);
                countryStakeholder.setVisible(false);
                auctionHouseStakeholder.setVisible(false);
                type = "country";
                userInput.setVisible(true);
                idSend.setVisible(true);
            }
        });
        auctionHouseStakeholder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, input an ID for your stakeholder.</html>");
                sellerStakeholder.setVisible(false);
                buyerStakeholder.setVisible(false);
                countryStakeholder.setVisible(false);
                auctionHouseStakeholder.setVisible(false);
                type = "auctionHouse";
                userInput.setVisible(true);
                idSend.setVisible(true);
            }
        });
        idSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Now, please input a name.</html>");
                id = userInput.getText();
                userInput.setText(null);
                userInput.setVisible(true);
                idSend.setVisible(false);
                nameSend.setVisible(true);
            }
        });
        nameSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Please input an address.</html>");
                name = userInput.getText();
                userInput.setText(null);
                userInput.setVisible(true);
                nameSend.setVisible(false);
                addressSend.setVisible(true);
            }
        });
        addressSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Lastly, please input the balance of the stakeholder.</html>");
                address = userInput.getText();
                userInput.setText(null);
                userInput.setVisible(true);
                addressSend.setVisible(false);
                balanceSend.setVisible(true);
            }
        });
        balanceSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Please input an address.</html>");
                balance = Double.parseDouble(userInput.getText());
                userInput.setText(null);
                userInput.setVisible(false);
                balanceSend.setVisible(false);
                instructions.setText("<html>Hello! Please choose a type below. Notice: you must create 3 transactions</html>");
                transaction.setVisible(true);
                stakeholder.setVisible(true);
                if (type.equals("seller")) {
                    Main.sellerList.add(new Stakeholder(id, name, address, balance));
                } else if (type.equals("buyer")) {
                    Main.buyerList.add(new Stakeholder(id, name, address, balance));
                } else if (type.equals("country")) {
                    Main.countryList.add(new Stakeholder(id, name, address, balance));
                } else {
                    Main.auctionList.add(new Stakeholder(id, name, address, balance));
                }
                id = null;
                name = null;
                address = null;
                balance = 0;
                label.setText("Transactions still needed:" + ((transactionCounter > 2) ? 0 : (3 - transactionCounter)));
            }
        });

        //TRANSACTION BUTTONS
        artefact1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, choose a seller.</html>");
                artefact1.setVisible(false);
                artefact2.setVisible(false);
                artefact3.setVisible(false);
                seller1.setVisible(true);
                seller2.setVisible(true);
                artefact = new Artefact(mainArt1);
            }
        });
        artefact2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, choose a seller.</html>");
                artefact1.setVisible(false);
                artefact2.setVisible(false);
                artefact3.setVisible(false);
                seller1.setVisible(true);
                seller2.setVisible(true);
                artefact = new Artefact(mainArt2);
            }
        });
        artefact3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, choose a seller.</html>");
                artefact1.setVisible(false);
                artefact2.setVisible(false);
                artefact3.setVisible(false);
                seller1.setVisible(true);
                seller2.setVisible(true);
                artefact = new Artefact(mainArt3);
            }
        });
        seller1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Now, please choose a buyer.</html>");
                seller1.setVisible(false);
                seller2.setVisible(false);
                buyer1.setVisible(true);
                buyer2.setVisible(true);
                seller = new Stakeholder(mainSeller1);
            }
        });
        seller2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Now, please choose a buyer.</html>");
                seller1.setVisible(false);
                seller2.setVisible(false);
                buyer1.setVisible(true);
                buyer2.setVisible(true);
                seller = new Stakeholder(mainSeller2);
            }
        });
        buyer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Please choose a country.</html>");
                buyer1.setVisible(false);
                buyer2.setVisible(false);
                country1.setVisible(true);
                country2.setVisible(true);
                buyer = new Stakeholder(mainBuyer1);
            }
        });
        buyer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Please choose a country.</html>");
                buyer1.setVisible(false);
                buyer2.setVisible(false);
                country1.setVisible(true);
                country2.setVisible(true);
                buyer = new Stakeholder(mainBuyer2);
            }
        });
        country1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, choose an auction house.</html>");
                country1.setVisible(false);
                country2.setVisible(false);
                auctionHouse1.setVisible(true);
                auctionHouse2.setVisible(true);
                country = new Stakeholder(mainCountry1);
            }
        });
        country2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Next, choose an auction house.</html>");
                country1.setVisible(false);
                country2.setVisible(false);
                auctionHouse1.setVisible(true);
                auctionHouse2.setVisible(true);
                country = new Stakeholder(mainCountry2);
            }
        });
        auctionHouse1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Finally, set an agreed upon price.</html>");
                auctionHouse1.setVisible(false);
                auctionHouse2.setVisible(false);
                userInput.setVisible(true);
                priceSend.setVisible(true);
                auctionHouse = new Stakeholder(mainAuctionHouse1);
            }
        });
        auctionHouse2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructions.setText("<html>Finally, set an agreed upon price.</html>");
                auctionHouse1.setVisible(false);
                auctionHouse2.setVisible(false);
                userInput.setVisible(true);
                priceSend.setVisible(true);
                auctionHouse = new Stakeholder(mainAuctionHouse2);
            }
        });
        priceSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                price = Integer.parseInt(userInput.getText());
                userInput.setText("");
                userInput.setVisible(false);
                priceSend.setVisible(false);
                transactionCounter++;
                if (Main.data1 == null) {
                    Main.data1 = new Transaction(artefact, seller, buyer, auctionHouse, price);
                    Main.transactions.add(Main.data1);
                } else if (Main.data2 == null) {
                    Main.data2 = new Transaction(artefact, seller, buyer, auctionHouse, price);
                    Main.transactions.add(Main.data2);
                } else if (Main.data3 == null) {
                    Main.data3 = new Transaction(artefact, seller, buyer, auctionHouse, price);
                    Main.transactions.add(Main.data3);
                } else {
                    Main.transactions.add(new Transaction(artefact, seller, buyer, auctionHouse, price));
                }
                artefact = null;
                seller = null;
                buyer = null;
                auctionHouse = null;
                price = 0;
                instructions.setText("<html>Hello! Please choose a type below. Notice: you must create 3 transactions</html>");
                transaction.setVisible(true);
                stakeholder.setVisible(true);
                label.setText("Transactions still needed:" + ((transactionCounter > 2) ? 0 : (3 - transactionCounter)));
            }
        });
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, buttons);
        frame.setVisible(true);

        Main.blockchain.get(Main.blockchain.size() - 1).calculateBlockHash();
        Block genesisBlock = new Block(Main.data1, Main.blockchain.get(Main.blockchain.size() - 1).getHash(), new Date().getTime());
        genesisBlock.setHash(genesisBlock.calculateBlockHash());
        genesisBlock.mineBlock(4); //changed from newBlock
        if (genesisBlock.getHash().substring(0, 4).equals("0000") &&
                Main.verify_Blockchain(Main.blockchain)) {
            Main.blockchain.add(genesisBlock); //changed from newBlock
        } else {
            System.out.println("Malicious block, not added to the chain");
        }
        Main.blockchain.get(Main.blockchain.size() - 1).calculateBlockHash();
        Block secondBlock = new Block(Main.data2, Main.blockchain.get(Main.blockchain.size() - 1).getHash(), new
                Date().getTime());
        secondBlock.setHash(secondBlock.calculateBlockHash());
        secondBlock.mineBlock(4); //changed from newBlock
        if (secondBlock.getHash().substring(0, 4).equals("0000") &&
                Main.verify_Blockchain(Main.blockchain)) { //changed from ArrayList<Block> BC
            Main.blockchain.add(secondBlock);
        } else {
            System.out.println("Malicious block, not added to the chain");
        }
        Main.blockchain.get(Main.blockchain.size() - 1).calculateBlockHash();
        Block newBlock = new Block(Main.data3, Main.blockchain.get(Main.blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.setHash(newBlock.calculateBlockHash());
        newBlock.mineBlock(4);
        if (newBlock.getHash().substring(0, 4).equals("0000") &&
                Main.verify_Blockchain(Main.blockchain)) {
            Main.blockchain.add(newBlock);
        } else {
            System.out.println("Malicious block, not added to the chain");
        }

        System.out.println("Blockchain:");
        for(int i = 0; i < Main.blockchain.size(); i++){
            System.out.println(Main.blockchain.get(i));
        }
    }
}
