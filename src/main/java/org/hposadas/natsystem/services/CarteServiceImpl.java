package org.hposadas.natsystem.services;

import org.hposadas.natsystem.Main;
import org.hposadas.natsystem.model.Carte;
import org.hposadas.natsystem.model.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hposadas.natsystem.Main.COULEURS;
import static org.hposadas.natsystem.Main.VALEURS;

public class CarteServiceImpl implements CarteService {
    @Override
    public void ajouterCartesAuVainqueur(List<Carte> cartesDuPli, List<Joueur> joueurs) {
        Carte cartePlusForte = trouverCartePlusForte(cartesDuPli);

        for (Carte carte : cartesDuPli) {
            if (carte.equals(cartePlusForte)){
                for (Joueur joueur : joueurs) {
                    if (joueur.contientCarte(carte)){
                        joueur.ajouterCartes(cartesDuPli);
                        System.out.println("Le joueur qui a gagne la main est le joueur " + joueur.getId());
                    }
                }
            }
        }
        joueurs.forEach(joueur -> {
            joueur.viderListeDemarrage();
            joueur.ajouterCartesDemarrage(joueur.getCartesApresDistribution());
        });
    }

    @Override
    public Carte trouverCartePlusForte(List<Carte> cartesDuPli) {
        Carte cartePlusForte = null;
        for (Carte carte : cartesDuPli) {
            if (cartePlusForte == null || comparerCartes(carte, cartePlusForte) > 0) {
                cartePlusForte = carte;
            }
        }
        return cartePlusForte;
    }

    @Override
    public List<Carte> prendreCartesDuPli(List<Joueur> joueurs) {
        List<Carte> cartesDuPli = new ArrayList<>();
        for (Joueur joueur : joueurs) {
            Carte carte = joueur.prendreDerniereCarte();
            cartesDuPli.add(carte);
        }
        return cartesDuPli;
    }

    @Override
    public void afficherCartesParJoueur(List<Joueur> joueurs) {
        Integer i = 1;
        for(Joueur joueur : joueurs) {
            System.out.println("Cartes du joueur " + i + ":");
            afficherCartes(joueur.getCartesApresDistribution());
            i++;
        }
    }

    @Override
    public List<Joueur> distribuerCartes(List<Carte> cardDeck, Integer nombreJoueurs) {
        List<Joueur> joueurs = new ArrayList<>();

        for (Integer i=1; i <= nombreJoueurs; i++) {
            Joueur joueur = new Joueur(i);
            for (int j = 0; j< Main.JOUEUR_TAILLE_DECK; j++) {
                int indexCarteAleatoire = new Random().nextInt(cardDeck.size());
                Carte carte = cardDeck.get(indexCarteAleatoire);
                cardDeck.remove(indexCarteAleatoire);
                joueur.ajouterCarteDemarrage(carte);
            }
            joueur.ajouterCartes(joueur.getCartesStart());
            joueurs.add(joueur);
        }
        return  joueurs;
    }

    @Override
    public Integer comparerCartes(Carte c1, Carte c2) {
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

    @Override
    public void afficherCartes(List<Carte> cards) {
        cards.forEach(System.out::println);
    }

    @Override
    public List<Carte> initialiserDeck() {
        List<Carte> deck = new ArrayList<>();
        for (String coul : COULEURS) {
            for (String val : VALEURS) {
                deck.add(new Carte(coul, val));
            }
        }
        return deck;
    }
}
