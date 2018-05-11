package jeu.Plateau;

import jeu.Carte.Carte;
import jeu.Deck.Deck;
import jeu.Exception.HearthstoneException;
import jeu.Joueur.IJoueur;
import jeu.Joueur.Joueur;

import java.util.ArrayList;

public class Plateau implements IPlateau {
    private IJoueur player1;
    private IJoueur player2;
    private ArrayList<IJoueur> players;
    private boolean debuter = false;
    private IJoueur joueurCourant;
    private static Plateau plateau = null;

    public Plateau(IJoueur player1, IJoueur player2) {
        this.setPlayer1(player1);
        this.setPlayer2(player2);
        this.players = new ArrayList<IJoueur>() ;
        ajouterJoueur(player1);
        ajouterJoueur(player2);
        this.joueurCourant = null;
    }

    //Getter Setter


    public IJoueur getPlayer1() {
        return player1;
    }

    public void setPlayer1(IJoueur player1) {
        this.player1 = player1;
    }

    public IJoueur getPlayer2() {
        return player2;
    }

    public void setPlayer2(IJoueur player2) {
        this.player2 = player2;
    }

    @Override
    public void ajouterJoueur(IJoueur joueur) {
        if(this.players.size()>2){
            throw new IllegalArgumentException("Trop de joueur");
        }
        this.players.add(joueur);

    }

    public void retirerJoueur(int i){
        if(i==0 || i==1){
            if(i == 0){
                players.remove(i);
            }else {
                if (i == 1) {
                    players.remove(i);
                } else {
                    throw new IllegalArgumentException("Erreur i");
                }
            }
        }
    }

    @Override
    public void demarerPartie() throws HearthstoneException {
        if(players.size() == 0){
            throw new HearthstoneException("Aucun joueur");
        }
        if(players.size()== 1){
            throw new HearthstoneException("Un seul joueur");
        }
        this.debuter = true;
    }

    @Override
    public boolean estDemaree() {
        return this.debuter;
    }

    @Override
    public void gagnerPartie(IJoueur joueur) {
        this.debuter = false;
        System.out.println("Joueur : "+joueur.getPseudo()+" a gagné la partie");

    }

    @Override
    public void finirTour(IJoueur joueur) {
        setJoueurCourant(joueur);

    }

    @Override
    public IJoueur getAdversaire(IJoueur courantplayer) {
        if( courantplayer == null){
            throw new IllegalArgumentException("Aucun adveraire");
        }
        if(courantplayer.getPseudo().equals(joueurCourant.getPseudo())&& players.get(0).getPseudo().equals(courantplayer.getPseudo())){
            return players.get(1);
        }
        if(courantplayer.getPseudo().equals(joueurCourant.getPseudo())&& players.get(1).getPseudo().equals(courantplayer.getPseudo())){
            return players.get(0);
        }
        return courantplayer;
    }

    @Override
    public IJoueur getJoueurCourant() {
        return this.joueurCourant;
    }

    @Override
    public void setJoueurCourant(IJoueur joueur) {
        this.joueurCourant = joueur;
    }




}