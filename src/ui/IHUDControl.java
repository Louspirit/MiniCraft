package ui;

import com.jme3.scene.Spatial;

public interface IHUDControl {
	
	Spatial generate();
	void update();
	void displayNextBlock();
	void displayPreviousBlock();
	void setBlocksTypes(String[] types);
	
}
