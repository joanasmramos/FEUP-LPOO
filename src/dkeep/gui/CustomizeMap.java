package dkeep.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import dkeep.logic.*;

public class CustomizeMap extends JPanel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static GraphicsBank graphics;
	private static JPanel mapPanel;
	private static JPanel btnsPanel;
	private static JButton btnWall;
	private static JButton btnHero;
	private static JButton btnOgre;
	private static JButton btnDoor;
	private static JButton btnKey;
	private JButton btnReturn;
	private static JPanel dimensionsPanel;
	private static JLabel lblWidth;
	private static JLabel lblHeight;
	private static JTextField heightTxtField;
	private static JTextField widthTxtField;
	private JLabel label;
	private JButton applyMapDim;

	
	private char[][] map;
	private int lines, columns;
	private static char elementToAdd;
	private Map mapObject;
	private GameElement objectToAdd;
	private GameState game;
	
	CustomizeMap() throws IOException {
        map = null;
        graphics =  new GraphicsBank();
        
		setLayout(new GridLayout(0, 2, 0, 0));
		
		addMouseListener(this);
		
		initMapPanel();
		
		btnsPanel = new JPanel();
		btnsPanel.setLayout(new BorderLayout());
		btnsPanel.setBounds(380,50,200,200);
		add(btnsPanel);
		
		initializeButtons();
		
		initDimensionsPanel();

        graphics = new GraphicsBank();
        graphics.loadGraphics();

    }
	
    private void initMapPanel() {
    	mapPanel = new JPanel();
		add(mapPanel);
		
		map = new char[10][10];
		
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = ' ';
			}
		}
		
		mapObject = new Map(map, false, false);
		game = new GameState(mapObject);
		repaint();
		
	}

	private void initDimensionsPanel() {
		dimensionsPanel = new JPanel();
		btnsPanel.add(dimensionsPanel);
		dimensionsPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblHeight = new JLabel("Height");
		dimensionsPanel.add(lblHeight);
		
		heightTxtField = new JTextField();
		dimensionsPanel.add(heightTxtField);
		
		lblWidth = new JLabel("Width");
		dimensionsPanel.add(lblWidth);
		
		widthTxtField = new JTextField();
		dimensionsPanel.add(widthTxtField);


		label = new JLabel("");
		btnsPanel.add(label);
	}

	public void initializeButtons() {
    	initBtnWall();
		
    	initBtnHero();
    	
    	initBtnOgre();
		
    	initBtnDoor();
    	
    	initBtnKey();
    	
    	initBtnReturn();
    	
    	initBtnApply();
    }

	private void initBtnApply() {
		applyMapDim = new JButton("Apply dimensions");
		applyMapDim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initMap();
			}
		});
		btnsPanel.add(applyMapDim);
	}

	private void initBtnReturn() {
		btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnsPanel.add(btnReturn);
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
                repaint();
            }
		});
		btnsPanel.add(btnDoor);
	}

	private void initBtnOgre() {
		btnOgre = new JButton("Ogre");
		btnOgre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOgre();
                repaint();
            }
		});
		btnsPanel.add(btnOgre);
	}

	private void initBtnHero() {
		btnHero = new JButton("Hero");
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createHero();
                repaint();

            }
		});
		btnsPanel.add(btnHero);
	}

	private void initBtnWall() {
		btnWall = new JButton("Wall");
		btnWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createWall();
                repaint();

            }

		});
		btnsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		btnsPanel.add(btnWall);
		
	}
	
    private int readHeight() {
    	int height;
    	
		try { 
			height = Integer.parseInt(heightTxtField.getText()); 
		}
		catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return 1;
        }
		
		return height;
	}
    
	private int readWidth() {
		int width;
		
		try {
		width = Integer.parseInt(widthTxtField.getText());
		}
		catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return 1;
        }
		
		return width;
	}
	
	private void initMap() {
		int height = 10, width = 10;
		
		height = readHeight();
		width = readWidth();
	    
	    map = new char[height][width];
	    
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = ' ';
			}
		}
		
		lines = height;
		columns = width;
		
		mapObject = new Map(map, false, false);
		game.setMap(mapObject);
		
		this.repaint();
	}

	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        int width = 30;
        int height = 30;
        int x = 30;
        int y = 80;


        g.drawImage(graphics.getFloor(), x, y, width, height, this);

        if(map==null) return;


        for (int i = 0; i<lines;i++) {
            for (int j = 0; j<columns; j++){
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
                    case 'O':
                        g.drawImage(graphics.getOgre(), x, y, width, height, this);
                        break;
                    case 'k':
                        g.drawImage(graphics.getKey(), x, y, width, height, this);
                        break;
                    case '*':
                        g.drawImage(graphics.getPotion(), x, y, width, height, this);
                        break;
                    case 'A':
                        g.drawImage(graphics.getClocked_hero(), x, y, width, height, this);
                        break;
                }
                x+=width;
            }
            x=30;
            y+=height;
        }
    }
	
	public void createKey() {
		elementToAdd = 'k';
		
		//dkeep.logic.Object key = new dkeep.logic.Object();
		//key.setChar(elementToAdd);
		//mapObject.setKey(true);
		//game.setKey(key);
		//mapObject.addObj(key);
	}

	public void createDoor() {
		elementToAdd = 'I';	
		
		//dkeep.logic.Object door = new dkeep.logic.Object();
		//door.setChar('I');
		//game.setExitDoor(door);
		//mapObject.addObj(door);
	}

	public void createOgre() {
		elementToAdd = 'O';
		
		//dkeep.logic.Ogre ogre = new Ogre(elementToAdd);
		//game.addOgre(ogre);
		//mapObject.addChar(ogre);
	}

	public void createHero() {
		elementToAdd = 'H';
		
		//dkeep.logic.Hero hero = new Hero(elementToAdd);
		//game.setHero(hero);
		//mapObject.addChar(hero);
	}

	public void createWall() {
		elementToAdd = 'X';
		
		//dkeep.logic.Object wall = new dkeep.logic.Object();
		//wall.setChar('X');
		//game.addWall(wall);
		//mapObject.addObj(wall);
	}
    
	public void mouseClicked(MouseEvent e) {
		int X = e.getX(), Y = e.getY(), x, y;
		
		if( X < 30 || X > (lines * 30 + 30) || Y < 80 || Y > (columns * 30 + 80) )
			return;
		
		x = (X-30) / 30;
		y = (Y-80) / 30;

		//objectToAdd.setCoordinates(y, x);
		
		map[y][x] = elementToAdd;
		
		repaint();
	}
	
	public char[][] getMap() {
		return map;
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
