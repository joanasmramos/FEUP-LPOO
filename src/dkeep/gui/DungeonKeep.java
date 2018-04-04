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
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import dkeep.gui.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class DungeonKeep{

	private JFrame frame;
	private static JTextField ogresnr;
	private static JComboBox<String> guard;
	private static JButton moveleft, moveright, moveup, movedown;
	private static JLabel statusMsg;
    static Interaction newGame = null;
	static GameState game = null;

	GraphicsDemo graphicsPanel;


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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel options = new JPanel();
		
		JPanel display = new JPanel();
		
		JPanel controls = new JPanel();
		
		statusMsg = new JLabel("You can start a new game.");
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
        display.setLayout(new BorderLayout(0, 0));

        graphicsPanel = new GraphicsDemo();
        graphicsPanel.setBounds(20,20,300,300);

		display.add(graphicsPanel);
		controls.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel pnlnewgame = new JPanel();
		controls.add(pnlnewgame);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGamePressed();
				graphicsPanel.requestFocusInWindow();
			}
		});
		pnlnewgame.add(btnNewGame);
		
		JPanel controlspnl = new JPanel();
		controls.add(controlspnl);
		controlspnl.setLayout(new GridLayout(3, 0, 0, 0));
		
		moveup = new JButton("Up");
		moveup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphicsPanel.buttonsHandler('w');
                graphicsPanel.requestFocusInWindow();


            }
		});
		moveup.setEnabled(false);
		controlspnl.add(moveup);
		
		JPanel leftrightpnl = new JPanel();   
		controlspnl.add(leftrightpnl);
		leftrightpnl.setLayout(new GridLayout(0, 2, 0, 0));
		
		moveleft = new JButton("Left");
		moveleft.setEnabled(false);
		moveleft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                graphicsPanel.buttonsHandler('a');
                graphicsPanel.requestFocusInWindow();
            }
		});
		leftrightpnl.add(moveleft);
		
		moveright = new JButton("Right");
		moveright.setEnabled(false);
		moveright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                graphicsPanel.buttonsHandler('d');
                graphicsPanel.requestFocusInWindow();

            }
		});
		leftrightpnl.add(moveright);
		
		movedown = new JButton("Down");
		movedown.setEnabled(false);
		movedown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                graphicsPanel.buttonsHandler('s');
                graphicsPanel.requestFocusInWindow();

            }
		});
		controlspnl.add(movedown);
		
		JPanel exitpnl = new JPanel();
		controls.add(exitpnl);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
                graphicsPanel.requestFocusInWindow();

            }
		});
		exitpnl.add(btnExit);
		options.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblnrofogres = new JLabel("Number of Ogres");
		options.add(lblnrofogres);
		
		ogresnr = new JTextField();
		options.add(ogresnr);
		ogresnr.setColumns(10);
		
		JLabel lblguard = new JLabel("Guard Personality");
		options.add(lblguard);
		
		guard = new JComboBox<>();
		guard.setModel(new DefaultComboBoxModel<>(new String[] {"Rookie", "Drunken", "Suspicious"}));
		options.add(guard);
		frame.getContentPane().setLayout(groupLayout);

	}
	
	public void newGamePressed() {

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

        newGame = new Interaction(ogresnr.getText(), guard.getSelectedIndex());
        game = newGame.Dungeon();
        game.getMap().resetMap();


        graphicsPanel.setMaze(game.getMap());
        statusMsg.setText("You can play now");
		enableMoveKeys(true);
	}

	public static void enableMoveKeys(boolean value) {
		moveleft.setEnabled(value);
		moveright.setEnabled(value);
		moveup.setEnabled(value);
		movedown.setEnabled(value);
	}



}
