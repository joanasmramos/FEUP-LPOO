package dkeep.gui;

import dkeep.cli.Interaction;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.io.IOException;

import dkeep.logic.*;



public class GraphicsDemo extends JPanel {

    private GraphicsBank graphics;
    private char[][] map;


    GraphicsDemo() throws IOException{
        map = null;
        graphics =  new GraphicsBank();
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
                    case 'O':
                        g.drawImage(graphics.getOgre(), x, y, width, height, this);
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



}
