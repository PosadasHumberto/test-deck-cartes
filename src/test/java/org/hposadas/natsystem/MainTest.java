package org.hposadas.natsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void ajouterCartesAuVainqueur() {
        List<List<Carte>> cartesDesJoueurs = new ArrayList<>();
        List<Carte> joueur1 = new ArrayList<>(Arrays.asList(new Carte("Coeur", "As")));
        List<Carte> joueur2 = new ArrayList<>(Arrays.asList(new Carte("Carreau", "Roi")));
        cartesDesJoueurs.add(joueur1);
        cartesDesJoueurs.add(joueur2);

        List<Carte> cartesDuPli = new ArrayList<>(Arrays.asList(new Carte("Pique", "Dame"), new Carte("Trèfle", "10")));
        Main.ajouterCartesAuVainqueur(cartesDuPli, cartesDesJoueurs);

        Assert.assertEquals(3, cartesDesJoueurs.get(0).size());
        Assert.assertEquals(3, cartesDesJoueurs.get(1).size());
    }

    @Test
    void trouverCartePlusForte() {
        List<Carte> cartes = Arrays.asList(
                new Carte( "Coeur", "As"),
                new Carte( "Carreau", "Roi"),
                new Carte("Pique", "Dame"),
                new Carte("Trèfle", "10")
        );

        Carte cartePlusForte = Main.trouverCartePlusForte(cartes);
        Assert.assertEquals("As", cartePlusForte.getValeur());
        Assert.assertEquals("Coeur", cartePlusForte.getCouleur());
    }

    @Test
    public void testPiocherCartesDuPli() {
        List<List<Carte>> cartesDesJoueurs = new ArrayList<>();
        List<Carte> joueur1 = new ArrayList<>(Arrays.asList(new Carte("As", "Coeur"), new Carte("2", "Carreau"), new Carte("3", "Trèfle")));
        List<Carte> joueur2 = new ArrayList<>(Arrays.asList(new Carte("Roi", "Pique"), new Carte("Dame", "Carreau"), new Carte("10", "Trèfle")));
        cartesDesJoueurs.add(joueur1);
        cartesDesJoueurs.add(joueur2);

        List<Carte> cartesDuPli = Main.piocherCartesDuPli(cartesDesJoueurs);
        Assert.assertEquals(2, cartesDuPli.size());
        Assert.assertEquals(2, cartesDesJoueurs.get(0).size());
    }


    @Test
    void distribuerCartes() {
        List<Carte> jeuDeCartes = Main.initialiserDeck();
        List<List<Carte>> cartesDesJoueurs = Main.distribuerCartes(jeuDeCartes, Main.NOMBRE_DE_JOUEURS );
        Assert.assertEquals(cartesDesJoueurs.size(), 4);
        Assert.assertEquals(cartesDesJoueurs.get(0).size(), 13);
    }

    @Test
    void comparerCartes() {
        Carte c1 = new Carte("Coeur", "As");
        Carte c2 = new Carte("Carreau", "Roi");
        Integer comparaisonRes = Main.comparerCartes(c1, c2);
        Assert.assertTrue(comparaisonRes > 0);
    }

    @Test
    void initialiserDeck() {
        List<Carte> deck = Main.initialiserDeck();
        Assert.assertEquals(52, deck.size());
    }
}