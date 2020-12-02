package games;

import java.util.*;
import players.Player;

public class Puissance extends AbstractGame{

  private Player[][] gameboard;

  public Puissance(Player joueur1,Player joueur2){
    super(joueur1,joueur2);
    this.gameboard = new Player[6][7];//Player[row][column]
  }

  public void doExecute(int coup){
    int cpt=0;
    for (int i=0;i<gameboard.length;i++){
      for (int j=0;j<gameboard[i].length;j++){
        if (cpt == coup){
          gameboard[i][j]=joueur_courant;
        }
        cpt+=1;
      }
    }
  }

  public boolean isValid(int coup){//KO
    int cpt=0;
    for(int i=0;i<gameboard.length;i++){
      for(int j=0;j<gameboard[i].length;j++){
        if (cpt==coup && i==0){//si le coup est dans la première ligne
          if(gameboard[i][j]==null){
            return true;
          }
        }
        else if(cpt==coup && i!=0){
          if(gameboard[i][j]==null && gameboard[i-1][j]!=null){
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

  public ArrayList<Integer> validMoves(){ //OK => la longueur max de 'liste' est 7, car 7 collones
    ArrayList<Integer> liste = new ArrayList<>();
    int cpt=0;
    System.out.println("Coups valides : ");
    for (int i=0;i<gameboard.length;i++){
      for (int j=0;j<gameboard[i].length;j++){
        if(i>=1){
          if(gameboard[i][j]==null && gameboard[i-1][j]!=null){
            liste.add(cpt);
            System.out.println(cpt+": ("+i+","+j+")");
          }
        }
        else{// si i == 0 (première ligne), donc tout les coups valides si gameboard[i][j]==null
          if(gameboard[i][j]==null){
            liste.add(cpt);
            System.out.println(cpt+": ("+i+","+j+")");
          }
        }
        cpt+=1;
      }
    }
    return liste;
  }

  public boolean wins(Player joueur,int row, int column,int deltaRow,int deltaColumn){ //delta = -1 ou 0 ou +1
    if(gameboard[row][column]==joueur && gameboard[row+deltaRow][column+deltaColumn]==joueur && gameboard[row+2*deltaRow][column+2*deltaColumn]==joueur && gameboard[row+3*deltaRow][column+3*deltaColumn]==joueur){
      return true;
    }
    else{
      return false;
    }
  }

  public Player getWinner(){// On sépare le gameboard en 4 carré et la collone du millieu qui reste
    for (int i=0;i<gameboard.length;i++){
      for (int j=0;j<gameboard[i].length;j++){
        if(0<=i && i<=2 && 0<=j && j<=2){//carré bas à gauche OK
          if(wins(joueur1,i,j,1,1)==true || wins(joueur1,i,j,0,1)==true || wins(joueur1,i,j,1,0)==true){
            return joueur1;
          }
          else if(wins(joueur2,i,j,1,1)==true || wins(joueur2,i,j,0,1)==true || wins(joueur2,i,j,1,0)==true){
            return joueur2;
          }
        }
        else if(3<=i && i<=5 && 0<=j && j<=2){//carré haut a gauche OK
          if(wins(joueur1,i,j,-1,1)==true || wins(joueur1,i,j,0,1)==true || wins(joueur1,i,j,-1,0)==true){
            return joueur1;
          }
          else if(wins(joueur2,i,j,-1,1)==true || wins(joueur2,i,j,0,1)==true || wins(joueur2,i,j,-1,0)==true){
            return joueur2;
          }
        }
        else if(3<=i && i<=5 && 4<=j && j<=6){//carré haut à droite OK
          if(wins(joueur1,i,j,-1,-1)==true || wins(joueur1,i,j,0,-1)==true || wins(joueur1,i,j,-1,0)==true){
            return joueur1;
          }
          else if(wins(joueur2,i,j,-1,-1)==true || wins(joueur2,i,j,0,-1)==true || wins(joueur2,i,j,-1,0)==true){
            return joueur2;
          }
        }
        else if(0<=i && i<=2 && 4<=j && j<=6){//carré bas à droite OK
          if(wins(joueur1,i,j,1,-1)==true || wins(joueur1,i,j,0,-1)==true || wins(joueur1,i,j,1,0)==true){
            return joueur1;
          }
          else if(wins(joueur2,i,j,1,-1)==true || wins(joueur2,i,j,0,-1)==true || wins(joueur2,i,j,1,0)==true){
            return joueur2;
          }
        }
        else if(0<=i && i<=2 && j==3){//collonne milleu bas => diagonales haut gauche et haut droite , gauche/droite et haut OK
          if(wins(joueur1,i,j,1,1)==true || wins(joueur1,i,j,1,-1)==true || wins(joueur1,i,j,1,0)==true || wins(joueur1,i,j,0,1)==true || wins(joueur1,i,j,0,-1)==true){
            return joueur1;
          }
          else if(wins(joueur2,i,j,1,1)==true || wins(joueur2,i,j,1,-1)==true || wins(joueur2,i,j,1,0)==true || wins(joueur2,i,j,0,1)==true || wins(joueur2,i,j,0,-1)==true){
            return joueur2;
          }
        }
        else if(3<=i && i<=5 && j==3){//collonne milleu haut => diagonales bas gauche et bas droit, gauche/droite et bas OK
          if(wins(joueur1,i,j,-1,1)==true || wins(joueur1,i,j,-1,-1)==true || wins(joueur1,i,j,-1,0)==true || wins(joueur1,i,j,0,-1)==true || wins(joueur1,i,j,0,1)==true){
            return joueur1;
          }
          else if(wins(joueur2,i,j,-1,1)==true || wins(joueur2,i,j,-1,-1)==true || wins(joueur2,i,j,-1,0)==true || wins(joueur2,i,j,0,-1)==true || wins(joueur2,i,j,0,1)==true){
            return joueur2;
          }
        }
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
    if (getWinner() != null){ //si on a un gagnant alors la partie est fini, LOGIQUE !
      return true;
    }
    else if(getWinner()==null && cpt!=0){ //si il y a un élément null dans le gameboard et pas de gagnant la partie n'est PAS FINI
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
    System.out.println("");
    return chaine;
  }

  public String situationToString(){//OK
    String[][] gameboard2 = new String[6][7];
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

    String chaine1=gameboard2[0][0]+" "+gameboard2[0][1]+" "+gameboard2[0][2]+" "+gameboard2[0][3]+" "+gameboard2[0][4]+" "+gameboard2[0][5]+" "+gameboard2[0][6];
    String chaine2=gameboard2[1][0]+" "+gameboard2[1][1]+" "+gameboard2[1][2]+" "+gameboard2[1][3]+" "+gameboard2[1][4]+" "+gameboard2[1][5]+" "+gameboard2[1][6];
    String chaine3=gameboard2[2][0]+" "+gameboard2[2][1]+" "+gameboard2[2][2]+" "+gameboard2[2][3]+" "+gameboard2[2][4]+" "+gameboard2[2][5]+" "+gameboard2[2][6];
    String chaine4=gameboard2[3][0]+" "+gameboard2[3][1]+" "+gameboard2[3][2]+" "+gameboard2[3][3]+" "+gameboard2[3][4]+" "+gameboard2[3][5]+" "+gameboard2[3][6];
    String chaine5=gameboard2[4][0]+" "+gameboard2[4][1]+" "+gameboard2[4][2]+" "+gameboard2[4][3]+" "+gameboard2[4][4]+" "+gameboard2[4][5]+" "+gameboard2[4][6];
    String chaine6=gameboard2[5][0]+" "+gameboard2[5][1]+" "+gameboard2[5][2]+" "+gameboard2[5][3]+" "+gameboard2[5][4]+" "+gameboard2[5][5]+" "+gameboard2[5][6];

    String chaine=System.lineSeparator();
    chaine+=chaine6;
    chaine+=System.lineSeparator();
    chaine+=chaine5;
    chaine+=System.lineSeparator();
    chaine+=chaine4;
    chaine+=System.lineSeparator();
    chaine+=chaine3;
    chaine+=System.lineSeparator();
    chaine+=chaine2;
    chaine+=System.lineSeparator();
    chaine+=chaine1;
    chaine+=System.lineSeparator();
    return chaine;
  }

  public Game copy(){
    Puissance res = new Puissance(this.joueur1,this.joueur2);
    res.joueur_courant = super.joueur_courant;
    res.gameboard = new Player[6][7];

    for(int i=0 ; i<gameboard.length ; i++){
      for (int j=0 ; j<gameboard[i].length ; j++){
        res.gameboard[i][j] = this.gameboard[i][j];
      }
    }
    return res;
  }

}//fin de classe
