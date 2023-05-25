package org.hposadas.natsystem.services;

import org.hposadas.natsystem.Main;
import org.hposadas.natsystem.model.Carte;
import org.hposadas.natsystem.model.Joueur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CarteServiceImplTest {

    public static final CarteService carteService = new CarteServiceImpl();
    private List<Carte> jeuDeCartes;

    @BeforeEach
    void setUp() {
        jeuDeCartes = carteService.initialiserDeck();
    }

    @Test
    void ajouterCartesAuVainqueur() {
        Joueur joueur1 = new Joueur(1);
        Carte carte1 = new Carte("Coeur", "As");
        Carte carte2 = new Carte("Carreau", "2");
        Carte carte3= new Carte("Trèfle", "3");
        Carte carte4 = new Carte("Pique", "4");

        Carte carte5 = new Carte("Coeur", "5");
        Carte carte6 = new Carte("Carreau", "6");
        Carte carte7 = new Carte("Trèfle", "7");
        Carte carte8 = new Carte("Pique", "8");
        joueur1.ajouterCarte(carte1);
        joueur1.ajouterCarte(carte2);
        joueur1.ajouterCarte(carte3);
        joueur1.ajouterCarte(carte4);
        joueur1.ajouterCartesDemarrage(joueur1.getCartesApresDistribution());

        Joueur joueur2 = new Joueur(2);
        joueur2.ajouterCarte(carte5);
        joueur2.ajouterCarte(carte6);
        joueur2.ajouterCarte(carte7);
        joueur2.ajouterCarte(carte8);
        joueur2.ajouterCartesDemarrage(joueur2.getCartesApresDistribution());

        List<Joueur> joueurs = List.of(joueur1, joueur2);

        List<Carte> cartesDuPli = List.of(carte4, carte8);
        carteService.ajouterCartesAuVainqueur(cartesDuPli, joueurs);

        Assertions.assertEquals(6, joueur2.getCartesApresDistribution().size());
    }

    @Test
    void trouverCartePlusForte() {
        Carte carte1 = new Carte( "Coeur", "As");
        Carte carte2 = new Carte("Carreau", "2");
        Carte carte3 = new Carte("Trefle", "3");
        Carte carte4 = new Carte("Pique", "4");

        List<Carte> cartes = List.of(carte1, carte2, carte3, carte4);

        Carte cartePlusForte = carteService.trouverCartePlusForte(cartes);
        Assertions.assertEquals(cartePlusForte, carte1);
    }

    @Test
    void prendreCartesDuPli() {
        Joueur joueur1 = new Joueur(1);
        joueur1.ajouterCarte(new Carte("Coeur", "As"));
        joueur1.ajouterCarte(new Carte("Carreau", "2"));
        joueur1.ajouterCarte(new Carte("Trefle", "3"));
        joueur1.ajouterCarte(new Carte("Pique", "4"));

        Joueur joueur2 = new Joueur(2);
        joueur2.ajouterCarte(new Carte("Carreau", "Valet"));
        joueur2.ajouterCarte(new Carte("Trefle", "5"));
        joueur2.ajouterCarte(new Carte("Coeur", "As"));
        joueur2.ajouterCarte(new Carte("Pique", "4"));

        List<Joueur> joueurs = List.of(joueur1, joueur2);

        List<Carte> cartesDuPli = carteService.prendreCartesDuPli(joueurs);
        Assertions.assertEquals(cartesDuPli.size(), 2);
    }


    @Test
    void distribuerCartes() {
        List<Joueur> joueurs = carteService.distribuerCartes(jeuDeCartes, Main.NOMBRE_DE_JOUEURS);
        Assertions.assertEquals(joueurs.size(), 4);

        joueurs.forEach(joueur ->{
            Assertions.assertEquals(joueur.getCartesStart().size(), 13);
        });
    }

    @Test
    void initialiserDeck() {
        Assertions.assertEquals(jeuDeCartes.size(), 52);
    }
}