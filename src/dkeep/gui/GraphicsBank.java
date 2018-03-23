package dkeep.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsBank {

    private BufferedImage wall;
    private BufferedImage door;
    private BufferedImage hero;
    private BufferedImage key;

    GraphicsBank() throws IOException{
        loadGraphics();
    }

    public void loadGraphics() throws IOException {
        wall = ImageIO.read(new File("graphics/wall.jpg"));
        door =  ImageIO.read(new File("graphics/door.jpg"));
        hero =  ImageIO.read(new File("graphics/hero.png"));
        key =  ImageIO.read(new File("graphics/key.png"));

    }

    public BufferedImage getWall() {
        return wall;
    }

    public BufferedImage getDoor() {
        return door;
    }

    public BufferedImage getHero() {
        return hero;
    }

    public BufferedImage getKey() {
        return key;
    }
}
