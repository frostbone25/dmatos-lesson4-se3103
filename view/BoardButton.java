package view;

//import the following java classes
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

//import our own classes
import model.Marking;

public class BoardButton extends JButton 
{
    private int position; // index/pos in the game board

    public BoardButton(int pos) 
    {
        this.position = pos;

        setFont(new Font("Courier New", Font.BOLD, 84));
        setForeground(Color.BLUE);
        setMark(Marking.U);
    }

    public int getPosition() 
    {
        return position;
    }

    public void setMark(Marking mark) 
    {
        String label = mark.name();

        if (mark == Marking.U) 
            label = ".";

        setText(label);
    }
}