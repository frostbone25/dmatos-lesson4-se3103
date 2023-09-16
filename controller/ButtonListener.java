package controller;

//import the following java classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import our own classes
import model.GameState;
import model.TicTacToeGame;
import view.BoardButton;

public class ButtonListener implements ActionListener 
{
    @Override
    public void actionPerformed(ActionEvent actionEvent) 
    {
        TicTacToeGame ticTacToeGame = Application.ticTacToeGame;
        BoardButton boardButton = (BoardButton)actionEvent.getSource();

        ticTacToeGame.play(boardButton.getPosition());

        if (ticTacToeGame.getWinner() != null) 
        {
            ticTacToeGame.setState(GameState.OVER);
            System.out.println("Game Over: " + ticTacToeGame.getWinner());
        } 
        else 
        {
            ticTacToeGame.changeTurns();
        }

        Application.applicationWindow.updateWindow();
    }
}