package main;

//javac -d "../build" main/Demo.java
//java -cp "../build" main.Demo


import players.Player;
import games.Game;
import java.util.*;

public class Demo{

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    System.out.print("\033[H\033[2J");//clear de console (CTRL+L)
    System.out.flush();//clear de console  (CTRL+L)

    System.out.println("Bonjour, combien de joueurs humain êtes-vous ? (1 ou 2) ");
    String nb_humains=scanner.next();
    System.out.println(" ");
    if(Integer.parseInt(nb_humains)==1){
      System.out.println("Voulez-vous un adversaire 'intelligent'(tapez : 1) ou qui joue de manière aléatoire (tapez : 2) ?");
      System.out.println("(NB : L'adversaire 'intelligent' ne fonctionne pas avec le puissance 4, désolé)");
      String IA=scanner.next();
      System.out.println(" ");
      if(Integer.parseInt(IA)==1){//1 joueur et 1 negamax
        System.out.println("Quel est ton nom ?");
        String nom_j1=scanner.next();
        System.out.println(" ");
        players.Player player1 = new players.Human(nom_j1,scanner);
        players.Player player2 = new players.NegamaxPlayer();

        System.out.println("A quoi veux-tu jouer "+nom_j1+" ? 1 : Nim, 2 : TicTacToe, 3 : Puissance4");
        String choix=scanner.next();
        System.out.println(" ");
        if(Integer.parseInt(choix)==1){//début de game
          System.out.println("Quel sera la taille du tas d'allumettes :");
          String taille_tas=scanner.next();
          System.out.println("Quel le nombre max d'allumettes que l'on pourras retirer lors d'un tour:");
          String max=scanner.next();

          games.Nim jeu = new games.Nim(Integer.parseInt(taille_tas),Integer.parseInt(max),player1,player2);

          plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
          orchestrator.play();
          scanner.close();
        }

        else if(Integer.parseInt(choix)==2){
          games.TicTacToe jeu = new games.TicTacToe(player1,player2);
          plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
          orchestrator.play();
          scanner.close();

        }
        else{
          System.out.println("Vous jouez contre un joueur qui joue aléatoirement");
          Random randInteger = new java.util.Random();//on génére un joueur random
          players.Player player3 = new players.RandomPlayer(randInteger);
          games.Puissance jeu = new games.Puissance(player1,player3);
          plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
          orchestrator.play();
          scanner.close();
        }

      }
      else{//1 joueur et 1 random
        System.out.println("Quel est ton nom ?");
        String nom_j1=scanner.next();
        Random randInteger = new java.util.Random();//on génére un joueur random
        players.Player player1 = new players.Human(nom_j1,scanner);
        players.Player player2 = new players.RandomPlayer(randInteger);

        System.out.println("A quoi veux-tu jouer "+nom_j1+" ? 1 : Nim, 2 : TicTacToe, 3 : Puissance4");
        String choix=scanner.next();
        if(Integer.parseInt(choix)==1){
          System.out.println("Quel sera la taille du tas d'allumettes :");
          String taille_tas=scanner.next();
          System.out.println("Quel le nombre max d'allumettes que l'on pourras retirer lors d'un tour:");
          String max=scanner.next();

          games.Nim jeu = new games.Nim(Integer.parseInt(taille_tas),Integer.parseInt(max),player1,player2);

          plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
          orchestrator.play();
          scanner.close();
        }

        else if(Integer.parseInt(choix)==2){
          games.TicTacToe jeu = new games.TicTacToe(player1,player2);
          plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
          orchestrator.play();
          scanner.close();

        }
        else{
          games.Puissance jeu = new games.Puissance(player1,player2);
          plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
          orchestrator.play();
          scanner.close();
        }
      }
    }
    else{ //2 joueurs
      System.out.println("Joueur n°1 quel est ton nom ?");
      String nom_j1=scanner.next();
      players.Player player1=new players.Human(nom_j1,scanner);
      System.out.println("Et toi joueur n°2 quel est ton nom ?");
      String nom_j2=scanner.next();
      players.Player player2=new players.Human(nom_j2,scanner);

      System.out.println("A quoi veux-tu jouer ? 1 : Nim, 2 : TicTacToe, 3 : Puissance4");
      String choix=scanner.next();
      if(Integer.parseInt(choix)==1){
        System.out.println("Quel sera la taille du tas d'allumettes :");
        String taille_tas=scanner.next();
        System.out.println("Quel le nombre max d'allumettes que l'on pourras retirer lors d'un tour:");
        String max=scanner.next();

        games.Nim jeu = new games.Nim(Integer.parseInt(taille_tas),Integer.parseInt(max),player1,player2);

        plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
        orchestrator.play();
        scanner.close();

      }
      else if(Integer.parseInt(choix)==2){
        games.TicTacToe jeu = new games.TicTacToe(player1,player2);
        plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
        orchestrator.play();
        scanner.close();

      }
      else{
        games.Puissance jeu = new games.Puissance(player1,player2);
        plays.Orchestrator orchestrator = new plays.Orchestrator(jeu);
        orchestrator.play();
        scanner.close();
      }
    }
  }
}
