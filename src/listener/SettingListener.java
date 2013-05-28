package listener;

import Player.PlayerSettingChoice;
import World.Block;

import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;

public class SettingListener implements ActionListener {

	@Override
	public void onAction(String name, boolean keyPressed, float tpf) {
		 if (name.equals("SwitchBlocUp")) {
			 PlayerSettingChoice.setNextBlocType();
		 } else if (name.equals("SwitchBlocDown")) {
			 PlayerSettingChoice.setPreviousBlocType();
		 }
	}

}
