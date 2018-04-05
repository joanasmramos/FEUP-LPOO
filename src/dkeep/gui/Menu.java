package dkeep.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JPanel {

    private GraphicsBank graphics;

    Menu() throws IOException{
        graphics = new GraphicsBank();
        graphics.loadGraphics();
        inicializeButtons();

    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(graphics.getMenu(), 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void inicializeButtons() {

        JButton btnNewGame = new JButton("New Game");
        btnNewGame.setBounds(600/2-50,500/2-15, 110, 45);
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DungeonKeep.newGamePressed();
                DungeonKeep.graphicsPanel.requestFocusInWindow();
            }
        });
        this.add(btnNewGame);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(600/2-50,500/2+50, 110, 45);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
                requestFocusInWindow();

            }
        });
        this.add(btnExit);

        JButton btnDesignMap = new JButton("Design Map");
        btnDesignMap.setBounds(600/2-50,500/2+100, 110, 45);
        btnDesignMap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DungeonKeep.designMenuPressed();
                requestFocusInWindow();

            }
        });
        this.add(btnDesignMap);


    }

}
