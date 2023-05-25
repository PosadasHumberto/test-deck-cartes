package org.hposadas.natsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final List<String> COULEURS = Arrays.asList("Trefle", "Pique", "Coeur", "Carreau");
    private static final List<String> VALEURS = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi", "As");
    public static final Integer JOUEUR_TAILLE_DECK = 13;

    public static final Integer NOMBRE_DE_JOUEURS = 4;


    public static void main(String[] args) {

        //1 - Initialiser ce jeu correctement : les 13 valeurs et les 4 couleurs.
        List<Carte> cardDeck = initialiserDeck();

        //2-  Afficher ensuite toutes les cartes.
        afficherCartes(cardDeck);
        System.out.println();

        //3-  Ecrire une fonction de comparaison de la valeur de 2 cartes.
        System.out.println("------- Comparaison des cartes -------");
        Carte c1 = new Carte("Trefle", "7");
        Carte c2 = new Carte("Pique", "Roi");
        Carte c3 = new Carte("Coeur", "2");
        Carte c4 = new Carte("Pique", "Roi");
        comparerCartes(c1, c2);
        comparerCartes(c2, c3);
        comparerCartes(c2, c4);
        System.out.println();

        //4-  Distribuer les cartes entre 4 joueurs.
        System.out.println("------- Distribution des cartes -------");
        List<List<Carte>> cartesParJoueur = distribuerCartes(cardDeck, 4);
        afficherCartesParJoueur(cartesParJoueur);
        System.out.println();

        //5-  Gestion d’un « pli » : prendre une carte dans le jeu de chacun des joueurs. Déterminer la plus forte.
        System.out.println("------- Piocher et trouver la carte la plus forte -------");
        List<Carte> pliCartes = piocherCartesDuPli(cartesParJoueur);
        Carte plusForteCarte = trouverCartePlusForte(pliCartes);
        System.out.println("La carte la plus forte est : " + plusForteCarte);
        System.out.println();

        //6-   Ajouter les cartes du pli aux cartes du joueur vainqueur (=carte la plus forte / si ex-aequo à n’importe quel vainqueur).
        System.out.println("------- Ajouter les cartes au vainqueur -------");
        ajouterCartesAuVainqueur(pliCartes, cartesParJoueur);
        afficherCartesParJoueur(cartesParJoueur);
    }

    public static void ajouterCartesAuVainqueur(List<Carte> pliCartes, List<List<Carte>> cartesParJoueur) {
        cartesParJoueur.forEach(cartes -> {
            cartes.addAll(pliCartes);
        });
    }

    public static Carte trouverCartePlusForte(List<Carte> pliCartes) {
        return Collections.max(pliCartes, (c1, c2) -> comparerCartes(c1, c2));
    }

    public static List<Carte> piocherCartesDuPli(List<List<Carte>> cartesDesJoueurs) {
        List<Carte> cartesDuPli = new ArrayList<>();
        for (List<Carte> cartesDuJoueur : cartesDesJoueurs) {
            cartesDuPli.add(cartesDuJoueur.remove(0));
        }
        return cartesDuPli;
    }

    public static void afficherCartesParJoueur(List<List<Carte>> cartesParJoueur) {
        Integer i = 1;
        for(List<Carte> cartesDuJoeur : cartesParJoueur) {
            System.out.println("Cartes du joueur " + i++ + " : ");
            cartesDuJoeur.forEach(System.out::println);
        }
    }

    public static List<List<Carte>> distribuerCartes(List<Carte> cardDeck, Integer NOMBRE_DE_JOUEURS) {
        List<List<Carte>> cartesParJoueur = new ArrayList<>();

        for (int i=0; i<NOMBRE_DE_JOUEURS; i++) {
            List<Carte> cartesDuJoueur = new ArrayList<>();
            for (int x=0; x<JOUEUR_TAILLE_DECK; x++) {
                cartesDuJoueur.add(
                        cardDeck.remove((int)(Math.random() * cardDeck.size()))
                );
            }
            cartesParJoueur.add(cartesDuJoueur);
        }
        return  cartesParJoueur;
    }

    public static Integer comparerCartes(Carte c1, Carte c2) {
        Integer resultComp = Integer.compare(
                VALEURS.indexOf(c1.getValeur()),
                VALEURS.indexOf(c2.getValeur())
        );
        String message;

        if (resultComp < 0) {
            message = "est plus petite que";
        } else if (resultComp > 0) {
            message = "est plus grande que";
        } else {
            message = "a la meme valeur que";
        }

        System.out.println(
                c1.getValeur() + " de " + c1.getCouleur() + " " +
                        message + " " +
                        c2.getValeur() + " de " + c2.getCouleur()
        );
        return resultComp;
    }

    public static void afficherCartes(List<Carte> cards) {
        cards.forEach(System.out::println);
    }

    public static List<Carte> initialiserDeck() {
        List<Carte> deck = new ArrayList<>();
        for (String coul : COULEURS) {
            for (String val : VALEURS) {
                deck.add(new Carte(coul, val));
            }
        }
        return deck;
    }
}
