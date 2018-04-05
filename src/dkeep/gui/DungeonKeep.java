package dkeep.gui;

import java.awt.EventQueue;
import dkeep.cli.Interaction;

import javax.swing.JFrame;
import javax.swing.JLabel;
import dkeep.logic.GameState;


import java.awt.GridLayout;
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
import java.awt.BorderLayout;
import java.io.IOException;


public class DungeonKeep{

	private static JFrame frame;
	private static JLabel statusMsg;
    static Interaction newGame = null;
	static GameState game = null;

	static GraphicsDemo graphicsPanel;
	static Menu menu;

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

    public static JLabel getStatusMsg(){
	    return statusMsg;
    }

    public static void setStatusMsg(String statusMsg) {
        DungeonKeep.statusMsg.setText(statusMsg);
    }

    /**
	 * Create the application.
	 */
	public DungeonKeep() throws IOException{
		initialize() ;
        graphicsPanel.setFocusable(true);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);




        JPanel display = new JPanel();
        display.setLayout(new BorderLayout(0, 0));

        menu = new Menu();
        menu.setLayout(new BorderLayout(0,0));
        menu.setBounds(0,0,600,500);
        menu.setVisible(true);
        frame.getContentPane().add(menu);

        graphicsPanel = new GraphicsDemo();
        graphicsPanel.setLayout(new BorderLayout(0,0));
        graphicsPanel.setBounds(0,0,600,500);
        graphicsPanel.setVisible(false);
        frame.getContentPane().add(graphicsPanel);


        JPanel options = new JPanel();

		
		JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(3, 0, 0, 0));

        statusMsg = new JLabel("You can start a new game.");
        statusMsg.setLayout(new BorderLayout());
        statusMsg.setBounds(150,450,200,30);
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

        menu.setVisible(false);
        graphicsPanel.setVisible(true);
    }







}
