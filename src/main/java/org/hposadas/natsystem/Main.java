package org.hposadas.natsystem;

import org.hposadas.natsystem.model.Carte;
import org.hposadas.natsystem.model.Joueur;
import org.hposadas.natsystem.services.CarteService;
import org.hposadas.natsystem.services.CarteServiceImpl;

import java.util.*;

public class Main {

    public static final List<String> COULEURS = Arrays.asList("Trefle", "Pique", "Coeur", "Carreau");
    public static final List<String> VALEURS = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Reine", "Roi", "As");
    public static final Integer JOUEUR_TAILLE_DECK = 13;

    public static final Integer NOMBRE_DE_JOUEURS = 4;


    public static void main(String[] args) {

        CarteService carteService = new CarteServiceImpl();

        //1 - Initialiser ce jeu correctement : les 13 valeurs et les 4 couleurs.
        List<Carte> cardDeck = carteService.initialiserDeck();

        //2-  Afficher ensuite toutes les cartes.
        carteService.afficherCartes(cardDeck);
        System.out.println();

        //3-  Ecrire une fonction de comparaison de la valeur de 2 cartes.
        System.out.println("------- Comparaison des cartes -------");
        Carte c1 = new Carte("Trefle", "7");
        Carte c2 = new Carte("Pique", "Roi");
        Carte c3 = new Carte("Coeur", "2");
        Carte c4 = new Carte("Pique", "Roi");
        carteService.comparerCartes(c1, c2);
        carteService.comparerCartes(c2, c3);
        carteService.comparerCartes(c2, c4);
        System.out.println();

        //4-  Distribuer les cartes entre 4 joueurs.
        System.out.println("------- Distribution des cartes -------");
        List<Joueur> joueurs = carteService.distribuerCartes(cardDeck, NOMBRE_DE_JOUEURS);
        carteService.afficherCartesParJoueur(joueurs);
        System.out.println();

        //5-  Gestion d’un « pli » : prendre une carte dans le jeu de chacun des joueurs. Déterminer la plus forte.
        System.out.println("------- Piocher et trouver la carte la plus forte -------");
        List<Carte> cartesDuPli = carteService.prendreCartesDuPli(joueurs);
        Carte plusForteCarte = carteService.trouverCartePlusForte(cartesDuPli);
        System.out.println("La carte la plus forte est : " + plusForteCarte);
        System.out.println();

        //6-   Ajouter les cartes du pli aux cartes du joueur vainqueur (=carte la plus forte / si ex-aequo à n’importe quel vainqueur).
        System.out.println("------- Ajouter les cartes au vainqueur -------");
        carteService.ajouterCartesAuVainqueur(cartesDuPli, joueurs);
        System.out.println("Apres la premiere main les sets de cartes de chaque joueur sont: ");
        carteService.afficherCartesParJoueur(joueurs);
    }
}
