package Player;

import java.util.HashMap;
import java.util.Observable;

import ui.IHUDControl;

import com.jme3.math.Vector3f;


public class PlayerSettingChoice extends Observable {
	
	private HashMap<Integer, String> bloc = new HashMap<Integer, String>();
	private String[] listeTypeBloc = {"grass.jpg", "terre.jpg", "beton.jpg", "water.jpg", 
			"wood.jpg", "puppy.jpg" , "sexy.jpg"};
	private int currentBlocType = 0;
	
	private Vector3f stockFirstVector;
	private boolean isCreatingForm;
	private boolean isFormFull;
    
    
    private static PlayerSettingChoice instance;
    
    private PlayerSettingChoice() {
		for (int i=0; i < listeTypeBloc.length ; i++) {
			bloc.put(i, listeTypeBloc[i]);
		}
		stockFirstVector = null;
	}
    
    public final static PlayerSettingChoice getInstance() {
    	if (PlayerSettingChoice.instance == null) {
    		PlayerSettingChoice.instance = new PlayerSettingChoice();
    	}
    	
    	return PlayerSettingChoice.instance;
    }
	
	public void setHudControl(IHUDControl hudControl) {
        hudControl.setBlocksTypes(listeTypeBloc);
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
	
	public void switchCreatingForm() {
		if (isCreatingForm==true) {
			initStockVector();
		}
		isCreatingForm = !isCreatingForm;
		System.out.println("Create Form ? " + isCreatingForm);
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
	
	public void switchFullForm() {
		isFormFull = !isFormFull;
		System.out.println("Create Form Full ? " + isFormFull);
	}
	
	public boolean isFormFull() {
		return isFormFull;
	}

}
