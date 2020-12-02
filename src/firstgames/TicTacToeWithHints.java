package firstgames;

import java.util.*;

public class TicTacToeWithHints extends TicTacToe{

  public TicTacToeWithHints(Player joueur1,Player joueur2){
    super(joueur1,joueur2);
    this.joueur_courant = joueur1;
    this.gameboard = new Player[3][3];
  }

//pour chaque case vide => simuler un coup de l'adversaire et appelé getWinner  et annulé le coup
  public ArrayList<Integer> hints(){
    ArrayList<Integer> liste = new ArrayList<Integer>();
    gameboard2 = new Player[3][3];
    Player adversaire=null;
    if(joueur_courant==joueur1){//OK
      adversaire=joueur2;
    }
    else{
      adversaire=joueur1;
    }
    for(int coup : validMoves()){
      execute(coup);
      if(getWinner()!=null){
        liste.add(coup);
      }

    }
    return liste;
  }


@Override
public String situationToString(){
  String[][] gameboard2 = new String[3][3];
  for(int i=0;i<gameboard.length;i++){
    for(int j=0;j<gameboard[i].length;j++){
      if(gameboard[i][j]==joueur1){
        gameboard2[i][j]="X";
      }
      else if(gameboard[i][j]==joueur2){
        gameboard2[i][j]="O";
      }
      else if(gameboard[i][j]==null){
        gameboard2[i][j]=".";
      }
    }
  }
  String chaine1=gameboard2[0][0]+" "+gameboard2[0][1]+" "+gameboard2[0][2];
  String chaine2=gameboard2[1][0]+" "+gameboard2[1][1]+" "+gameboard2[1][2];
  String chaine3=gameboard2[2][0]+" "+gameboard2[2][1]+" "+gameboard2[2][2];

  String chaine=System.lineSeparator();
  chaine+=chaine3;
  chaine+=System.lineSeparator();
  chaine+=chaine2;
  chaine+=System.lineSeparator();
  chaine+=chaine1;
  chaine+=System.lineSeparator();

  System.out.println(hints());
  return chaine;
}

}
