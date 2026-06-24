package gamestates.states;

import gamestates.Context;
import gamestates.GameState;

public class ReadyToPlay  implements GameState {

  private final Context context;

  public ReadyToPlay(Context context) {
    this.context = context;
  }

  @Override
  public void turn() {
    GameState nextState = new InPlay(context);
    nextState.turn();
    context.setGameState(nextState);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }
}
