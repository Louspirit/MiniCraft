package ui;

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

	public HUDControl(Minicraft app, int width, int height) {
		this.app = app;
		this.width = width;
		this.height = height;
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
	public void displayNextBlock() {
		picture.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + types[++i] , true);
	}
	
	@Override
	public void displayPreviousBlock() {
		picture.setImage(app.getAssetManager(), Constant.TEXTURES_PATH + types[--i] , true);
	}

	@Override
	public void update() {
		app.getGuiNode().attachChild(picture);
	}

	@Override
	public void setBlocksTypes(String[] types) {
		this.types = types;
	}

}
