package controller;

//import the following java classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import our own classes
import model.GameState;

public class NewGameButtonListener implements ActionListener 
{
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        Application.ticTacToeGame.reset();
        Application.ticTacToeGame.setState(GameState.PLAYING);
        Application.applicationWindow.updateWindow();
    }
}