package Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import ui.IHUDControl;
import util.BlockType;
import util.Constant;

import com.jme3.math.Vector3f;


public class PlayerSettingChoice extends Observable {
	
	private List<BlockType> listeTypeBloc;
	private int currentBlocType;
	
	private Vector3f stockFirstVector;
	private boolean isCreatingForm;
	private boolean isFormFull;
	private String mode;
    
    
    private static PlayerSettingChoice instance;
    
    private PlayerSettingChoice() 
    {
    	listeTypeBloc = new LinkedList<BlockType>();

    	listeTypeBloc.add(new BlockType("Terre", Constant.DIRT));
    	listeTypeBloc.add(new BlockType("Pierre", Constant.CONCRETE));
    	listeTypeBloc.add(new BlockType("Herbe", Constant.GRASS));
    	listeTypeBloc.add(new BlockType("Bois", Constant.WOOD));
    	listeTypeBloc.add(new BlockType("Eau", Constant.WATER));
    	listeTypeBloc.add(new BlockType("Chiot", Constant.PUPPY));
    	
    	currentBlocType = 0;//listeTypeBloc.listIterator();
		stockFirstVector = null;
		mode = Constant.Bloc;
	}
    
    public final static PlayerSettingChoice getInstance() 
    {
    	if (PlayerSettingChoice.instance == null) 
    	{
    		PlayerSettingChoice.instance = new PlayerSettingChoice();
    	}
    	return PlayerSettingChoice.instance;
    }
	
	public void setHudControl(IHUDControl hudControl) 
	{
		this.addObserver(hudControl);
	}
	
	public void setNextBlocType() 
	{	
		if (currentBlocType < listeTypeBloc.size()-1) {
			currentBlocType++;
			setChanged();
			notifyObservers();
		}
	}
	
	public void setPreviousBlocType() {
		if (currentBlocType> 0) {
			currentBlocType--;
			setChanged();
			notifyObservers();
		}
	}
	
	public BlockType getTypeBloc() {
		return listeTypeBloc.get(currentBlocType);
	}
	
	public BlockType getDefautType()
	{
		return listeTypeBloc.get(0);
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
