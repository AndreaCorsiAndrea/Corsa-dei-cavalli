package com.tuo_nome.progetto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Cavallo extends Thread {
    private String nome;
    private int percorsoTotale;
    private int distanzaPercorsa = 0;
    private static boolean garaFinita = false;

    public Cavallo(String nome, int percorsoTotale) {
        this.nome = nome;
        this.percorsoTotale = percorsoTotale;
    }

    @Override
    public void run() {
        Random random = new Random();
        
        while (distanzaPercorsa < percorsoTotale && !garaFinita) {
            int avanzamento = random.nextInt(10) + 1; // il cavallo avanza da 1 a 10 metri
            distanzaPercorsa += avanzamento;
            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri");

            if (distanzaPercorsa >= percorsoTotale && !garaFinita) {
                garaFinita = true;
                System.out.println(nome + " ha vinto la gara!");
            }

            try {
                Thread.sleep(500); // attesa di 500 ms per simulare il tempo di avanzamento
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class GaraDiCavalli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inserisci la lunghezza del percorso
        System.out.print("Inserisci la lunghezza del percorso della gara in metri: ");
        int percorso = scanner.nextInt();
        scanner.nextLine();  // Consumiamo il newline

        // Inserisci il numero di cavalli
        System.out.print("Inserisci il numero di cavalli: ");
        int numeroCavalli = scanner.nextInt();
        scanner.nextLine();

        List<Cavallo> cavalli = new ArrayList<>();

        // Creiamo i cavalli
        for (int i = 1; i <= numeroCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + i + ": ");
            String nomeCavallo = scanner.nextLine();
            cavalli.add(new Cavallo(nomeCavallo, percorso));
        }

        // Avvia tutti i cavalli (thread)
        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        scanner.close();
    }
}
