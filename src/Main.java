package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main{

    //user interface transactions
    public static Transaction data1 = null;
    public static Transaction data2 = null;
    public static Transaction data3 = null;
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    //list of objects for transaction option
    public static ArrayList<Stakeholder> buyerList = new ArrayList<>();
    public static ArrayList<Stakeholder> sellerList = new ArrayList<>();
    public static ArrayList<Stakeholder> countryList = new ArrayList<>();
    public static ArrayList<Stakeholder> auctionList = new ArrayList<>();
    public static ArrayList<Artefact> artefactList = new ArrayList<>();

    public static boolean verify_Blockchain(ArrayList<Block> BC) {

        for (int i = 6; i < BC.size(); i++) {
            //if the hash is accurate
            String dataToHash = BC.get(i).getPreviousHash()
                    + Long.toString(BC.get(i).getTimeStamp())
                    + Integer.toString(BC.get(i).getNonce())
                    + BC.get(i).getData().toString();
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
            //if calculateBlockHash is accurate
            if (!BC.get(i).calculateBlockHash().equals(buffer.toString())){
                return false;
            }
            //if the previous hash is accurate
            if (i > 0) {
                if (!BC.get(i).getPreviousHash().equals(BC.get(i - 1).getHash())) {
                    return false;
                }
            }
            //if it has been mined
            if(!BC.get(i).getHash().startsWith("0000")){
                System.out.println(BC.get(i).getHash());
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Block> blockchain = new ArrayList<>();


    public static void main(String[] args) {
        //GUI interface
        new GUI();
        //scanner
        Scanner scnr = new Scanner(System.in);
        //buyers
        Stakeholder buyer1 = new Stakeholder("buyer1", "Buyer 1", "111 buyer street", 100);
        buyerList.add(buyer1);
        Stakeholder buyer2 = new Stakeholder("buyer2", "Buyer 2", "222 buyer street", 150);
        buyerList.add(buyer2);
        //sellers
        Stakeholder seller1 = new Stakeholder("seller1", "Seller 1", "111 seller street", 125);
        sellerList.add(seller1);
        Stakeholder seller2 = new Stakeholder("seller2", "Seller 2", "222 seller street", 120);
        sellerList.add(seller2);
        //countries
        Stakeholder country1 = new Stakeholder("usaID", "United States", "USA", 300);
        countryList.add(country1);
        Stakeholder country2 = new Stakeholder("ukID", "United Kingdom", "UK", 450);
        countryList.add(country2);
        //auction houses
        Stakeholder auctionHouse1 = new Stakeholder("auctionHouse1", "Auction House 1", "123 Auction street", 575);
        auctionList.add(auctionHouse1);
        Stakeholder auctionHouse2 = new Stakeholder("auctionHouse2", "Auction House 2", "456 Auction street", 700);
        auctionList.add(auctionHouse2);

        //Artefacts for GUI
        Artefact art1 = new Artefact("art1", "oil lamp", country1, seller1);
        artefactList.add(art1);
        Artefact art2 = new Artefact("art2", "antique cabinet", country1, seller2);
        artefactList.add(art2);
        Artefact art3 = new Artefact("art3", "antique table", country2, seller2);
        artefactList.add(art3);

        //pre-created transactions to be logged in blockchain
        Transaction transaction1 = new Transaction(art1, seller1, buyer1, auctionHouse1, 15);
        transactions.add(transaction1);
        Transaction transaction2 = new Transaction(art1, seller2, buyer1, auctionHouse1, 17);
        transactions.add(transaction2);
        Transaction transaction3 = new Transaction(art2, seller1, buyer2, auctionHouse2, 25);
        transactions.add(transaction3);
        Transaction transaction4 = new Transaction(art2, seller2, buyer1, auctionHouse1, 23);
        transactions.add(transaction4);
        Transaction transaction5 = new Transaction(art3, seller1, buyer1, auctionHouse2, 14);
        transactions.add(transaction5);
        Transaction transaction6 = new Transaction(art3, seller2, buyer2, auctionHouse2, 18);
        transactions.add(transaction6);
        Transaction transaction7 = new Transaction(art1, seller1, buyer1, auctionHouse1, 15);
        transactions.add(transaction7);
        //creation of blockchain


        Block block1 = new Block(transaction1, null, new Date().getTime());
        block1.setHash(block1.calculateBlockHash());
        blockchain.add(block1);
        Block block2 = new Block(transaction2, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        block2.setHash(block2.calculateBlockHash());
        blockchain.add(block2);
        Block block3 = new Block(transaction3, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        block3.setHash(block3.calculateBlockHash());
        blockchain.add(block3);
        Block block4 = new Block(transaction4, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        block4.setHash(block4.calculateBlockHash());
        blockchain.add(block4);
        Block block5 = new Block(transaction5, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        block5.setHash(block5.calculateBlockHash());
        blockchain.add(block5);
        Block block6 = new Block(transaction6, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        block6.setHash(block6.calculateBlockHash());
        blockchain.add(block6);
        Block block7 = new Block(transaction7, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        block7.setHash(block7.calculateBlockHash());
        block7.mineBlock(4);
        blockchain.add(block7);

        //creation of prefix
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        while (data1 == null || data2 == null || data3 == null) {
            String option;
            System.out.println("Do you want to add a stakeholder or transaction? Type s or t");
            System.out.println("Note: you must make 3 transaction.");
            option = scnr.nextLine();
            while(!option.equals("s") && !option.equals("t")){
                option = scnr.nextLine();
            }
            //stakeholder option
            String stakeID;
            String stakeName;
            String stakeAddress;
            String stakeType;
            double stakeBalance;
            if (option.contains("s")) {
                Scanner numScanner = new Scanner(System.in);
                Scanner stakeholderScanner = new Scanner(System.in);
                System.out.println("What type of stakeholder do you want to create? Type the corresponding number.");
                System.out.println("1. Seller");
                System.out.println("2. Buyer");
                System.out.println("3. Country");
                System.out.println("4. Auction House");
                int type = stakeholderScanner.nextInt();
                while (type < 1 || type > 4) {
                    System.out.println("Invalid response. Try again.");
                    type = numScanner.nextInt();
                }
                if (type == 1) {
                    stakeType = "Seller";
                } else if (type == 2) {
                    stakeType = "Buyer";
                } else if (type == 3) {
                    stakeType = "Country";
                } else {
                    stakeType = "Auction House";
                }
                System.out.println("Next, please write the ID. This must differ from all other ID's");
                String temp = stakeholderScanner.nextLine();
                stakeID = stakeholderScanner.nextLine();
                System.out.println("Please write the stakeholder name. This will be used to identify them.");
                stakeName = stakeholderScanner.nextLine();
                System.out.println("Now write the stakeholder address.");
                stakeAddress = stakeholderScanner.nextLine();
                System.out.println("Last, write the current balance of the stakeholder.");
                stakeBalance = stakeholderScanner.nextDouble();

                Stakeholder userStakeholder = new Stakeholder(stakeID, stakeName, stakeAddress, stakeBalance);
                if (stakeType.equals("Seller")) {
                    sellerList.add(userStakeholder);
                } else if (stakeType.equals("Buyer")) {
                    buyerList.add(userStakeholder);
                } else if (stakeType.equals("Country")) {
                    countryList.add(userStakeholder);
                } else {
                    auctionList.add(userStakeholder);
                }
                System.out.println("Stakeholder successfully added!");
            } else { //transaction option
                //Parts of transaction in user interface
                Artefact artefact = null;
                Stakeholder seller = null;
                Stakeholder buyer = null;
                Stakeholder auctionHouse = null;

                //artefact info
                System.out.println("Choose an artefact from the list and write it's ID.");
                for (int i = 0; i < artefactList.size(); i++) {
                    System.out.println(artefactList.get(i).getName() + ": " + artefactList.get(i).getId());
                }
                String artefactID = scnr.nextLine();
                for (int i = 0; i < artefactList.size(); i++) {
                    if (artefactList.get(i).getId().equals(artefactID)) {
                        artefact = new Artefact(artefactList.get(i));
                    }
                }
                while (artefact == null) {
                    for (int i = 0; i < artefactList.size(); i++) {
                        if (artefactList.get(i).getId().equals(artefactID)) {
                            artefact = new Artefact(artefactList.get(i));
                        }
                    }
                    if (artefact == null) {
                        System.out.println("That ID is not in our system. Try again.");
                        artefactID = scnr.nextLine();
                    }
                }
                //seller info
                System.out.println("Choose a seller from the list and write it's ID.");
                for (int i = 0; i < sellerList.size(); i++) {
                    System.out.println(sellerList.get(i).getName() + ": " + sellerList.get(i).getId());
                }
                String sellerID = scnr.nextLine();
                for (int i = 0; i < sellerList.size(); i++) {
                    if (sellerList.get(i).getId().equals(sellerID)) {
                        seller = new Stakeholder(sellerList.get(i));
                    }
                }
                while (seller == null) {
                    for (int i = 0; i < sellerList.size(); i++) {
                        if (sellerList.get(i).getId().equals(sellerID)) {
                            seller = new Stakeholder(sellerList.get(i));
                        }
                    }
                    if (seller == null) {
                        System.out.println("That ID is not in our system. Try again.");
                        sellerID = scnr.nextLine();
                    }
                }
                //buyer info
                System.out.println("Choose a buyer from the list and write it's ID.");
                for (int i = 0; i < buyerList.size(); i++) {
                    System.out.println(buyerList.get(i).getName() + ": " + buyerList.get(i).getId());
                }
                String buyerID = scnr.nextLine();
                for (int i = 0; i < buyerList.size(); i++) {
                    if (buyerList.get(i).getId().equals(buyerID)) {
                        buyer = new Stakeholder(buyerList.get(i));
                    }
                }
                while (buyer == null) {
                    for (int i = 0; i < buyerList.size(); i++) {
                        if (buyerList.get(i).getId().equals(buyerID)) {
                            buyer = new Stakeholder(buyerList.get(i));
                        }
                    }
                    if (buyer == null) {
                        System.out.println("That ID is not in our system. Try again.");
                        buyerID = scnr.nextLine();
                    }
                }
                //auction info
                System.out.println("Choose an auction house from the list and write it's ID.");
                for (int i = 0; i < auctionList.size(); i++) {
                    System.out.println(auctionList.get(i).getName() + ": " + auctionList.get(i).getId());
                }
                String auctionID = scnr.nextLine();
                for (int i = 0; i < auctionList.size(); i++) {
                    if (auctionList.get(i).getId().equals(auctionID)) {
                        auctionHouse = new Stakeholder(auctionList.get(i));
                    }
                }
                while (auctionHouse == null) {
                    for (int i = 0; i < auctionList.size(); i++) {
                        if (auctionList.get(i).getId().equals(auctionID)) {
                            auctionHouse = new Stakeholder(auctionList.get(i));
                        }
                    }
                    if (auctionHouse == null) {
                        System.out.println("That ID is not in our system. Try again.");
                        auctionID = scnr.nextLine();
                    }
                }
                //price
                System.out.println("Finally, write an agreed upon price.");
                double price = scnr.nextDouble();

                if(data1 == null) {
                    data1 = new Transaction(artefact, seller, buyer, auctionHouse, price);
                    transactions.add(data1);
                }
                else if(data2 == null) {
                    data2 = new Transaction(artefact, seller, buyer, auctionHouse, price);
                    transactions.add(data2);
                }
                else if(data3 == null) {
                    data3 = new Transaction(artefact, seller, buyer, auctionHouse, price);
                    transactions.add(data3);
                }
                else{
                    transactions.add(new Transaction(artefact, seller, buyer, auctionHouse, price));
                }
            }
        }
        //data1-data3 should be filled by the user
        blockchain.get(blockchain.size() - 1).calculateBlockHash();
        Block genesisBlock = new Block(data1, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        genesisBlock.setHash(genesisBlock.calculateBlockHash());
        genesisBlock.mineBlock(prefix); //changed from newBlock
        if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) {
            blockchain.add(genesisBlock); //changed from newBlock
        } else {
            System.out.println("Malicious block, not added to the chain");
        }
        blockchain.get(blockchain.size() - 1).calculateBlockHash();
        Block secondBlock = new Block(data2, blockchain.get(blockchain.size() - 1).getHash(), new
                Date().getTime());
        secondBlock.setHash(secondBlock.calculateBlockHash());
        secondBlock.mineBlock(prefix); //changed from newBlock
        if (secondBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) { //changed from ArrayList<Block> BC
            blockchain.add(secondBlock);
        } else {
            System.out.println("Malicious block, not added to the chain");
        }
        blockchain.get(blockchain.size() - 1).calculateBlockHash();
        Block newBlock = new Block(data3, blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.setHash(newBlock.calculateBlockHash());
        newBlock.mineBlock(prefix);
        if (newBlock.getHash().substring(0, prefix).equals(prefixString) &&
                verify_Blockchain(blockchain)) {
            blockchain.add(newBlock);
        } else {
            System.out.println("Malicious block, not added to the chain");
        }

        System.out.println("Blockchain:");
        for(int i = 6; i < blockchain.size(); i++){
            System.out.println(blockchain.get(i));
        }
    }


}
