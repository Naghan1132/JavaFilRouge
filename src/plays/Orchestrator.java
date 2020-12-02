package plays;

import games.Game;
import players.Player;
import java.util.*;


public class Orchestrator{

  protected Game jeu;

  public Orchestrator(Game jeu){
    this.jeu=jeu;
  }


  public void play(){
    System.out.println(" ");
    System.out.println("Bienvenue dans votre partie");
    System.out.println(" ");
    while (jeu.isOver()==false){
      System.out.println(" ");
      System.out.println("-----------------------------------------------");
      System.out.println(" ");
      System.out.println("C'est à "+jeu.getCurrentPlayer()+" de jouer");
      System.out.println(jeu.validMoves());
      int move = jeu.getCurrentPlayer().chooseMove(jeu);
      jeu.execute(move);
    }
    if(jeu.getWinner()!=null){
      System.out.println(jeu.situationToString());
      System.out.println("Le gagnant est : "+jeu.getWinner()+" ^_^ ");
    }
    else{
      System.out.println(jeu.situationToString());
      System.out.println("EGALITE, dommage peut-être la prochaine fois ^_^ ");
    }

  }
}
