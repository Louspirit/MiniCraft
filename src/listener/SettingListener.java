package listener;

import Player.PlayerSettingChoice;
import com.jme3.input.controls.ActionListener;

/**
 * Classe modifiant la forme de bloc et sa texture
 * @author Guillaume
 *
 */
public class SettingListener implements ActionListener {
	private PlayerSettingChoice setting = PlayerSettingChoice.getInstance();
	
	/**
	 * @return the setting
	 */
	public PlayerSettingChoice getSetting() {
		return setting;
	}

	@Override
	public void onAction(String name, boolean keyPressed, float tpf) {
  	  
		 if (name.equals("SwitchBlocUp")) {
			 setting.setNextBlocType();
		 } else if (name.equals("SwitchBlocDown")) {
			 setting.setPreviousBlocType();
		 } else if (name.equals("CreateForm") && !keyPressed) {
			 setting.switchCreatingForm();
			 setting.setStockVector(null);
		 }  else if (name.equals("CreateFormFull") && !keyPressed) {
			 setting.switchFullForm();
		 }
	}

}
