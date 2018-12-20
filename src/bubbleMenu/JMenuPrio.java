package bubbleMenu;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JMenu;
import javax.swing.SwingUtilities;

import sun.swing.SwingUtilities2;

public class JMenuPrio extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean prioritaire = false ;
	Color couleur = new Color(175,175,230);
	
	public JMenuPrio(String nom, boolean prio)
	{
		super(nom);
		this.setOpaque(true);
		prioritaire = prio;
		changeBg();
	}
	
	public JMenuPrio(String nom)
	{
		super(nom);
		this.setOpaque(true);
		prioritaire = false;
		changeBg();
	}
	
	
	public void changeBg()
	{
		if(prioritaire)
		{
			this.setBackground(couleur);
			this.setForeground(Color.WHITE);
		}
		else
		{
			this.setBackground(new Color(238,238,238));
			this.setForeground(new Color(51,51,51));
		}
	}
	
	public void changeCouleur(Color couleur)
	{
		this.couleur = couleur;
		changeBg();
	}
	
	public void changePrio()
	{
		if (prioritaire)
			prioritaire = false;
		else
			prioritaire = true;
	}
	

	
	
	

}
