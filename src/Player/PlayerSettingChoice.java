package Player;

import java.util.HashMap;
import java.util.Observable;

import ui.IHUDControl;
import util.Constant;

import com.jme3.math.Vector3f;


public class PlayerSettingChoice extends Observable {
	
	private HashMap<Integer, String> bloc = new HashMap<Integer, String>();
	private String[] listeTypeBloc = {"grass.jpg", "terre.jpg", "beton.jpg", "water.jpg", 
			"wood.jpg", "puppy.jpg" };
	private int currentBlocType = 0;
	
	private Vector3f stockFirstVector;
	private boolean isCreatingForm;
	private boolean isFormFull;
	private String mode;
    
    
    private static PlayerSettingChoice instance;
    
    private PlayerSettingChoice() {
		for (int i=0; i < listeTypeBloc.length ; i++) {
			bloc.put(i, listeTypeBloc[i]);
		}
		stockFirstVector = null;
		mode = Constant.Bloc;
	}
    
    public final static PlayerSettingChoice getInstance() {
    	if (PlayerSettingChoice.instance == null) {
    		PlayerSettingChoice.instance = new PlayerSettingChoice();
    	}
    	
    	return PlayerSettingChoice.instance;
    }
	
	public void setHudControl(IHUDControl hudControl) {
		this.addObserver(hudControl);
	}
	
	public void setNextBlocType() {
		
		if (currentBlocType<listeTypeBloc.length-1) {
			currentBlocType++;
			setChanged();
			notifyObservers();
		}
	}
	
	public void setPreviousBlocType() {
		if (currentBlocType>0) {
			currentBlocType--;
			setChanged();
			notifyObservers();
		}
	}
	
	public String getTypeBloc() {
		return bloc.get(currentBlocType);
	}
	
	public String getMode() {
		return mode;
	}
	
	public void switchCreatingForm() {
		isCreatingForm = !isCreatingForm;
		setGoodForm();
		setChanged();
		notifyObservers();
	}
	
	
	public void switchFullForm() {
		isFormFull = !isFormFull;
		setGoodForm();
		setChanged();
		notifyObservers();
	}
	
	private void setGoodForm() {
		if (isCreatingForm && isFormFull) {
			mode = Constant.FormFull;
		} else if (isCreatingForm && !isFormFull) {
			mode = Constant.Form;
		} else {
			mode = Constant.Bloc;
		}
	}
	
	public boolean isCreatingForm() {
		return isCreatingForm;
	}
	
	public void setStockVector(Vector3f vector) {
		stockFirstVector = vector;
	}
	
	public void initStockVector() {
		stockFirstVector = null;
	}
	
	public Vector3f getStockVector() {
		return stockFirstVector;
	}
	
	public boolean isFormFull() {
		return isFormFull;
	}

}
