package ui;

import java.util.Observer;

import com.jme3.scene.Spatial;

public interface IHUDControl extends Observer {
	
	Spatial generatePictureBloc();
	Spatial generatePictureForm();
	
}
