package dkeep.gui;

import dkeep.cli.Interaction;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;
import java.io.IOException;

import dkeep.logic.*;



public class GraphicsDemo extends JPanel {

    GraphicsBank graphics;
    char[][] map;
    int width = 20;
    int height = 20;

    GraphicsDemo() throws IOException{
        map = null;
        graphics =  new GraphicsBank();
    }

    @Override
    public void paintComponent(Graphics g) {
        int x = 30;
        int y = 30;
        if(map==null) return;

        for (int i = 0; i<10;i++){
            for (int j = 0; j<10; j++){
                switch (map[i][j]){
                    case 'X':
                        g.drawImage(graphics.getWall(), x, y, width, height, this);
                        break;
                    case ' ':
                        g.setColor(Color.WHITE);
                        g.fillRect(x,y,width,height);
                        break;
                    case 'I':
                        g.drawImage(graphics.getDoor(), x, y, width, height, this);
                        break;
                    case 'H':
                        g.drawImage(graphics.getHero(), x, y, width, height, this);
                        break;
                    case 'G':
                        g.setColor(Color.YELLOW);
                        g.fillRect(x,y,width,height);
                        break;
                    case 'k':
                        g.drawImage(graphics.getKey(), x, y, width, height, this);
                        break;
                }
                x+=width;
            }
            x=30;
            y+=height;
        }
    }

    public void setMaze(Map map){

        this.map = Interaction.printToString(map);
        repaint();
    }



}
