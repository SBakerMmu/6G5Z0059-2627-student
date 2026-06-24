package gamestates.states;


import gamestates.Context;
import gamestates.GameState;

public class GameOver implements GameState {
  private final Context context;

  GameOver(Context context) {
    this.context = context;
  }

  @Override
  public void turn() {
    System.out.println("GameOver");
  }

  @Override
  public boolean isGameOver() {
    return true;
  }
}
