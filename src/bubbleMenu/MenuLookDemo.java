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
import java.awt.geom.Point2D;

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
public class MenuLookDemo
{

	JTextArea output;
	JScrollPane scrollPane;
	static JFrame frame;

	static JMenuBar jMenuBar = null;
	static JMenuPrio jMenuActuel = null;

	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar;
		JMenuPrio menu, submenu;
		JMenuItemPrio menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenuPrio("A Menu", true);
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItemPrio("A text-only menu item", KeyEvent.VK_T, true);
		// menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("CA HIDE PAAAAAS");

			}
		});
		menu.add(menuItem);
		System.out.println(menuItem.getBackground());
		System.out.println(menuItem.getForeground());

		ImageIcon icon = createImageIcon("images/middle.gif");
		menuItem = new JMenuItemPrio("Both text and icon", icon);
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);

		// a submenu
		submenu = new JMenuPrio("A submenu", true);
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItemPrio("An item in the submenu", true);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItemPrio("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		// Build second menu in the menu bar.
		menu = new JMenuPrio("Another Menu");
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);

		menuItem = new JMenuItemPrio("A text-only menu item", KeyEvent.VK_T);
		// menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuItem);

		return menuBar;
	}

	public Container createContentPane()
	{
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
	protected static ImageIcon createImageIcon(String path)
	{
		java.net.URL imgURL = MenuLookDemo.class.getResource(path);
		if (imgURL != null)
		{
			return new ImageIcon(imgURL);
		} else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI()
	{
		// Create and set up the window.
		frame = new JFrame("MenuLookDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		MenuLookDemo demo = new MenuLookDemo();

		jMenuBar = demo.createMenuBar();
		frame.setJMenuBar(jMenuBar);

		Container container = demo.createContentPane();
		JPanel jPanel = new JPanel();
		container.add(jPanel);
		frame.setContentPane(container);

		jPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e)
			{
				// TODO Auto-generated method stub
				if (SwingUtilities.isRightMouseButton(e))
				{
					Object object = menuPlusProche(e.getX(), e.getY());
					if(object != null)
					{
						if (object instanceof JMenuPrio)
						{
							JMenuPrio menuPrio = (JMenuPrio) object;
							Point p = new Point(e.getX(), e.getY());
							int distance = (int) p.distance(menuPrio.getX(), menuPrio.getY());
							drawCircle(e.getX(), e.getY(), distance );
							//frame.repaint();
						} 
						else
						{
							JMenuItemPrio itemPrio = (JMenuItemPrio) object;
							Point p = new Point(e.getX(), e.getY());
							int distance = (int) p.distance(itemPrio.getX(), itemPrio.getY());
							drawCircle(e.getX(), e.getY(), distance );
						}
					}
				}
			}

			@Override
			public void mouseMoved(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

		});

		jPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub
				if (SwingUtilities.isRightMouseButton(e))
				{
					Object object = menuPlusProche(e.getX(), e.getY());
					System.out.println(object);
					// System.out.println(jMenuActuel);

					if (object != null)
					{
						if (object instanceof JMenuPrio)
						{
							JMenuPrio subMenu = (JMenuPrio) object;
							subMenu.doClick();
							jMenuActuel = subMenu;
						} else
						{
							JMenuItemPrio item = (JMenuItemPrio) object;
							item.doClick();
							jMenuActuel = null;
						}
					}

				}

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					if (jMenuActuel != null)
					{
						jMenuActuel.doClick();
					}
				}

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

		});

		// Display the window.
		frame.setSize(450, 260);
		frame.setVisible(true);
	}
	
	public static void drawCircle(int x, int y, int taille)
	{
		Graphics g = frame.getGraphics();
        g.drawOval(x - taille/2, y - taille/2, taille, taille);
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 2, 2);
	}

	//Methode pour trouver le menu ou l'item prioritaire le plus proche
	public static Object menuPlusProche(int x, int y)
	{
		double distanceMin = Double.MAX_VALUE;

		// Cas ou on a pas encore choisi de menu -> on check la JMenuBar
		if (jMenuActuel == null)
		{
			System.out.println("menu actuel = null");
			JMenuPrio jMenuPrio;
			JMenuPrio jMenuPrioMin = null;

			Point2D point1;
			double distance;

			for (int i = 0; i < jMenuBar.getMenuCount(); i++)
			{
				jMenuPrio = (JMenuPrio) jMenuBar.getMenu(i);
				// Si c'est un menu prioritaire, alors on regarde la distance
				if (jMenuPrio.prioritaire)
				{
					point1 = new Point(jMenuPrio.getX(), jMenuPrio.getY());
					distance = point1.distance(x, y);
					System.out.println(distance);

					if (distance < distanceMin)
					{
						distanceMin = distance;
						jMenuPrioMin = jMenuPrio;
					}
				}
			}
			return jMenuPrioMin;
		}

		// Cas ou on a deja choisi un menu -> on regarde les items dans ce menu
		else
		{
			JMenuItemPrio jMenuItemPrio;
			Object objectMin = null;
			Object object;
			JMenuPrio jMenuPrio;
			boolean prio = true;

			Point2D point1;
			double distance;

			for (int i = 0; i < jMenuActuel.getItemCount(); i++)
			{
				object = jMenuActuel.getItem(i);

				if (object instanceof JMenuPrio)
				{
					jMenuPrio = (JMenuPrio) object;
					point1 = new Point(jMenuPrio.getX(), jMenuPrio.getY());
					distance = point1.distance(x, y);
					prio = jMenuPrio.prioritaire;

				} else
				{
					jMenuItemPrio = (JMenuItemPrio) object;
					point1 = new Point(jMenuItemPrio.getX(), jMenuItemPrio.getY());
					distance = point1.distance(x, y);
					prio = jMenuItemPrio.prioritaire;
				}

				if (distance < distanceMin && prio)
				{
					distanceMin = distance;
					objectMin = object;
				}

			}
			return objectMin;
		}
	}

	public static void main(String[] args)
	{
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createAndShowGUI();
			}
		});
	}
}