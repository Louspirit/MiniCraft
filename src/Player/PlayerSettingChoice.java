package Player;

import java.util.HashMap;

import com.jme3.math.Vector3f;


public class PlayerSettingChoice {
	
	private static HashMap<Integer, String> bloc = new HashMap<Integer, String>();
	private static String[] listeTypeBloc = {"grass.jpg", "terre.jpg", "beton.jpg", "water.jpg", 
			"wood.jpg", "puppy.jpg" , "sexy.jpg"};
	private static int currentBlocType = 0;
	
	private static Vector3f stockFirstVector;
	private static boolean isCreatingForm;
	private static boolean isFormFull;
	
	public static void init() {
		for (int i=0; i < listeTypeBloc.length ; i++) {
			bloc.put(i, listeTypeBloc[i]);
		}
		stockFirstVector = null;
	}
	
	public static void setNextBlocType() {
		
		if (currentBlocType<listeTypeBloc.length-1) {
			currentBlocType++;
		}
		System.out.println("Bloc selectionnee : " + getTypeBloc());		
	}
	
	public static void setPreviousBlocType() {
		if (currentBlocType>0) {
			currentBlocType--;
		}
		System.out.println("Bloc selectionnee : " + getTypeBloc());
	}
	
	public static String getTypeBloc() {
		return bloc.get(currentBlocType);
	}
	
	public static void switchCreatingForm() {
		if (isCreatingForm==true) {
			initStockVector();
		}
		isCreatingForm = !isCreatingForm;
		System.out.println("Create Form ? " + isCreatingForm);
	}
	
	public static boolean isCreatingForm() {
		return isCreatingForm;
	}
	
	public static void setStockVector(Vector3f vector) {
		stockFirstVector = vector;
	}
	
	public static void initStockVector() {
		stockFirstVector = null;
	}
	
	public static Vector3f getStockVector() {
		return stockFirstVector;
	}
	
	public static void switchFullForm() {
		isFormFull = !isFormFull;
		System.out.println("Create Form Full ? " + isFormFull);
	}
	
	public static boolean isFormFull() {
		return isFormFull;
	}

}
