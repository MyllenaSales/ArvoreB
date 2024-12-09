package com.example;
import java.util.ArrayList;

public class ArvoreB {
    private Node raiz;
    private int ordem;

    public ArvoreB(int ordem) {
        this.ordem = ordem;
        raiz = new Node(true);  /* aq ele seta a raiz como folha */
    }

    public void inserir(int valor) {
        if (raiz.chaves.size() == (ordem - 1)) {
            Node novaRaiz = new Node(false);  
            novaRaiz.filhos.add(raiz);  
            dividir(novaRaiz, 0, raiz);  
            raiz = novaRaiz; 
        }
        inserirRecursivo(raiz, valor);  
    }

   
    private void inserirRecursivo(Node no, int valor) {
        int i = 0;
        /* aq ele encontra a posição correta pra add a chave */
        while (i < no.chaves.size() && valor > no.chaves.get(i)) {
            i++;
        }
        if (no.ehFolha) {
            no.chaves.add(i, valor);  
        } else {
            Node filho = no.filhos.get(i);
            if (filho.chaves.size() == (ordem - 1)) {
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
        int meio = (ordem - 1) / 2;  /* aq ele pega a chave central */

        /* aq ele move as chaves pro novo nó filho */
        for (int i = meio + 1; i < filho.chaves.size(); i++) {
            novoFilho.chaves.add(filho.chaves.get(i));
        }
        /* aq é só pra garantir q o msm possa ocorrer c um nó folha */
        if (!filho.ehFolha) {
            for (int i = meio + 1; i < filho.filhos.size(); i++) {
                novoFilho.filhos.add(filho.filhos.get(i));
            }
        }
        /* remove as chaves do no original */
        for (int i = filho.chaves.size() - 1; i >= meio + 1; i--) {
            filho.chaves.remove(i);
        }
        for (int i = filho.filhos.size() - 1; i >= meio + 1; i--) {
            filho.filhos.remove(i);
        }
        /* a chave do meio é promovida para o nó pai */
        pai.chaves.add(indice, filho.chaves.remove(meio)); 
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
        /* Após imprimir todas as chaves, se não for folha, percorre o último filho */
        if (!no.ehFolha) {
            percorrer(no.filhos.get(no.chaves.size()));  
        }
    }
}
