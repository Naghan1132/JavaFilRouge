package secondgames;

//import players.*;
import java.util.*;

public class Orchestrator{

    public void playGame(AbstractGame jeu){
      Scanner scanner2 = new Scanner(System.in);
      while (jeu.isOver()==false){
        System.out.println(" ");
        System.out.println("C'est à "+jeu.getCurrentPlayer().getName()+" de jouer ");
        System.out.println(jeu.validMoves());
        System.out.println(" ");
        System.out.println(jeu.situationToString());
        System.out.println("Entre ton coup "+jeu.getCurrentPlayer().getName()+" : ");
        String chaine=scanner2.next();
        int coup = Integer.parseInt(chaine);
        if (jeu.isValid(coup)==true){
          System.out.println("Tu as choisi : "+jeu.moveToString(coup));//truc
          jeu.execute(coup);
        }
        else{
          System.out.println("Le coup n'est pas valide, recommence");
        }

    }//partie terminée => on sort de la boucle
    System.out.println(jeu.situationToString());
    if (jeu.getWinner()!=null){
      System.out.println("Bien joué "+jeu.getWinner().getName()+", tu es le grand gagnant de ce jeu ^_^");
    }

    else{
      System.out.println("Egalité, dommage ^_^");
    }
    scanner2.close();
  }


      public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        Orchestrator jeu = new Orchestrator();

        System.out.println("Joueur 1 quel est ton nom ?");
        String nom_j1=scanner.next();
        Player player1=new Player(nom_j1);// new players.Human(nom_j1, scanner)
        System.out.println("Joueur 2 quel est ton nom ?");
        String nom_j2=scanner.next();
        Player player2=new Player(nom_j2);

        System.out.println("Tape 1 pour le jeu de Nim ou 2 pour le Morpion :");
        String choixS=scanner.next();
        int choix = Integer.parseInt(choixS);
        if (choix==1){
          System.out.println(" ");
          System.out.println("Bienvenue "+player1.getName()+" et "+player2.getName()+" au jeu de Nim");
          System.out.println(" ");
          System.out.println("Quel sera la taille du tas d'allumettes :");
          String taille_tas=scanner.next();
          System.out.println("Quel le nombre max d'allumettes que l'on pourras retirer lors d'un tour:");
          String max=scanner.next();
          Nim nim = new Nim(Integer.parseInt(taille_tas),Integer.parseInt(max),player1,player2); //objet de type AbstractGame
          jeu.playGame(nim);
        }
        else{
          System.out.println(" ");
          System.out.println("Bienvenue "+player1.getName()+" et "+player2.getName()+" au jeu du TicTacToe");
          System.out.println(" ");
          TicTacToe morpion = new TicTacToe(player1,player2); //objet de type AbstractGame
          jeu.playGame(morpion);
        }

        scanner.close();
      }
}
