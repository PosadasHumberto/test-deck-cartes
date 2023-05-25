package org.hposadas.natsystem.services;

import org.hposadas.natsystem.model.Carte;
import org.hposadas.natsystem.model.Joueur;

import java.util.List;

public interface CarteService {

    public void ajouterCartesAuVainqueur(List<Carte> cartesDuPli, List<Joueur> joueurs);
    public Carte trouverCartePlusForte(List<Carte> cartesDuPli);
    public List<Carte> prendreCartesDuPli(List<Joueur> joueurs);
    public void afficherCartesParJoueur(List<Joueur> joueurs);
    public List<Joueur> distribuerCartes(List<Carte> cardDeck, Integer nombreJoueurs);
    public Integer comparerCartes(Carte c1, Carte c2);
    public void afficherCartes(List<Carte> cards);
    public List<Carte> initialiserDeck();

}
