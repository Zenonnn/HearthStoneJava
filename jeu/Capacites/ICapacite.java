package jeu.Capacites;

import jeu.Exception.HearthstoneException;

public interface ICapacite {
    void executerAction(Object cible) throws HearthstoneException;
    void executerEffetDebutTour()throws HearthstoneException;
    void executeEffetDisparition(Object cible)throws HearthstoneException;
    void executerEffetFinTour()throws HearthstoneException;
    void executeEffetMiseEnjeu(Object cible)throws HearthstoneException;
    String getDescription();
    String getNom();
}
