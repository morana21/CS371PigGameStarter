package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

   PigGameState pigGameState;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        pigGameState = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if(pigGameState.turn == playerIdx) return true;
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        if(action instanceof PigHoldAction)
        {
            if(pigGameState.getTurn() == 0)
            {
                pigGameState.setP0_score(pigGameState.getHold_total() + pigGameState.getP0_score());
                pigGameState.setHold_total(0);
                pigGameState.setTurn(1);
                return true;
            }
            else if(pigGameState.getTurn() == 1)
            {
                pigGameState.setP1_score(pigGameState.getHold_total() + pigGameState.getP1_score());
                pigGameState.setHold_total(0);
                pigGameState.setTurn(0);
                return true;
            }
        }
        else if(action instanceof PigRollAction)
        {
            Random rand = new Random();
            int die = rand.nextInt(6) + 1;
            if(die == 1)
            {
                pigGameState.setHold_total(0);
                if(playerNames.length == 2)
                {
                    if(pigGameState.getTurn() == 0) pigGameState.setTurn(1);
                    else if(pigGameState.getTurn() == 1) pigGameState.setTurn(0);
                }
            }
            else pigGameState.setHold_total(pigGameState.getHold_total() + die);
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState temp = new PigGameState(pigGameState.getTurn(), pigGameState.getP0_score(), pigGameState.getP1_score(), pigGameState.getHold_total());
        p.sendInfo(temp);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if(pigGameState.getP0_score() >= 50)
        {
            return playerNames[0] + " : " + pigGameState.getP0_score();
        }
        else if(pigGameState.getP1_score() >= 50)
        {
            return playerNames[1] + " : " + pigGameState.getP1_score();
        }
        return null;
    }

}// class PigLocalGame
