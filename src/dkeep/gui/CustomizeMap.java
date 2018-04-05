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
	private static JPanel dimensionsPanel;
	private static JLabel lblWidth;
	private static JLabel lblHeight;
	private static JTextField heightTxtField;
	private static JTextField widthTxtField;
	
	private static char[][] map;
	private int lines, columns;
	private static char elementToAdd;
	private JButton btnReturn;
	
	CustomizeMap() throws IOException {
        map = null;
        graphics =  new GraphicsBank();
		
		btnsPanel = new JPanel();
		btnsPanel.setLayout(new BorderLayout());
		btnsPanel.setBounds(380,50,200,200);
		add(btnsPanel);
		
		initializeButtons();
		
		initDimensionsPanel();

        initMap();
        graphics = new GraphicsBank();
        graphics.loadGraphics();

    }
	
    private void initDimensionsPanel() {
		dimensionsPanel = new JPanel();
		btnsPanel.add(dimensionsPanel);
		dimensionsPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblHeight = new JLabel("Height");
		dimensionsPanel.add(lblHeight);
		
		
		heightTxtField = new JTextField();
		dimensionsPanel.add(heightTxtField);
		heightTxtField.setColumns(10);
		
		
		lblWidth = new JLabel("Width");
		dimensionsPanel.add(lblWidth);
		
		
		widthTxtField = new JTextField();
		dimensionsPanel.add(widthTxtField);
		widthTxtField.setColumns(10);
	}

	public void initializeButtons() {
    	initBtnWall();
		
    	initBtnHero();
    	
    	initBtnOgre();
		
    	initBtnDoor();
    	
    	initBtnKey();
    	
    	initBtnReturn();
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
	
	private void initMap() {
		int height, width;

		height = 10;
		width = 10;
		
	    /*try {
            height = Integer.parseInt(heightTxtField.getText());
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return;
        }
	    
	    try {
            width = Integer.parseInt(heightTxtField.getText());
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number.");
            return;
        }
	    
	    if(height < 1 || width < 1) {
	    	JOptionPane.showMessageDialog(null, "Enter a valid width/height. ");
	    	return;
	    }*/
	    
	    map = new char[height][width];
	    
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = ' ';
			}
		}
		
		lines = height;
		columns = width;
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
	
	protected void createKey() {
		elementToAdd = 'k';
	}

	protected void createDoor() {
		elementToAdd = 'I';	
	}

	protected void createOgre() {
		elementToAdd = 'O';
	}

	protected void createHero() {
		elementToAdd = 'H';
	}

	private void createWall() {
		elementToAdd = 'X';
	}
    
	public void mouseClicked(MouseEvent e) {
		int X = e.getX(), Y = e.getY(), x, y;
		
		if( X < 30 || X > (lines * 30 + 30) || Y < 80 || Y > (columns * 30 + 80) )
			return;
		
		x = (X-30) % 30;
		y = (Y-80) % 30;
		
		map[x][y] = elementToAdd;
		
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
