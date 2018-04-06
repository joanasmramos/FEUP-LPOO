package dkeep.gui;

import dkeep.cli.Interaction;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.Object;

import dkeep.logic.*;


 class GraphicsDemo extends JPanel implements KeyListener {

    private GraphicsBank graphics;
    private char[][] map;
    private  JTextField ogresnr;
    private static JComboBox<String> guard;
    private static JButton moveleft, moveright, moveup, movedown, start, custom;
    GameState game;



     GraphicsDemo() throws IOException{
        map = null;
        graphics =  new GraphicsBank();
        addKeyListener(this);
        inicializeButtons();
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        int width = 30;
        int height = 30;
        int x = 30;
        int y = 80;
        if(map==null) return;


        for (int i = 0; i<map.length;i++) {
            for (int j = 0; j<map[i].length; j++){
                g.drawImage(graphics.getFloor(), x, y, width, height, this);


                switch (map[i][j]){
                    case 'X':
                        g.drawImage(graphics.getWall(), x, y, width, height, this);
                        break;
                    case 'I':
                        g.drawImage(graphics.getClosedDoor(), x, y, width, height, this);
                        break;
                    case 'S':
                        g.drawImage(graphics.getOpenDoor(), x, y, width, height, this);
                        break;
                    case 'H':
                        g.drawImage(graphics.getHero(), x, y, width, height, this);
                        break;
                    case 'G':
                        g.drawImage(graphics.getGuard(), x, y, width, height, this);
                        break;
                    case 'g':
                        g.drawImage(graphics.getGuardAsleep(), x, y, width, height, this);
                        break;
                    case 'O':
                        g.drawImage(graphics.getOgre(), x, y, width, height, this);
                        break;
                    case '8':
                        g.drawImage(graphics.getOgreStunned(), x, y, width, height, this);
                        break;
                    case 'k':
                        g.drawImage(graphics.getKey(), x, y, width, height, this);
                        break;
                    case 'C':
                        g.drawImage(graphics.getRing(), x, y, width, height, this);
                        break;
                    case '*':
                        g.drawImage(graphics.getPotion(), x, y, width, height, this);
                        break;
                    case 'A':
                        g.drawImage(graphics.getClocked_hero(), x, y, width, height, this);
                        break;
                    case 'K':
                        if(DungeonKeep.game.getLever().isUp())
                        g.drawImage(graphics.getLeverUp(), x, y, width, height, this);
                        else  g.drawImage(graphics.getLeverDown(), x, y, width, height, this);

                        break;
                }
                x+=width;
            }
            x=30;
            y+=height;
        }
    }

     public void startGame(){

         try {
             Integer.parseInt(ogresnr.getText());
         }catch(NumberFormatException e) {
             JOptionPane.showMessageDialog(null, "Enter a valid number.");
             return;
         }

         if(Integer.parseInt(ogresnr.getText())>5) {
             JOptionPane.showMessageDialog(null, "Enter a number between 0-5.");
             return;
         }

         DungeonKeep.newGame = new Interaction(ogresnr.getText(), guard.getSelectedIndex());
         DungeonKeep.game = DungeonKeep.newGame.Dungeon();
         DungeonKeep.game.getMap().resetMap();


         this.setMaze(DungeonKeep.game.getMap());
         DungeonKeep.setStatusMsg("You can play now");
         enableMoveKeys(true);
     }

    public void setMaze(Map map){
        this.map = Interaction.printToString(map);
        repaint();
    }


     public void buttonsHandler(char button){
         if(DungeonKeep.getGame().getCurrent_state()!= GameState.States.DONE && DungeonKeep.getGame().getCurrent_state()!= GameState.States.GAME_OVER){
             DungeonKeep.getGame().game(button);
             DungeonKeep.getGame().checkEvents();

             this.setMaze(DungeonKeep.getGame().getMap());
         }else{
             enableMoveKeys(false);
         }


         if(DungeonKeep.getGame().getCurrent_state()== GameState.States.DONE){
             JOptionPane.showMessageDialog(null, "YOU WIN");
         }
         if(DungeonKeep.getGame().getCurrent_state()== GameState.States.GAME_OVER){
             JOptionPane.showMessageDialog(null, "GAME OVER");
         }
     }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    buttonsHandler('a');
                    break;
                case KeyEvent.VK_RIGHT:
                    buttonsHandler('d');
                    break;
                case KeyEvent.VK_UP:
                    buttonsHandler('w');
                    break;
                case KeyEvent.VK_DOWN:
                    buttonsHandler('s');
                    break;

            }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



     public void inicializeButtons() {


         moveup = new JButton("Up");
         moveup.setBounds(440,100, 80, 30);
         moveup.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 buttonsHandler('w');
                 requestFocusInWindow();


             }
         });
         moveup.setEnabled(false);
         add(moveup);

         moveleft = new JButton("Left");
         moveleft.setEnabled(false);
         moveleft.setBounds(393,135, 80, 30);

         moveleft.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 buttonsHandler('a');
                 requestFocusInWindow();
             }
         });


         this.add(moveleft);

         moveright = new JButton("Right");
         moveright.setEnabled(false);
         moveright.setBounds(483,135, 80, 30);

         moveright.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 buttonsHandler('d');
                 requestFocusInWindow();

             }
         });
         this.add(moveright);



         movedown = new JButton("Down");
         movedown.setEnabled(false);
         movedown.setBounds(440,170, 80, 30);
         movedown.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 buttonsHandler('s');
                 requestFocusInWindow();

             }
         });
         this.add(movedown);

         JButton btnExit = new JButton("Return");
         btnExit.setBounds(440,300, 80, 30);
         btnExit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                DungeonKeep.returnMainMenu();
                 requestFocusInWindow();

             }
         });
         this.add(btnExit);

         start = new JButton("Start");
         start.setBounds(440,50, 80, 30);
         start.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 startGame();
                 requestFocusInWindow();

             }
         });
         this.add(start);

         custom = new JButton("Custom Level");
         custom.setBounds(440-75/2,230, 150, 30);
         custom.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                 requestFocusInWindow();
                 DungeonKeep.startCustomLevel();
             }
         });
         this.add(custom);

         JLabel lblnrofogres = new JLabel("Number of Ogres");
         lblnrofogres.setBounds(25,5,150,50);
         add(lblnrofogres);

         ogresnr = new JTextField();
         ogresnr.setBounds(180 ,15,80,30);
         add(ogresnr);
         ogresnr.setColumns(10);

         JLabel lblguard = new JLabel("Guard Personality");
         lblguard.setBounds(25,30,180,50);
         add(lblguard);

         guard = new JComboBox<>();
         guard.setModel(new DefaultComboBoxModel<>(new String[] {"Rookie", "Drunken", "Suspicious"}));
         guard.setBounds(180,30,180,50);
         add(guard);
     }


     public static void enableMoveKeys(boolean value) {
         moveleft.setEnabled(value);
         moveright.setEnabled(value);
         moveup.setEnabled(value);
         movedown.setEnabled(value);
     }


}
