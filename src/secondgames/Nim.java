package secondgames;

import java.util.*;

public class Nim extends AbstractGame{

  protected int taille_initiale;
  protected int retire_max;
  protected int taille_courante;

    public Nim(int taille_initiale,int retire_max,Player joueur1,Player joueur2){
      super(joueur1,joueur2);
      this.taille_initiale=taille_initiale;
      this.retire_max=retire_max;
      this.taille_courante=taille_initiale;

    }

    public int getInitialNbMatches(){//OK
      return taille_initiale;
    }
    public int getCurrentNbMatches(){//OK
      return taille_courante;
    }

    @Override
    public String situationToString(){ //OK
      String situation = "Il reste "+taille_courante+" allumettes";
      return situation;
    }

    public void doExecute(int nb_allumettes){//OK
      taille_courante-=nb_allumettes;
    }

    @Override
    public boolean isValid(int nb_allumettes){//OK
      if (nb_allumettes<=retire_max && nb_allumettes>0 && nb_allumettes<=taille_courante){
        return true;
      }
      else{
        return false;
      }
    }

    @Override
    public boolean isOver(){//OK
      if (taille_courante==0){
        return true;
      }
      else{
        return false;
      }
    }

    @Override
    public Player getWinner(){//OK
      if (isOver()==true){
        if (joueur_courant==joueur1){
            return joueur1;
          }
          else{
            return joueur2;
          }
      }
      else{
        return null;
      }
    }

    @Override
    public ArrayList<Integer> validMoves(){
      ArrayList<Integer> liste = new ArrayList<Integer> ();
      if(taille_courante>=retire_max){
        for(int i=1;i<=retire_max;i++){
          liste.add(i);
        }
      }
      else{
        for(int i=1;i<=taille_courante;i++){
          liste.add(i);
        }
      }
      System.out.println("Coups valides : ");
      return liste;
    }

    @Override
    public String moveToString(int coup){
      String coupS = Integer.toString(coup);
      return coupS;
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

      //début de la boucle de jeu
      while (jeu.isOver()==false){
      System.out.println(jeu.situationToString());
      System.out.println(jeu.validMoves());
      System.out.println("C'est à "+jeu.getCurrentPlayer().getName()+" de jouer, combien d'allumettes veut-tu enlever ?");
      String chaine=scanner.next();
      int entier = Integer.parseInt(chaine);
      if (jeu.isValid(entier)==true){
        jeu.execute(entier);
      }
      else{
        System.out.println("Le coup n'est pas valide, recommence");
      }

    }//partie terminée => on sort de la boucle

    System.out.println("Bien joué "+jeu.getWinner().getName()+", tu es le grand gagnant du Jeu de Nim");

    scanner.close();

    }
}
