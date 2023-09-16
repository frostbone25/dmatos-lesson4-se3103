package view;

//import the following java classes
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

//import our own classes
import controller.Application;
import controller.ButtonListener;
import controller.NewGameButtonListener;
import controller.StrategyButtonListener;
import model.Marking;
import model.PlayStrategy;
import model.TicTacToeGame;

public class ApplicationWindow extends JFrame 
{
    public static final String versusHumanAction = "vs. Human";
    public static final String versusComputerAction = "vs. Computer";

    private ApplicationCanvas canvas = new ApplicationCanvas();
    private BoardButton[] markingButtons = new BoardButton[9];
    private JButton newGameButton = new JButton("New Game");
    private JRadioButton vsHumanButton;
    private JRadioButton vsComputerButton;

    public void initalize() 
    {
        var contentPane = getContentPane();
        contentPane.add(canvas, BorderLayout.NORTH);

        ButtonListener buttonListener = new ButtonListener();

        for (int i = 0; i < markingButtons.length; i++) 
        {
            markingButtons[i] = new BoardButton(i);
            markingButtons[i].addActionListener(buttonListener);
        }

        JPanel gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new GridLayout(3, 3));

        for (BoardButton cell: markingButtons) 
        {
            gameBoardPanel.add(cell);
        }

        contentPane.add(gameBoardPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        contentPane.add(southPanel, BorderLayout.SOUTH);

        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new TitledBorder("Play strategy"));
        vsHumanButton = new JRadioButton(versusHumanAction, Application.ticTacToeGame.getStrategy() == PlayStrategy.VersusHuman);
        vsComputerButton = new JRadioButton(versusComputerAction, Application.ticTacToeGame.getStrategy() == PlayStrategy.VersusComputer);
        radioPanel.add(vsHumanButton);
        radioPanel.add(vsComputerButton);
        StrategyButtonListener strategyListener = new StrategyButtonListener();
        vsHumanButton.addActionListener(strategyListener);
        vsComputerButton.addActionListener(strategyListener);
        ButtonGroup strategyGroup = new ButtonGroup();
        strategyGroup.add(vsHumanButton);
        strategyGroup.add(vsComputerButton);
        southPanel.add(radioPanel);

        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(new TitledBorder("Action"));
        actionPanel.add(newGameButton);
        newGameButton.addActionListener(new NewGameButtonListener());
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        actionPanel.add(exitButton);
        southPanel.add(actionPanel);

        updateWindow();
    }

    public void updateWindow() 
    {
        TicTacToeGame game = Application.ticTacToeGame;
        Marking[] board = game.getBoard();

        for (int i = 0; i < board.length; i++) 
        {
            markingButtons[i].setMark(board[i]);
        }

        switch(game.getState()) 
        {
            case INITAL:
            case OVER:
                for (BoardButton button: markingButtons) 
                {
                    button.setEnabled(false);
                }

                newGameButton.setEnabled(true);
                vsHumanButton.setEnabled(true);
                vsComputerButton.setEnabled(true);

                break;
            case PLAYING:
                newGameButton.setEnabled(false);
                vsHumanButton.setEnabled(false);
                vsComputerButton.setEnabled(false);

                for (int i = 0; i < board.length; i++) 
                {
                    markingButtons[i].setEnabled(board[i] == Marking.U);
                }

                break;
        }

        canvas.repaint();
    }

}
