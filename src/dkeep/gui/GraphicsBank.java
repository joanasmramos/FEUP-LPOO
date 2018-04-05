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
    private BufferedImage floor;
    private BufferedImage guardAsleep;
    private BufferedImage ogreStunned;



    GraphicsBank() throws IOException{
        loadGraphics();
    }

    public void loadGraphics() throws IOException {
    	closedDoor =  ImageIO.read(new File("../graphics/closeddoor.png"));
        wall = ImageIO.read(new File("../graphics/wall.png"));
        openDoor =  ImageIO.read(new File("../graphics/opendoor.png"));
        hero =  ImageIO.read(new File("../graphics/hero.png"));
        key =  ImageIO.read(new File("../graphics/key.png"));
        guard =  ImageIO.read(new File("../graphics/guard.png"));
        ogre =  ImageIO.read(new File("../graphics/ogre.png"));
        ring =  ImageIO.read(new File("../graphics/ring.png"));
        potion =  ImageIO.read(new File("../graphics/potion.png"));
        clocked_hero =  ImageIO.read(new File("../graphics/heroclocked.png"));
        leverUp =  ImageIO.read(new File("../graphics/leverup.png"));
        leverDown =  ImageIO.read(new File("../graphics/leverdown.png"));
        floor =  ImageIO.read(new File("../graphics/floor.png"));
        guardAsleep =  ImageIO.read(new File("../graphics/guardasleep.png"));
        ogreStunned =  ImageIO.read(new File("../graphics/ogrestunned.png"));

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

    public BufferedImage getFloor() {
        return floor;
    }

    public BufferedImage getOgreStunned() {
        return ogreStunned;
    }

    public BufferedImage getGuardAsleep() {
        return guardAsleep;
    }
}
