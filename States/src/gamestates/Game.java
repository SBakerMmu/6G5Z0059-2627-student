package gamestates;

import gamestates.states.GameOver;
import gamestates.states.ReadyToPlay;

class Game implements Context {

  private GameState gameState;

  Game() {
    gameState = new ReadyToPlay(this);
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public void play() {
    while (!gameState.isGameOver()) {
      gameState.turn();
    }
    //show what happens if we play a turn when game over
    gameState.turn();
  }


}
