package dkeep.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class CustomizeMap extends JPanel implements MouseListener {
	private static GraphicsBank graphics;
	private static JPanel mapPanel;
	private static JPanel btnsPanel;
	private static JButton btnWall;
	private static JButton btnHero;
	private static JButton btnOgre;
	private static JButton btnDoor;
	private static JButton btnKey;
	
	
	CustomizeMap() throws IOException {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		mapPanel = new JPanel();
		add(mapPanel);
		
		btnsPanel = new JPanel();
		add(btnsPanel);
		btnsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		initializeButtons();
		
        graphics = new GraphicsBank();
        graphics.loadGraphics();

    }
	
    public void initializeButtons() {
    	initBtnWall();
		
    	initBtnHero();
    	
    	initBtnOgre();
		
    	initBtnDoor();
    	
    	initBtnKey();
    }

	private void initBtnKey() {
		btnKey = new JButton("Key");
		btnKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createKey();
			}
		});
		btnsPanel.add(btnKey);
	}

	private void initBtnDoor() {
		btnDoor = new JButton("Door");
		btnDoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createDoor();
			}
		});
		btnsPanel.add(btnDoor);
	}

	private void initBtnOgre() {
		btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOgre();
			}
		});
		btnsPanel.add(btnOgre);
	}

	private void initBtnHero() {
		btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHero();
			}
		});
		btnsPanel.add(btnHero);
	}

	private void initBtnWall() {
		btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createWall();
			}

		});
		btnsPanel.add(btnWall);
		
	}

	protected void createKey() {
		// TODO Auto-generated method stub
		
	}

	protected void createDoor() {
		// TODO Auto-generated method stub
		
	}

	protected void createOgre() {
		// TODO Auto-generated method stub
		
	}

	protected void createHero() {
		// TODO Auto-generated method stub
		
	}

	private void createWall() {
		// TODO Auto-generated method stub
		
	}
    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
