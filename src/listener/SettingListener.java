package listener;

import Player.PlayerSettingChoice;
import com.jme3.input.controls.ActionListener;

public class SettingListener implements ActionListener {

	@Override
	public void onAction(String name, boolean keyPressed, float tpf) {
		 if (name.equals("SwitchBlocUp")) {
			 PlayerSettingChoice.setNextBlocType();
		 } else if (name.equals("SwitchBlocDown")) {
			 PlayerSettingChoice.setPreviousBlocType();
		 } else if (name.equals("CreateForm") && !keyPressed) {
			 PlayerSettingChoice.switchCreatingForm();
		 }  else if (name.equals("CreateFormFull") && !keyPressed) {
			 PlayerSettingChoice.switchFullForm();
		 }
	}

}
