package ui;

import java.util.Observable;
import java.util.Observer;

import Player.PlayerSettingChoice;

import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;
import util.Constant;

import fenetre.Minicraft;

public class HUDControl implements IHUDControl {
	
	private Minicraft app;
	private int height;
	private int width;
	private String[] types;
	private Picture picture = new Picture("HUD Picture");
	private int i = 0;
	PlayerSettingChoice setting;

	public HUDControl(Minicraft app, int width, int height) {
		this.app = app;
		this.width = width;
		this.height = height;
		setting = PlayerSettingChoice.getInstance();
		setting.setHudControl(this);
	}
	
	@Override
	public Spatial generate() { 
		picture.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + Constant.GRASS , true);
		picture.setWidth(100);
		picture.setHeight(100);
		picture.setPosition(width-100, height-100);
		return picture;
	}

	@Override
	public void setBlocksTypes(String[] types) {
		this.types = types;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		picture.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + setting.getTypeBloc() , true);
	}

}
