package com.example;

public class ArvoreB {
    private Node raiz;
    private int ordem;

    public ArvoreB(int ordem) {
        this.ordem = ordem;
        raiz = new Node(true);  // Raiz começa como folha
    }


    public void inserir(int valor) {
        if (raiz.chaves.size() == (ordem * 2 - 1)) {  
            Node novaRaiz = new Node(false);  
            novaRaiz.filhos.add(raiz);  
            dividir(novaRaiz, 0, raiz); 
            raiz = novaRaiz;  
        }
        inserirRecursivo(raiz, valor);  
    }

    private void inserirRecursivo(Node no, int valor) {
        int i = 0;
        /* aq ele procura a posição ideal para a nova chave */
        while (i < no.chaves.size() && valor > no.chaves.get(i)) {
            i++;
        }

        if (no.ehFolha) {
            no.chaves.add(i, valor); 
        } else {
            Node filho = no.filhos.get(i);
            if (filho.chaves.size() == (ordem * 2 - 1)) {
                dividir(no, i, filho);  
                if (valor > no.chaves.get(i)) {
                    i++;
                }
            }
            inserirRecursivo(no.filhos.get(i), valor);  
        }
    }

    
    private void dividir(Node pai, int indice, Node filho) {
        Node novoFilho = new Node(filho.ehFolha);
        int meio = ordem - 1;

        for (int i = 0; i < meio; i++) {
            novoFilho.chaves.add(filho.chaves.remove(ordem));
        }

        if (!filho.ehFolha) {
            for (int i = 0; i <= meio; i++) {
                novoFilho.filhos.add(filho.filhos.remove(ordem));
            }
        }

        pai.chaves.add(indice, filho.chaves.remove(meio));  /* aq a chave do meio sobe */
        pai.filhos.add(indice + 1, novoFilho);  
    }

    
    public void percorrer() {
        percorrer(raiz);
        System.out.println();
    }

    private void percorrer(Node no) {
        for (int i = 0; i < no.chaves.size(); i++) {
            if (!no.ehFolha) {
                percorrer(no.filhos.get(i));
            }
            System.out.print(no.chaves.get(i) + " ");
        }
        if (!no.ehFolha) {
            percorrer(no.filhos.get(no.chaves.size()));
        }
    }

}