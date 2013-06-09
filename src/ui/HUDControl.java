package ui;

import java.util.Observable;

import Player.PlayerSettingChoice;

import com.jme3.math.Vector2f;
import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;
import util.Constant;

import fenetre.Minicraft;

public class HUDControl implements IHUDControl {
	
	//private Minicraft app;
	private int height;
	private int width;
	private Picture pictureBloc = new Picture("HUD Picture");
	PlayerSettingChoice setting;
	private Picture pictureForm = new Picture("HUD Picture Form");

	public HUDControl(int width, int height) {
		this.width = width;
		this.height = height;
		setting = PlayerSettingChoice.getInstance();
		setting.setHudControl(this);
	}
	
	/**
	 * Positionne l'aperçu du bloc (texture) dans l'HUD
	 */
	@Override
	public Spatial generatePictureBloc() 
	{ 
		return generatePicture(pictureBloc, new Vector2f(-100, -100), Constant.TEXTURES_PATH + setting.getDefautType().getTexture());
	}
	
	/**
	 * Positionne l'aperçu du mode (bloc, forme, ...) dans l'HUD
	 */
	@Override
	public Spatial generatePictureForm() 
	{ 
		return generatePicture(pictureForm, new Vector2f(-200, -100), Constant.TEXTURES_PATH + setting.getMode());
	}
	
	private Spatial generatePicture(Picture picture, Vector2f position, String texturePath)
	{
		picture.setImage(Minicraft.getInstance().getAssetManager(), texturePath, true);
		picture.setWidth(100);
		picture.setHeight(100);
		picture.setPosition(width + position.x, height + position.y);
		return picture;
	}

	/**
	 * Met à jour les informations affichés dans l'HUD
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		pictureBloc.setImage(Minicraft.getInstance().getAssetManager(), Constant.TEXTURES_PATH + setting.getTypeBloc().getTexture() , true);
		pictureForm.setImage(Minicraft.getInstance().getAssetManager(), Constant.TEXTURES_PATH + setting.getMode() , true);
	}
}
