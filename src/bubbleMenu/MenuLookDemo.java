/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package bubbleMenu;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.peer.MouseInfoPeer;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

/* MenuLookDemo.java requires images/middle.gif. */

/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class MenuLookDemo {

	JTextArea output;
	JScrollPane scrollPane;
	static JFrame frame;
	public static Point mousePosition = new Point();
	public static Point mousePositionOnScreen;

	static int offset;
	static JMenuBar jMenuBar = null;
	static JMenuPrio jMenuActuel = null;

	static Shape circle = null;
	static Boolean drag = false;
	
	static Color couleur = Color.BLACK;

	//Fonction pour créer la MenuBar afficher
	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenuPrio menu, submenu;
		JMenuItemPrio menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenuPrio("Couleurs 1", true);
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItemPrio("Blue", KeyEvent.VK_T, true);
		// menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.BLUE;
				// TODO Auto-generated method stub
				jMenuActuel = null;

			}
		});
		menu.add(menuItem);

		ImageIcon icon = createImageIcon("images/middle.gif");
		menuItem = new JMenuItemPrio("Rose", icon);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.PINK;
				jMenuActuel = null;
			}
		});
		
		menu.add(menuItem);

		// a submenu
		submenu = new JMenuPrio("Plus de couleurs!", true);
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItemPrio("Vert", true);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.GREEN;
				jMenuActuel = null;
			}
		});
		submenu.add(menuItem);

		menuItem = new JMenuItemPrio("Cyan");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.CYAN;
				jMenuActuel = null;
			}
		});
		submenu.add(menuItem);
		menu.add(submenu);

		// Build second menu in the menu bar.
		menu = new JMenuPrio("Couleurs 2");
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);

		menuItem = new JMenuItemPrio("Orange", KeyEvent.VK_T);
		// menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.ORANGE;
				jMenuActuel = null;
			}
		});
		menu.add(menuItem);
		
		// Build thir menu
		// Build second menu in the menu bar.
		menu = new JMenuPrio("Couleurs 3", true);
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);

		menuItem = new JMenuItemPrio("Rouge", KeyEvent.VK_T, true);
		// menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.RED;
				jMenuActuel = null;
			}
		});
		menu.add(menuItem);
		
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuItem);
		
		submenu = new JMenuPrio("Plus de couleurs", true);
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItemPrio("Jaune", true);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.YELLOW;
				jMenuActuel = null;
			}
		});
		submenu.add(menuItem);

		menuItem = new JMenuItemPrio("Magenta");
		submenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				couleur = Color.MAGENTA;
				jMenuActuel = null;
			}
		});
		menu.add(submenu);

		return menuBar;
	}

	//Fonction pour créer le container contenant le JPanel
	public Container createContentPane() {
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		// Create a scrolled text area.
		output = new JTextArea(5, 30);
		output.setEditable(false);
		scrollPane = new JScrollPane(output);

		// Add the text area to the content pane.
		contentPane.add(scrollPane, BorderLayout.CENTER);

		return contentPane;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MenuLookDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("MenuLookDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		MenuLookDemo demo = new MenuLookDemo();

		jMenuBar = demo.createMenuBar();
		jMenuBar.setRequestFocusEnabled(false);
		frame.setJMenuBar(jMenuBar);
	

		Container container = demo.createContentPane();
		JPanel jPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				//Si on fait un paint alors qu'on drag, on affiche le cercle du BubbleMenu
				if (drag) {
					
					
					Object object = menuPlusProche(mousePosition.x, mousePosition.y);
					if (object != null) {
						if (object instanceof JMenuPrio) {
							
							//On recupere la position de la souris, le menu le plus proche, puis dessine le cercle
							JMenuPrio menuPrio = (JMenuPrio) object;
							
							Point p = mousePosition;
						
							Rectangle r = new Rectangle();
							r.x = menuPrio.getLocationOnScreen().x - 8;
							r.y = menuPrio.getLocationOnScreen().y - offset;
							r.width = menuPrio.getWidth();
							r.height = menuPrio.getHeight();
							g.drawRect(r.x, r.y, r.width, r.height);
							drawCircle(pointPlusProche(r,p), p, g);
						} else {
							
							JMenuItemPrio itemPrio = (JMenuItemPrio) object;
							Point p = mousePosition;
							Rectangle r = new Rectangle();
							r.x = itemPrio.getLocationOnScreen().x - 8 ;
							r.y = itemPrio.getLocationOnScreen().y - offset;
							r.width = itemPrio.getWidth();
							r.height = itemPrio.getHeight();
							g.drawRect(r.x, r.y, r.width, r.height);
							drawCircle(pointPlusProche(r,p), p, g);
						}
					}
						
				}
			}
			

		};
		container.add(jPanel);
		frame.setContentPane(container);

		jPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				mousePositionOnScreen = e.getLocationOnScreen();
				mousePosition.x = e.getX(); 
				mousePosition.y = e.getY();
				
				if(drag)
				{
					jPanel.repaint();
				}
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
				mousePositionOnScreen = e.getLocationOnScreen();
				mousePosition.x = e.getX(); 
				mousePosition.y = e.getY();
				if(drag)
				{
					jPanel.repaint();
				}
			}

		});

		jPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
				if (SwingUtilities.isRightMouseButton(e)) {
					Object object = menuPlusProche(e.getX(), e.getY());
					

					if (object != null) {
						if (object instanceof JMenuPrio) {
							JMenuPrio subMenu = (JMenuPrio) object;
							subMenu.doClick();
							jMenuActuel = subMenu;
						} else {
							JMenuItemPrio item = (JMenuItemPrio) object;
							item.doClick();
							jMenuActuel = null;
						}
					}
					drag = false;
					jPanel.repaint();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					
					drag = true;
					jPanel.repaint();
					
					if (jMenuActuel != null) {
						jMenuActuel.doClick();
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					jMenuActuel = null;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

		});

		// Display the window.
		frame.setSize(500, 500);
		frame.setVisible(true);
		offset = jPanel.getLocationOnScreen().y;
		
	}
	
	//Fonction pour récupérer le point le plus proche du rectangle pour un point P
	//Permet de dessiner un cercle plus fiable
	public static Point pointPlusProche(Rectangle r, Point p)
	{
		Point pointeur = new Point(p);
//		pointeur.translate(0, -offset);
		ArrayList<Point> l = new ArrayList();  //HG, BG, BD, HD
		Double distMin = Double.MAX_VALUE;
		Point res = new Point();
		//HG
		l.add(new Point(r.getLocation())); 
		//BG
		l.add(new Point((int)r.getX(),(int)(r.getY() + r.getHeight())));
		//(BG - BD) /2
		l.add(new Point((int)(r.getX() + r.getWidth()/2),(int)(r.getY() + r.getHeight())));
		//BD
		l.add(new Point((int)(r.getX() + r.getWidth()),(int)(r.getY() + r.getHeight())));
		//HD
		l.add(new Point((int)(r.getX() + r.getWidth()),(int)r.getY()));
		//(HG - HD)/2
		l.add(new Point((int)(r.getX() + r.getWidth()/2),(int)r.getY()));
		for(Point pf: l)
		{
			if(pointeur.distance(pf) < distMin)
			{
				res = pf;
				distMin = pointeur.distance(pf);
			}
				
		}
		return res;
	}

	//Fonction qui dessine le cercle entre deux points
	public static void drawCircle(Point pMenu, Point pPointeur, Graphics g) {
		
		double rayon = pMenu.distance(pPointeur);
		g.setColor(couleur);
		
		g.drawOval((int) (pPointeur.getX() - rayon), (int) (pPointeur.getY() - rayon), (int) (rayon * 2),
				(int) (rayon * 2));

	}

	// Methode pour trouver le menu ou l'item prioritaire le plus proche
	public static Object menuPlusProche(int x, int y) {
		double distanceMin = Double.MAX_VALUE;

		// Cas ou on a pas encore choisi de menu -> on check la JMenuBar
		if (jMenuActuel == null) {
			
			JMenuPrio jMenuPrio;
			JMenuPrio jMenuPrioMin = null;

			Point2D point1;
			double distance;
			
			for (int i = 0; i < jMenuBar.getMenuCount(); i++) {
				jMenuPrio = (JMenuPrio) jMenuBar.getMenu(i);
				// Si c'est un menu prioritaire, alors on regarde la distance
				if (jMenuPrio.prioritaire) {
					point1 = new Point(jMenuPrio.getX(), jMenuPrio.getY());
					Rectangle r = jMenuPrio.getBounds();
					
					Point p = new Point(x,y);
					Point pmin = pointPlusProche(r,  p);
					
					distance = pmin.distance(x,y);
					

					if (distance < distanceMin) {
						distanceMin = distance;
						jMenuPrioMin = jMenuPrio;
					}
				}
			}
			return jMenuPrioMin;
		}

		// Cas ou on a deja choisi un menu -> on regarde les items dans ce menu
		else {
			JMenuItemPrio jMenuItemPrio;
			Object objectMin = null;
			Object object;
			JMenuPrio jMenuPrio;
			boolean prio = true;

			Point2D point1;
			double distance;

			for (int i = 0; i < jMenuActuel.getItemCount(); i++) {
				object = jMenuActuel.getItem(i);

				if (object instanceof JMenuPrio) {
					jMenuPrio = (JMenuPrio) object;
					point1 = new Point(jMenuPrio.getX(), jMenuPrio.getY());
					Rectangle r = jMenuPrio.getBounds();
					
					Point p = new Point(x,y);
					Point pmin = pointPlusProche(r,  p);
					
					distance = pmin.distance(x,y);
					prio = jMenuPrio.prioritaire;

				} else {
					jMenuItemPrio = (JMenuItemPrio) object;
					point1 = new Point(jMenuItemPrio.getX(), jMenuItemPrio.getY());
					Rectangle r = jMenuItemPrio.getBounds();
					
					Point p = new Point(x,y);
					Point pmin = pointPlusProche(r,  p);
					
					distance = pmin.distance(x,y);
					prio = jMenuItemPrio.prioritaire;
				}

				if (distance < distanceMin && prio) {
					distanceMin = distance;
					objectMin = object;
				}

			}
			return objectMin;
		}
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
