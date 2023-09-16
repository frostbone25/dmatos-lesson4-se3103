package model;

public class TicTacToeGame {
    private Marking[] board = new Marking[9];
    private Marking turn = Marking.X;
    private int moves = 0;
    private Marking winner = null;
    private GameState state = GameState.INIT;
    private PlayStrategy strategy = PlayStrategy.VsHuman;

    public TicTacToeGame() {
        reset();
    }
    
    public void reset() {
        for(int i = 0; i <board.length; i++){
            board[i] = Marking.U;
        }

        turn = Marking.X;
        moves = 0;
        winner = null;
    }

    public void changeTurns() {
        turn = (turn == Marking.X) ? Marking.O : Marking.X;
    }

    public Marking getTurn() {
        return turn;
    }

    public void play(int position) {
        if (strategy == PlayStrategy.VsHuman) {
            humanPlayer(position);
            setWinner();
        } else if (strategy == PlayStrategy.VsComputer) {
            humanPlayer(position);
            setWinner();
            if (getWinner() != null) return;
            changeTurns();
            computerPlayer();
            setWinner();
        }
    }

    public Marking getWinner() {
        return winner;
    }

    private void computerPlayer() {
        int pos = computerPick();
        board[pos] = turn;
        ++moves;
    }

    private int computerPick() {
        int pos = -1;
        for (int i = 0; i < board.length; i++) {
            if(board[i] == Marking.U) {
                pos = i;
                break;
            }
        }
        assert pos >= 0 : "Invalid position from computerPick()";
        return pos;
    }

    private void humanPlayer(int pos) {
        board[pos] = turn;
        ++moves;
    }

    public void setWinner() {
        for (int i = 0; i < 3; i++){
            winner = checkCol(i);
            if (winner != null) {
                return;
            }
            winner = checkRow(i);
            if (winner != null) {
                return;
            }
        }
        winner = checkDiag1();
        if (winner != null) return;
        winner = checkDiag2();
        if (winner != null) return;

        if (moves == 9) {
            winner = Marking.U; // draw
            return;
        }
        winner = null;
    }

    private Marking checkRow(int n) {
        int r = n * 3;
        if (board[r] != Marking.U &&
            board[r] == board[r+1] &&
            board[r] == board[r+2]) {
                return board[r];
        } else {
            return null; // no winner
        }
    }

    private Marking checkCol(int n) {
        if (board[n] != Marking.U &&
                board[n] == board[n + 3] &&
                board[n] == board[n + 6]) {
            return board[n];
        } else {
            return null;
        }
    }

    private Marking checkDiag1() {
        if(board[0] != Marking.U && board[0] == board[4] 
            && board[0] == board[8]) {
            return board[0];
        } else {
            return null;
        }
    }

    private Marking checkDiag2() {
        if(board[2] != Marking.U && board[2] == board[4] 
            && board[2] == board[6]) {
            return board[2];
        } else {
            return null;
        }
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public PlayStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PlayStrategy strategy) {
        this.strategy = strategy;
    }

    public Marking[] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        var r1 = String.format("%s %s %s\n", board[0], board[1], board[2]);
        var r2 = String.format("%s %s %s\n", board[3], board[4], board[5]);
        var r3 = String.format("%s %s %s\n", board[6], board[7], board[8]);
        var r4 = String.format("Winner: %s (moves: %d)\n", winner, moves);
        return r1 + r2 + r3 + r4;
    }
}
