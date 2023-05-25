package org.hposadas.natsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private Integer id;
    private List<Carte> cartesStart;
    private List<Carte> cartesApresDistribution;

    public Joueur(Integer id) {
        this.id = id;
        this.cartesStart = new ArrayList<>();
        this.cartesApresDistribution = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public List<Carte> getCartesStart() {
        return cartesStart;
    }

    public List<Carte> getCartesApresDistribution() {
        return cartesApresDistribution;
    }

    public void ajouterCarteDemarrage(Carte carte) {
        cartesStart.add(carte);
    }

    public void ajouterCartesDemarrage(List<Carte> cartes) {
        cartesStart.addAll(cartes);
    }

    public void ajouterCarte(Carte carte) {
        cartesApresDistribution.add(carte);
    }

    public void ajouterCartes(List<Carte> cartes) {
        cartesApresDistribution.addAll(cartes);
    }

    public boolean contientCarte(Carte carte) {
        return cartesStart.contains(carte);
    }

    public  Carte prendreDerniereCarte(){
        return cartesApresDistribution.remove(cartesApresDistribution.size()-1);
    }

    public void viderListeDemarrage(){
        this.cartesStart.clear();
    }
}
