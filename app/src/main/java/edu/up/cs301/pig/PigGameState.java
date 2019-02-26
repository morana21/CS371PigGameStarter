package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.*;

public class PigGameState extends GameState{
    int turn;
    int p0_score;
    int p1_score;
    int hold_total;

    public PigGameState ()
    {
        turn = 0;
        p0_score = 0;
        p1_score = 0;
        hold_total = 0;
    }

    public PigGameState (int t, int p0, int p1, int hold)
    {
        turn = t;
        p0_score = p0;
        p1_score = p1;
        hold_total = hold;
    }

    public PigGameState getGameState () {  return new PigGameState(turn, p0_score, p1_score, hold_total);  }

    public int getTurn () { return turn; }

    public int getP0_score () { return p0_score;  }

    public int getP1_score () { return p1_score;  }

    public int getHold_total () { return hold_total;  }

    public void setTurn (int turn) { this.turn = turn;  }

    public void setP0_score (int p0_score) { this.p0_score = p0_score;  }

    public void setP1_score (int p1_score) { this.p1_score = p1_score;  }

    public void setHold_total (int hold_total) { this.hold_total = hold_total;  }
}
