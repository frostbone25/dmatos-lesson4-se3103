package controller;

//import the following java classes
import javax.swing.JFrame;

//import our own classes
import model.TicTacToeGame;
import view.ApplicationWindow;

public class Application 
{
    public static ApplicationWindow applicationWindow = new ApplicationWindow();
    public static TicTacToeGame ticTacToeGame = new TicTacToeGame();

    public static void main(String[] args) 
    {
        applicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationWindow.initalize();
        applicationWindow.setLocation(300, 200);
        applicationWindow.pack();
        applicationWindow.setVisible(true);
    }
}