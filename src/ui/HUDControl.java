package ui;

import java.util.Observable;

import Player.PlayerSettingChoice;

import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;
import util.Constant;

import fenetre.Minicraft;

public class HUDControl implements IHUDControl {
	
	private Minicraft app;
	private int height;
	private int width;
	private Picture pictureBloc = new Picture("HUD Picture");
	PlayerSettingChoice setting;
	private Picture pictureForm = new Picture("HUD Picture Form");

	public HUDControl(Minicraft app, int width, int height) {
		this.app = app;
		this.width = width;
		this.height = height;
		setting = PlayerSettingChoice.getInstance();
		setting.setHudControl(this);
	}
	
	@Override
	public Spatial generatePictureBloc() { 
		pictureBloc.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + Constant.GRASS , true);
		pictureBloc.setWidth(100);
		pictureBloc.setHeight(100);
		pictureBloc.setPosition(width-100, height-100);
		return pictureBloc;
	}
	
	@Override
	public Spatial generatePictureForm() { 
		pictureForm.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + setting.getMode(), true);
		pictureForm.setWidth(100);
		pictureForm.setHeight(100);
		pictureForm.setPosition(width-200, height-100);
		return pictureForm;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		pictureBloc.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + setting.getTypeBloc() , true);
		pictureForm.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + setting.getMode() , true);
	}

}
