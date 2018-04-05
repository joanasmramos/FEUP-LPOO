package dkeep.gui;

import dkeep.cli.Interaction;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import dkeep.logic.*;


 class GraphicsDemo extends JPanel implements KeyListener {

    private GraphicsBank graphics;
    private char[][] map;


    GraphicsDemo() throws IOException{
        map = null;
        graphics =  new GraphicsBank();
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        int width = 30;
        int height = 30;
        int x = 20;
        int y = 20;
        if(map==null) return;


        for (int i = 0; i<10;i++){
            for (int j = 0; j<10; j++){
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
                        g.drawImage(graphics.getLeverUp(), x, y, width, height, this);
                        break;
                    case 'L':
                        g.drawImage(graphics.getLeverDown(), x, y, width, height, this);
                        break;
                }
                x+=width;
            }
            x=20;
            y+=height;
        }
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
             DungeonKeep.enableMoveKeys(false);
         }


         if(DungeonKeep.getGame().getCurrent_state()== GameState.States.DONE){
             DungeonKeep.getStatusMsg().setText("YOU WIN");}
         if(DungeonKeep.getGame().getCurrent_state()== GameState.States.GAME_OVER){
             DungeonKeep.getStatusMsg().setText("GAME OVER");
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
}
