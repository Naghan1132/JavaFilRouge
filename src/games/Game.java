package games;

import players.*;
import java.util.*;

public interface Game{

  public abstract void execute(int coup); //OK
  public abstract Player getCurrentPlayer(); //OK
  public abstract ArrayList<Integer> validMoves();
  public abstract boolean isValid(int coup);
  public abstract String situationToString();
  public abstract String moveToString(int coup);
  public abstract boolean isOver();
  public abstract Player getWinner();

  //negamax
  public abstract Game copy();


}
