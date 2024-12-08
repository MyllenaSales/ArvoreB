package com.example;

import java.util.ArrayList;

class Node {
    boolean ehFolha; 
    ArrayList<Integer> chaves; 
    ArrayList<Node> filhos; 

    Node(boolean ehFolha) {
        this.ehFolha = ehFolha;
        this.chaves = new ArrayList<>();
        this.filhos = new ArrayList<>();
    }
}


