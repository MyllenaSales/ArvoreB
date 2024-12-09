package com.example;

public class Main {
    public static void main(String[] args) {
        ArvoreB arvore = new ArvoreB(3); 
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(20);
        arvore.inserir(15);
        arvore.inserir(35);
        arvore.inserir(40);
        arvore.inserir(25);
        arvore.inserir(30);

        arvore.percorrer();  
    }
}
