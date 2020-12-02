package secondgames;

import java.util.*;

public abstract class AbstractGame{

  protected Player joueur1;
  protected Player joueur2;
  protected Player joueur_courant;

  public AbstractGame(Player joueur1,Player joueur2){
    this.joueur1=joueur1;
    this.joueur2=joueur2;
    this.joueur_courant=joueur1;
  }

  protected abstract void doExecute(int coup);

  public void execute(int coup){
    doExecute(coup);
    if(joueur_courant==joueur1){
      joueur_courant=joueur2;
    }
    else{
      joueur_courant=joueur1;
    }
  }

  public Player getCurrentPlayer(){
    return joueur_courant;
  }

  public abstract ArrayList<Integer> validMoves();//need override
  public abstract boolean isValid(int coup);
  public abstract String situationToString();
  public abstract String moveToString(int coup);//need override
  public abstract boolean isOver();
  public abstract Player getWinner();

}
