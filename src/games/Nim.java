package games;

import java.util.*;
import players.*;


public class Nim extends AbstractGame{

  protected int taille_initiale;
  protected int retire_max;
  protected int taille_courante;

  public Nim(int taille_initiale,int retire_max,players.Player joueur1,players.Player joueur2){
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
    System.out.println("");
    System.out.println("Représentation des allumettes restantes :");
    String situation = "Il reste "+taille_courante+" allumettes";
    String allumette = "---๐"; //Petit ajout bonus : représenter les allumettes restantes
    int cpt=0;
    for(int i=0;i<taille_courante;i++){
      cpt+=1;
      int reste=taille_courante-i;
      if(cpt==5){ //on affiche le allumettes par paquets de 5 maximum
        System.out.println("---๐ ---๐ ---๐ ---๐ ---๐");
        cpt=0;
      }
      else if(cpt==4 && reste<5){//on vérifie si on peut encore paquets les allumettes par 5
        System.out.println("---๐ ---๐ ---๐ ---๐");
        cpt=0;
      }
      else if(cpt==3 && reste<4){ //etc...
        System.out.println("---๐ ---๐ ---๐");
        cpt=0;
      }
      else if(cpt==2 && reste<3){
        System.out.println("---๐ ---๐");
        cpt=0;
      }
      else if(cpt==1 && reste<2){
        System.out.println("---๐");
        cpt=0;
      }

    }
    System.out.println("");
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
  public ArrayList<Integer> validMoves(){//OK
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
    return liste;
  }

  @Override
  public String moveToString(int coup){
    String coupS = Integer.toString(coup);
    return coupS;
  }

  public Game copy(){
    Nim res = new Nim(this.taille_initiale,this.retire_max,this.joueur1,this.joueur2);
    res.taille_courante = this.taille_courante;
    res.joueur_courant = super.joueur_courant;
    return res;
  }
}
