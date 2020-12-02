package firstgames;

import java.util.*;

public class TicTacToe{

  protected Player joueur1;
  protected Player joueur2;
  protected Player joueur_courant;
  protected Player[][] gameboard;

  public TicTacToe(Player joueur1,Player joueur2){
    this.joueur1 = joueur1;
    this.joueur2 = joueur2;
    this.joueur_courant = joueur1;
    this.gameboard = new Player[3][3];
  }

  public Player getCurrentPlayer(){
    return joueur_courant;
  }

  public void execute(int coup){
    if (joueur_courant == joueur1){
      int cpt=0;
      for (int i=0;i<gameboard.length;i++){
        for (int j=0;j<gameboard[i].length;j++){
          if (cpt == coup){
            gameboard[i][j]=joueur_courant;
          }
          cpt+=1;
        }
      }
      joueur_courant=joueur2;
    }

    else{
      int cpt=0;
      for (int i=0;i<gameboard.length;i++){
        for (int j=0;j<gameboard[i].length;j++){
          if (cpt == coup){
            gameboard[i][j]=joueur_courant;
          }
          cpt+=1;
        }
      }
      joueur_courant=joueur1;
    }
  }


  public boolean isValid(int coup){
    int cpt=0;
    for (int i=0;i<gameboard.length;i++){
      for (int j=0;j<gameboard[i].length;j++){
        if (cpt==coup){
          if (gameboard[i][j]==null && coup>=0 && coup<=8){
            return true;
          }
          else{
            return false;
          }
        }
        cpt+=1;
      }
    }
    return false;
  }

  public ArrayList<Integer> validMoves(){
    ArrayList<Integer> liste = new ArrayList<>();
    int cpt=0;
    System.out.println("Coups valides : ");
    for (int i=0;i<gameboard.length;i++){
      for (int j=0;j<gameboard[i].length;j++){
        if (gameboard[i][j]==null){
          liste.add(cpt);
          System.out.println(cpt+": ("+i+","+j+")");
        }
        cpt+=1;
      }
    }
    return liste;
  }

  public boolean wins(Player joueur,int row, int column,int deltaRow,int deltaColumn){ //delta = -1 ou 0 ou +1
    if(gameboard[row][column]==joueur && gameboard[row+deltaRow][column+deltaColumn]==joueur && gameboard[row+2*deltaRow][column+2*deltaColumn]==joueur){
      return true;
    }
    else{
      return false;
    }
  }
  public Player getWinner(){
    for (int i=0;i<gameboard.length;i++){
      if(wins(joueur1,i,0,0,1)==true || wins(joueur1,0,i,1,0)==true || wins(joueur1,0,0,1,1)==true || wins(joueur1,0,2,1,-1)==true){ //check de rangées, colonnes et diagonales
        return joueur1;
      }
      else if (wins(joueur2,i,0,0,1)==true || wins(joueur2,0,i,1,0)==true || wins(joueur2,0,0,1,1)==true || wins(joueur2,0,2,1,-1)==true){
        return joueur2;
      }
    }
    return null;// => personne à win pour l'instant ou égalité
  }

  public boolean isOver(){
    int cpt=0;
    for(int i=0;i<gameboard.length;i++){
      for(int j=0;j<gameboard[i].length;j++){
        if (gameboard[i][j]==null){
          cpt+=1;
        }
      }
    }
    if (getWinner() != null){ //si on a un gagnant alors la partie est FINI LOGIQUE !
      return true;
    }
    else if(getWinner()==null && cpt!=0){ //si il y a un élément null dans le gameboard la partie n'est PAS FINI
    return false;
  }
  else if(getWinner()==null && cpt==0){ // si ya pas de gagnant mais que le tableau est plein alors EGALITE donc FINI
    return true;
  }
  return false;
}

public String moveToString(int coup){
  String chaine = "";
  int cpt=0;
  for (int i=0;i<gameboard.length;i++){
    for (int j=0;j<gameboard[i].length;j++){
      if (cpt==coup){
        chaine+=cpt+" = ("+i+","+j+")";
      }
      cpt+=1;
    }
  }

  return chaine;
}


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

  return chaine;
}

public static void main(String args[]){

  Scanner scanner = new Scanner(System.in);

  System.out.println("Bienvenue au jeu du TicTacToe, by Nathan GRIMAULT");
  System.out.println(" ");

  System.out.println("Joueur 1 quel est ton nom ?");
  String nom_j1=scanner.next();
  Player player1=new Player(nom_j1);
  System.out.println("Joueur 2 quel est ton nom ?");
  String nom_j2=scanner.next();
  Player player2=new Player(nom_j2);

  System.out.println("Voulez vous des indices ?");
  /*  if (answer.equals("oui")) {
      game = new TicTacToeWithHints(...);
  } else {
      game = new TicTacToe(...);
  }*/

  TicTacToeWithHints jeu=new TicTacToeWithHints(player1,player2);


  System.out.println(jeu.situationToString());

  while (jeu.isOver()==false){
    if(jeu.getCurrentPlayer() == player1){
      System.out.println(jeu.getCurrentPlayer().getName()+" ton symbole est le X, à toi de jouer ");
    }
    else{
      System.out.println(jeu.getCurrentPlayer().getName()+" ton symbole est le O, à toi de jouer ");
    }
    jeu.validMoves();
    System.out.println(" ");
    String coup_string=scanner.next();
    int coup=Integer.parseInt(coup_string);
    if (jeu.isValid(coup)==true){
      jeu.execute(coup); //parseInt ignore le 0 d'où le  "NullPointerExeption" peut-être
    }
    else{
      System.out.println("Le coup n'est pas valide recommence");
    }

    System.out.println(jeu.situationToString());
  }

  if (jeu.getWinner() != null){
    System.out.println("Bien joué "+jeu.getWinner().getName()+" tu as gagné cette partie ^_^"); //la partie est fini alors on affiche le winner
  }
  else{
    System.out.println("EGALITE");
  }

  scanner.close();

  }

}
