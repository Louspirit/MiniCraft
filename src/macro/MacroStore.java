/**
 * 
 */
package macro;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jme3.input.controls.ActionListener;
import com.jme3.util.ListMap;

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
	
	public MacroStore(IMapControl carte)
	{
		carteGlobale = carte;
		liste = new LinkedList<Macro>();
		enregistre = false;
		
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
	
	
}
