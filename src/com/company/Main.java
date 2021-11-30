package com.company.com.company;

import java.util.ArrayList;
import java.util.Date;

public class Main {


    public boolean TreatySC(Transaction t){

    }

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

    public static void main(String[] args) {
        Stakeholder stake = new Stakeholder("stakeID","John Doe","123 compsci street",0.0);
        Artefact art = new Artefact("artID","John","USA",stake);

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        //data1-data3 should be filled by the user
        Block genesisBlock = new Block(data1,   blockchain.get(blockchain.size() - 1).getHash(), new
                Date().getTime());
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

    }

}
