package listener;

import util.PCalcul;
import Player.PlayerSettingChoice;
import World.*;
import World.block.Block;

import com.jme3.collision.CollisionResult;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;

import fenetre.Minicraft;

/**
 * Classe permettant de poser et supprimer des blocs
 * @author Guillaume
 *
 */
public class BlocListener implements ActionListener {

	private IMapControl mapControl;
	private IBlockControl blockControl;

	public BlocListener(IMapControl mapControl, IBlockControl blockControl) 
	{
		this.mapControl = mapControl;
		this.blockControl = blockControl;
	}


	public void onAction(String name, boolean keyPressed, float tpf) {
		if ((name.equals("Add") || name.equals("Delete")) && !keyPressed) {

			CollisionResult closest = PCalcul.targetBloc();

			if(closest != null)
			{
				Vector3f coord =  closest.getGeometry().getWorldBound().getCenter();

				if (mapControl.existBloc(coord) && closest.getDistance() < 5) 
				{
					PlayerSettingChoice setting = PlayerSettingChoice.getInstance();
					Block block = mapControl.getBlock(coord);
					
					if (name.equals("Add") && !setting.isCreatingForm() && !setting.isMacro()) 
					{
						blockControl.newBlocNextTo(block, PCalcul.calculDirection(coord, closest.getContactPoint()), true);
					} 
					else if (name.equals("Delete")) 
					{
						blockControl.deleteBloc(block);
					}
					else if (name.equals("Add") && setting.isCreatingForm()) 
					{
						if (setting.getStockVector() != null) 
						{
							blockControl.createRectangle(setting.getStockVector(), blockControl.newBlocNextTo(block, PCalcul.calculDirection(coord, closest.getContactPoint()), false).getCoord(), setting.isFormFull());
							setting.initStockVector();
						}
						else
						{
							setting.setStockVector(blockControl.newBlocNextTo(block, PCalcul.calculDirection(coord, closest.getContactPoint()), false).getCoord());
						}
					}else if(name.equals("Add") && setting.isMacro()){
						Minicraft.getInstance().getMacroStore().replayMacro();
					}
				}
			}		
		}
	}
}
