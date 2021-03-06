package jeu.Capacites;

import jeu.Carte.ICarte;
import jeu.Carte.Serviteur;
import jeu.Carte.Sort;
import jeu.Exception.HearthstoneException;
import jeu.Heros.Heros;
import jeu.Plateau.Plateau;

/**
 * Classe AttaqueCiblee reprente la capacité a attaquer une cible. Herite de Capacité
 * @author  Rayan KOUSSA
 * @version 0.1
 * @see  Capacite
 */
public class AttaqueCiblee extends Capacite{
    int degat;


    public AttaqueCiblee(String nom, String description,  int degat) {
        super(nom, description);
        this.degat= degat;
    }

    public int getDegat() {
        return degat;
    }

    /**
     * Permet d'attaquer un adversaire Heros ou serviteur
     * @param cible serviteur ou heros
     * @throws HearthstoneException
     */
    public void executerAction(Object cible) throws HearthstoneException {
        Plateau plateau = Plateau.getInstance();
        if (cible == null){
            throw new IllegalArgumentException("Cible nulle");
        }
        if(cible instanceof Sort){
            throw new HearthstoneException("Cibler un sort est impossible");
        }
        if (cible instanceof Plateau){
            throw new HearthstoneException("Cibler le platau??????");
        }
        if(cible instanceof Heros){
            for (ICarte carte: plateau.getAdversaire(plateau.getJoueurCourant()).getJeu()) {
                if(((Serviteur)carte).getProvocation()){
                    throw  new HearthstoneException("Personnage avec Provocation sur le plateau");
                }
            }
            plateau.getAdversaire(plateau.getJoueurCourant()).getHeros().blesserHero(this.degat);
            super.setServis(true);
            if(plateau.getAdversaire(Plateau.getInstance().getJoueurCourant()).getHeros().mort()){
                plateau.gagnerPartie(plateau.getAdversaire(plateau.getJoueurCourant()));
            }
        }
        if(cible instanceof Serviteur){
            if(plateau.getAdversaire(plateau.getJoueurCourant()).getJeu().contains(cible)){
                if(((Serviteur) cible).getProvocation()){
                    ((Serviteur) cible).blesserServiteur(this.degat);
                }
                for (ICarte carte: plateau.getAdversaire(plateau.getJoueurCourant()).getJeu()) {
                    if(((Serviteur)carte).getProvocation()){
                        throw  new HearthstoneException("Personnage avec Provocation sur le plateau");
                    }
                }
                super.setServis(true);
                if(( plateau.getAdversaire(plateau.getJoueurCourant()).getCarteEnJeu((((Serviteur) cible).getNomCarte()))).disparait()){
                    plateau.getAdversaire(plateau.getJoueurCourant()).getJeu().remove(cible);
                }
            }
        }
    }

    public void executerEffetDebutTour() {

    }

    public void executeEffetDisparition(Object cible)  {

    }

    public void executerEffetFinTour()  {

    }

    public void executeEffetMiseEnjeu(Object cible)  {

    }

}
