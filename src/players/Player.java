package players;

import games.Game;
import java.util.*;

//une interface est comme un contrat :
//Il faut que les classes implémentées possèdent AU MOINS chooseMove()
//pour être un joueur (pour faire partie de l'interface Player)

public interface Player{//que des méthodes abstraites dans une interface donc pas de méthodes avec de corps

  public abstract int chooseMove(Game jeu);

}
