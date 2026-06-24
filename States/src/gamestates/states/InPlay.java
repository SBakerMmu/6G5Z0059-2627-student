package gamestates.states;


import gamestates.Context;
import gamestates.GameState;

class InPlay implements GameState {

  private final Context context;
  private int turns;

  InPlay(Context context) {
    this.context = context;
  }

  @Override
  public void turn() {
    playTurn();
    if(turns == 3)
    {
      context.setGameState(new GameOver(context));
    }
  }

  private void playTurn() {
    ++turns;
    System.out.format("turn %d%n", turns);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }
}
