package firstgames;

import java.util.*;

public class Nim{

  private int taille_initiale;
  private int retire_max;
  private Player joueur1;
  private Player joueur2;
  private Player joueur_courant;
  private int taille_courante;

    public Nim(int taille_initiale,int retire_max,Player joueur1,Player joueur2){
      this.taille_initiale=taille_initiale;// = 20
      this.retire_max=retire_max; // = 3
      this.joueur1=joueur1;
      this.joueur2=joueur2;
      this.taille_courante=taille_initiale; //pour le premier tour
      this.joueur_courant=joueur1; //il faut le premier joueur a entré dans le constructeur
    }


    public int getInitialNbMatches(){
      return taille_initiale;
    }
    public int getCurrentNbMatches(){
      return taille_courante;
    }
    public String getCurrentPlayer(){
      return joueur_courant.getName();
    }
    public String situationToString(){
      String situation = "Il reste "+taille_courante+" allumettes";
      return situation;
    }
    public int removeMatches(int nb_allumettes){
      taille_courante-=nb_allumettes;
      if (joueur_courant==joueur1){
        joueur_courant=joueur2;
      }
      else{
        joueur_courant=joueur1;
      }

      return taille_courante;
    }

    public boolean isValid(int nb_allumettes){
      if (nb_allumettes<=retire_max && nb_allumettes>0 && nb_allumettes<=taille_courante){
        return true;
      }
      else{
        return false;
      }

    }

    public boolean isOver(){
      if (taille_courante==0){
        return true;
      }
      else{
        return false;
      }
    }

    public String getWinner(){
      if (isOver()==true){
        if (joueur_courant==joueur1){
            return joueur1.getName();
          }
          else{
            return joueur2.getName();
          }
      }
      else{
        return null;
      }
    }

    public static void main(String args[]){

      Scanner scanner = new Scanner(System.in);

      System.out.println("Bonjour joueur1 quel est ton nom :");
      String joueur1=scanner.next();
      Player player1=new Player(joueur1);
      System.out.println("Et toi joueur2 quel est ton nom :");
      String joueur2=scanner.next();
      Player player2=new Player(joueur2);

      System.out.println("Quel sera la taille du tas d'allumettes :");
      String taille_tas=scanner.next();
      System.out.println("Quel le nombre max d'allumettes que l'on pourras retirer lors d'un tour:");
      String max=scanner.next();
      Nim jeu = new Nim(Integer.parseInt(taille_tas),Integer.parseInt(max),player1,player2);
      System.out.println("Bienvenue "+player1.getName()+" et "+player2.getName()+" au Jeu de Nim : ");


      while (jeu.isOver()==false){
      System.out.println(jeu.situationToString());
      System.out.println("C'est à "+jeu.getCurrentPlayer()+" de jouer, combien d'allumettes veut-tu enlever ?");
      String chaine=scanner.next();
      int entier = Integer.parseInt(chaine);
      if (jeu.isValid(entier)==true){
        jeu.removeMatches(entier);
      }
      else{
        System.out.println("Le coup n'est pas valide, recommence");
      }

    }//partie terminée => on sort de la boucle

    System.out.println("Bien joué "+jeu.getWinner()+", tu es le grand gagnant du Jeu de Nim");

    scanner.close();

    }
}
