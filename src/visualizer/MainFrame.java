package visualizer;

import scripts.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {

    public Map map;
    public int size;
    public DrawTile[][] jTiles;
    private JLabel score;

    public MainFrame() {
        super("2048");
        this.map = new Map();
        this.size = 4;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        addKeyListener(this);
        setLayout(new BorderLayout());


        JLabel info = new JLabel("Press R to restart");
        info.setFont(new Font("Verdana",1,20));
        this.score = new JLabel("Current score: " + this.map.getScore());
        this.score.setFont(new Font("Verdana",1,30));

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(this.size, this.size, 4,4));
        this.jTiles = new DrawTile[this.size][this.size];
        for (int i=0; i < this.size; i++) {
            for(int j=0; j<this.size; j++) {
                jTiles[j][i] = new DrawTile(map.tiles[j][i]);
                board.add(jTiles[j][i]);
            }
        }

        add(info, BorderLayout.SOUTH);
        add(this.score, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);

        setVisible(true);
    }

    private void updateTiles() {
        for (int i=0; i < this.size; i++) {
            for(int j=0; j<this.size; j++) {
                jTiles[j][i].updateText();
            }
        }
        this.score.setText("Current score: " + this.map.getScore());
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            this.map.moveUp();
            repaint();
            this.updateTiles();
        }
        if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            this.map.moveDown();
            repaint();
            this.updateTiles();
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            this.map.moveLeft();
            repaint();
            this.updateTiles();
        }
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.map.moveRight();
            repaint();
            this.updateTiles();
        }

        if (event.getKeyCode() == KeyEvent.VK_R) {
            this.map.resetMap();
            repaint();
            this.updateTiles();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }
}
