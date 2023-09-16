package controller;

//import the following java classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import our own classes
import model.PlayStrategy;
import view.ApplicationWindow;

public class StrategyButtonListener implements ActionListener 
{
    @Override
    public void actionPerformed(ActionEvent actionEvent) 
    {
        switch(actionEvent.getActionCommand()) 
        {
            case ApplicationWindow.versusHumanAction:
                Application.ticTacToeGame.setStrategy(PlayStrategy.VersusHuman);
                break;
            case ApplicationWindow.versusComputerAction:
                Application.ticTacToeGame.setStrategy(PlayStrategy.VersusComputer);
                break;
        }
    }
}