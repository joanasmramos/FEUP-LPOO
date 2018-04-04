package dkeep.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsBank {

    private BufferedImage wall;
    private BufferedImage openDoor;
    private BufferedImage hero;
    private BufferedImage key;
    private BufferedImage closedDoor;
    private BufferedImage ogre;
    private BufferedImage guard;
    private BufferedImage ring;
    private BufferedImage potion;
    private BufferedImage clocked_hero;
    private BufferedImage leverUp;
    private BufferedImage leverDown;

    GraphicsBank() throws IOException{
        loadGraphics();
    }

    public void loadGraphics() throws IOException {
    	closedDoor =  ImageIO.read(new File("../graphics/closeddoor.png"));
        wall = ImageIO.read(new File("../graphics/wall.jpg"));
        openDoor =  ImageIO.read(new File("../graphics/opendoor.png"));
        hero =  ImageIO.read(new File("../graphics/hero.jpg"));
        key =  ImageIO.read(new File("../graphics/key.png"));
        guard =  ImageIO.read(new File("../graphics/guard.jpg"));
        ogre =  ImageIO.read(new File("../graphics/ogre.jpg"));
        ring =  ImageIO.read(new File("../graphics/ring.jpg"));
        potion =  ImageIO.read(new File("../graphics/potion.png"));
        clocked_hero =  ImageIO.read(new File("../graphics/heroclocked.jpg"));
        leverUp =  ImageIO.read(new File("../graphics/leverup.jpg"));
        leverDown =  ImageIO.read(new File("../graphics/leverdown.jpg"));
    }

    public BufferedImage getWall() {
        return wall;
    }

    public BufferedImage getClosedDoor() {
        return closedDoor;
    }

    public BufferedImage getHero() {
        return hero;
    }

    public BufferedImage getKey() {
        return key;
    }

    public BufferedImage getOpenDoor() {
        return openDoor;
    }

    public BufferedImage getGuard() {
        return guard;
    }

    public BufferedImage getOgre() {
        return ogre;
    }

    public BufferedImage getRing() {
        return ring;
    }

    public BufferedImage getPotion() {
        return potion;
    }

    public BufferedImage getClocked_hero() {
        return clocked_hero;
    }

    public BufferedImage getLeverUp() {
        return leverUp;
    }

    public BufferedImage getLeverDown() {
        return leverDown;
    }
}
