package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayStrategy;
import view.AppWindow;

public class StrategyButtonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        var actionCommand = e.getActionCommand();
        switch(actionCommand) {
            case AppWindow.vsHumanAction:
                App.game.setStrategy(PlayStrategy.VsHuman);
                break;
            case AppWindow.VsComputerAction:
                App.game.setStrategy(PlayStrategy.VsComputer);
                break;
        }
    }
    
}
