package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controller.App;
import controller.ButtonListener;
import controller.NewGameButtonListener;
import controller.StrategyButtonListener;
import model.Marking;
import model.PlayStrategy;
import model.TicTacToeGame;

public class AppWindow extends JFrame {
    
    public static final String vsHumanAction = "vs. Human";
    public static final String VsComputerAction = "vs. Computer";

    private AppCanvas canvas = new AppCanvas();
    private BoardButton[] markingButtons = new BoardButton[9];
    private JButton newGameButton = new JButton("New Game");
    private JRadioButton vsHumanButton;
    private JRadioButton vsComputerButton;

    public void init() {
        var cp = getContentPane();
        cp.add(canvas, BorderLayout.NORTH);

        ButtonListener buttonListener = new ButtonListener();
        for (int i = 0; i < markingButtons.length; i++) {
            markingButtons[i] = new BoardButton(i);
            markingButtons[i].addActionListener(buttonListener);
        }

        JPanel gameBoardPanel = new JPanel();
        gameBoardPanel.setLayout(new GridLayout(3, 3));
        for (var cell: markingButtons) {
            gameBoardPanel.add(cell);
        }
        cp.add(gameBoardPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        cp.add(southPanel, BorderLayout.SOUTH);

        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new TitledBorder("Play strategy"));
        vsHumanButton = new JRadioButton(vsHumanAction, App.game.getStrategy() == PlayStrategy.VsHuman);
        vsComputerButton = new JRadioButton(VsComputerAction, App.game.getStrategy() == PlayStrategy.VsComputer);
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

    public void updateWindow() {
        TicTacToeGame game = App.game;
        Marking[] board = game.getBoard();
        for (int i = 0; i < board.length; i++) {
            markingButtons[i].setMark(board[i]);
        }

        switch(game.getState()) {
            case INIT:
            case OVER:
                for (var b: markingButtons) {
                    b.setEnabled(false);
                }
                newGameButton.setEnabled(true);
                vsHumanButton.setEnabled(true);
                vsComputerButton.setEnabled(true);
                break;
            case PLAYING:
                newGameButton.setEnabled(false);
                vsHumanButton.setEnabled(false);
                vsComputerButton.setEnabled(false);
                for (int i = 0; i <board.length; i++) {
                    markingButtons[i].setEnabled(board[i] == Marking.U);
                }
                break;
        }

        canvas.repaint();
    }

}
