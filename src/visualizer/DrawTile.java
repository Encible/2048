package visualizer;

import scripts.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DrawTile extends JPanel {

    private Tile tile;
    private JLabel label;


    DrawTile(Tile tile) {
        setLayout(new CardLayout());
        this.tile = tile;
        this.label = new JLabel(this.tile.toString(), SwingConstants.CENTER);
        this.label.setFont(new Font("Verdana",1,40));
        add(this.label);
        setBorder(new LineBorder(Color.BLACK));
    }

    public void updateText() {
        this.label.setText(this.tile.toString());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!this.tile.isEmpty())
            setBackground(this.getColor());
        else
            setBackground(Color.white);
    }

    private Color getColor() {
        int number = this.tile.getNumber();
        if(number == 2) return new Color (238,228,218);
        if(number == 4) return new Color (237,224,200);
        if(number == 8) return new Color ( 242,177 ,121 );
        if(number == 16) return new Color (245,149 ,99 );
        if(number == 32) return new Color (246,124,95);
        if(number == 64) return new Color (146,94,59);
        if(number == 128) return new Color (237,207,114);
        if(number == 256) return new Color (237,204,97);
        if(number == 512) return new Color (237,200,80);
        if(number == 1024) return new Color (237,197,63);
        if(number == 2048) return new Color (237,194,46);
        else return new Color (237,200,150); //for anything over than 2048
    }

}
