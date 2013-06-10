/**
 * 
 */
package macro;

import java.util.LinkedList;
import java.util.List;

import com.jme3.input.controls.ActionListener;

import World.IMapControl;
import World.block.Block;


/**
 * @author roulleau
 *
 */
public class MacroStore implements ActionListener{

	private List<Macro> liste;
	private Macro macroActuelle;
	private IMapControl carteGlobale;
	private boolean enregistre;
	private int marqueur;
	
	private final String NAME = "macro";
	private static int compt =0;
	
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
		if(name.equals("MacroRecStop") && !value)
		{
			enregistre = ! enregistre;
			if(!enregistre && macroActuelle != null)
			{
				this.liste.add(this.macroActuelle);
				this.macroActuelle = null;
			}
		}
		if(name.equals("PlayMacro") && ! value)
		{
			replayMacro();
		}
		
		if(name.equals("NextMacro")&& ! value)
		{
			changeMacro(1);
		}
		if(name.equals("PreviousMacro") && ! value)
		{
			changeMacro(-1);
		}
	}
	
	public void addBloc(Block bloc)
	{
		doAction(ActionTypeMacro.addBlock, bloc);
	}
	
	public void removeBoc(Block bloc)
	{
		doAction(ActionTypeMacro.removeBlock, bloc);
	}
	
	private void doAction(ActionTypeMacro action, Block bloc)
	{
		if(enregistre)
		{
			if(this.macroActuelle == null)
			{
				this.macroActuelle = new Macro(NAME+compt,bloc.getCoord());
				compt++;
			}
			this.macroActuelle.recordMacro(action, bloc, bloc.getCoord())	;		
		}
	}
	
	private void replayMacro()
	{
		if(liste.size() > 0 && liste.get(marqueur)!= null)
		{	
			liste.get(marqueur).replay(carteGlobale);
		}
		
	}
	
	private void changeMacro(int dif)
	{
		if (liste.size()>0) {
			
			marqueur = (marqueur+dif)%liste.size();
			if(marqueur < 0) 
			{
				marqueur = liste.size() -1;
			}
			System.out.println("Change macro marqueur :"+marqueur);
		}
	}
}
