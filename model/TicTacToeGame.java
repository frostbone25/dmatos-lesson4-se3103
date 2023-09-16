package model;

public class TicTacToeGame {
    private Marking[] board = new Marking[9];
    private Marking turn = Marking.X;
    private int moves = 0;
    private Marking winner = null;
    private GameState state = GameState.INIT;
    private PlayStrategy strategy = PlayStrategy.VsHuman;

    public TicTacToeGame() {

    }
    
    public void reset() {
        for(int i = 0; i <board.length; i++){
            board[i] = Marking.U;
        }

        turn = Marking.X;
        moves = 0;
        winner = null;
    }
}
