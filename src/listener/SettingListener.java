package listener;

import Player.PlayerSettingChoice;
import com.jme3.input.controls.ActionListener;

public class SettingListener implements ActionListener {

	@Override
	public void onAction(String name, boolean keyPressed, float tpf) {
  	  PlayerSettingChoice setting = PlayerSettingChoice.getInstance();
		 if (name.equals("SwitchBlocUp")) {
			 setting.setNextBlocType();
		 } else if (name.equals("SwitchBlocDown")) {
			 setting.setPreviousBlocType();
		 } else if (name.equals("CreateForm") && !keyPressed) {
			 setting.switchCreatingForm();
		 }  else if (name.equals("CreateFormFull") && !keyPressed) {
			 setting.switchFullForm();
		 }
	}

}
