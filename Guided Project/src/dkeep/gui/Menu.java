package dkeep.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JPanel {

    private GraphicsBank graphics;
    private JOptionPane gameFilesPane;

    Menu() throws IOException {
        graphics = new GraphicsBank();
        graphics.loadGraphics();
        inicializeButtons();

        JTextField filename = new JTextField();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(graphics.getMenu(), 0, 0, this.getWidth(), this.getHeight(), this);
        gameFilesPane = new JOptionPane();
    }

    public void inicializeButtons() throws IOException{

        JButton btnNewGame = new JButton("Play");
        btnNewGame.setBounds(600/2-50,500/2-45, 110, 45);
        btnNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DungeonKeep.newGamePressed();
                DungeonKeep.graphicsPanel.requestFocusInWindow();
            }
        });
        this.add(btnNewGame);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(600/2-50,500/2+150, 110, 45);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
                requestFocusInWindow();

            }
        });
        this.add(btnExit);



        JButton loadGame = new JButton("Load Game");
        loadGame.setBounds(600/2-50,500/2+50, 110, 45);
        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try{DungeonKeep.game = DungeonKeep.loadGame(JOptionPane.showInputDialog("Insert name for file:"));
                    DungeonKeep.graphicsPanel.buttonsHandler('d');
                    DungeonKeep.graphicsPanel.repaint();
                }
                catch (ClassNotFoundException e){}
                catch ( IOException e) {}


            }
        });
        this.add(loadGame);

        JButton saveGame = new JButton("Save Game");
        saveGame.setBounds(600/2-50,500/2+5, 110, 45);
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(DungeonKeep.getGame() == null) {
                    JOptionPane.showMessageDialog(null, "No game was started.");
                return;
                }
                DungeonKeep.saveGame(JOptionPane.showInputDialog("Insert name for file:"), DungeonKeep.getGame());
            }
        });
        this.add(saveGame);


        JButton design = new JButton("Design Map");
        design.setBounds(600/2-50,500/2+100, 110, 45);
        design.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DungeonKeep.designMenuPressed();
                requestFocusInWindow();

            }
        });
        this.add(design);



    }

}
