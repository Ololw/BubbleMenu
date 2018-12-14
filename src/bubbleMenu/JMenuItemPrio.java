package bubbleMenu;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class JMenuItemPrio extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean prioritaire = false ;
	Color couleur = new Color(175,175,230);
	
	public JMenuItemPrio() {
		super();
		this.setOpaque(true);			

	}
	public JMenuItemPrio(Action a) {
		super(a);
		this.setOpaque(true);			

	}
	public JMenuItemPrio(Icon icon) {
		super(icon);
		this.setOpaque(true);			

	}
	public JMenuItemPrio(String text, Icon icon) {
		super(text, icon);
		this.setOpaque(true);			

	}
	public JMenuItemPrio(String text, int mnemonic) {
		super(text, mnemonic);
		this.setOpaque(true);			

	}
	public JMenuItemPrio(String text) {
		super(text);
		this.setOpaque(true);			

	}
	
	public JMenuItemPrio(boolean prio) {
		super();
		prioritaire = prio;
		this.setOpaque(true);			
		changeBg();
	}
	public JMenuItemPrio(Action a, boolean prio) {
		super(a);
		prioritaire = prio;
		this.setOpaque(true);			
		changeBg();
	}
	public JMenuItemPrio(Icon icon, boolean prio) {
		super(icon);
		prioritaire = prio;
		this.setOpaque(true);			
		changeBg();
	}
	public JMenuItemPrio(String text, Icon icon, boolean prio) {
		super(text, icon);
		prioritaire = prio;
		this.setOpaque(true);			
		changeBg();
	}
	public JMenuItemPrio(String text, int mnemonic, boolean prio) {
		super(text, mnemonic);
		prioritaire = prio;
		this.setOpaque(true);			
		changeBg();
	}
	public JMenuItemPrio(String text, boolean prio) {
		super(text);
		prioritaire = prio;
		this.setOpaque(true);			
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

	
	

}
