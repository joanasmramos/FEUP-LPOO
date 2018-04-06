package dkeep.gui;

import java.awt.*;

import dkeep.cli.Interaction;

import javax.swing.JFrame;
import javax.swing.JLabel;
import dkeep.logic.GameState;


import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.*;


public class DungeonKeep {

    private static JFrame frame;
    private static JFrame custom_frame;
     static GameState custom;
     static Interaction custom_i;

    private static JLabel statusMsg;
    static Interaction newGame = null;
    static GameState game = null;

    static GraphicsDemo graphicsPanel;
    static Menu menu;
    static CustomizeMap designMenu;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DungeonKeep window = new DungeonKeep();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static GameState getGame() {
        return game;
    }

    public static JLabel getStatusMsg() {
        return statusMsg;
    }

    public static void setStatusMsg(String statusMsg) {
        DungeonKeep.statusMsg.setText(statusMsg);
    }

    /**
     * Create the application.
     */
    public DungeonKeep() throws IOException {
        initialize();
        graphicsPanel.setFocusable(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() throws IOException {

        initFrames();

        initPannels();

        statusMsg = new JLabel("You can start a new game.");
        statusMsg.setLayout(new BorderLayout());
        statusMsg.setBounds(150, 450, 200, 30);

        initGroupLayout();

    }

    public void initFrames(){
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        custom_frame = new JFrame();
        custom_frame.setVisible(false);
        custom_frame.setBounds(120, 120, 560, 460);
        custom_frame.getContentPane().setLayout(null);
    }

    public void initPannels() throws IOException{
        menu = new Menu();
        menu.setLayout(new BorderLayout(0, 0));
        menu.setBounds(0, 0, 600, 500);
        menu.setVisible(true);
        frame.getContentPane().add(menu);

        graphicsPanel = new GraphicsDemo();
        graphicsPanel.setLayout(new BorderLayout(0, 0));
        graphicsPanel.setBounds(0, 0, 560, 460);
        graphicsPanel.setVisible(false);
        frame.getContentPane().add(graphicsPanel);


        designMenu = new CustomizeMap();
        designMenu.setLayout(new BorderLayout());
        designMenu.setBounds(0, 0, 540, 440);
        designMenu.setVisible(false);
        custom_frame.add(designMenu);
    }

    public void initGroupLayout(){

        JPanel options = new JPanel();
        JPanel display = new JPanel();
        display.setLayout(new BorderLayout(0, 0));

        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(3, 0, 0, 0));


        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(options, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 40, Short.MAX_VALUE))
                                        .addComponent(display, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(153)
                                                .addComponent(statusMsg)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(controls, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(options, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                .addGap(7)
                                .addComponent(display, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(statusMsg, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                .addGap(18))
                        .addComponent(controls, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
        );


        frame.getContentPane().setLayout(groupLayout);
    }

    public static void newGamePressed() {
        frame.setVisible(true);
        menu.setVisible(false);
        graphicsPanel.setVisible(true);

        DungeonKeep.newGame = new Interaction("2", 0);

        setStatusMsg("You can play now");
        graphicsPanel.enableMoveKeys(true);
    }


    public static void designMenuPressed() {
        menu.setVisible(true);
        graphicsPanel.setVisible(false);
        custom_frame.setVisible(true);
        designMenu.setVisible(true);
        designMenu.restore();

    }

    public  static void returnMainMenu(){
        menu.setVisible(true);
        graphicsPanel.setVisible(false);
        custom_frame.setVisible(false);
        designMenu.setVisible(false);
    }

    public static void startCustomLevel(){
        game = custom;
        if(game!=null)  graphicsPanel.setMaze(custom.getMap());
        else JOptionPane.showMessageDialog(null, "No map saved.");
    }

    public static void saveGame(String filename, GameState game) {

        try{
            FileOutputStream fileOut = new FileOutputStream("../savedGames/" + filename + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        }catch (IOException i){
            i.printStackTrace();
        }
    }

    public static GameState loadGame(String filename) throws IOException, ClassNotFoundException {
        GameState game = null;

        FileInputStream fileIn = new FileInputStream("../savedGames/" + filename + ".txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        game = (GameState) in.readObject();
        in.close();
        fileIn.close();

        return game;

    }
}
