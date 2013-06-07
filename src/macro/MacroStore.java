/**
 * 
 */
package macro;

import java.util.LinkedList;
import java.util.List;

import com.jme3.input.controls.ActionListener;

import Player.IPlayerControl;
import World.Block;
import World.IMapControl;


/**
 * @author roulleau
 *
 */
public class MacroStore implements ActionListener{

	private List<Macro> liste;
	private Macro macroActuelle;
	private IMapControl carteGlobale;
	private IPlayerControl player;
	private boolean enregistre;
	
	public MacroStore()
	{
		liste = new LinkedList<Macro>();
		enregistre = false;
	}
	
	public void setMap(IMapControl carte)
	{
		this.carteGlobale = carte;
	}

	@Override
	public void onAction(String name, boolean value, float tpf) {
		if(name.equals("MacroRecStop"))
		{
			enregistre = ! enregistre;
			if(!enregistre)
			{
				this.liste.add(this.macroActuelle);
				this.macroActuelle = null;
			}
		}
		if(name.equals("Replay"))
		{
			
		}
	}
	
	public void addBloc(Block bloc)
	{
		if(enregistre)
		{
			if(this.macroActuelle != null)
			{
				
			}
			else
			{
				//this.macroActuelle = new Macro();
					
			}
			
		}
	}
	
	public void removeBoc(Block bloc)
	{
		
	}
	
	
}
