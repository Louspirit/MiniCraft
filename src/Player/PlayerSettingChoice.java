package Player;

import java.util.HashMap;

import util.Constant;

public class PlayerSettingChoice {
	
	private static HashMap<Integer, String> bloc = new HashMap<Integer, String>();
	private static String[] listeTypeBloc = {"grass.jpg", "terre.jpg", "beton.jpg", "water.jpg", 
			"wood.jpg", "puppy.jpg" , "sexy.jpg"};
	private static int currentBlocType = 0;
	
	public static void init() {
		for (int i=0; i < listeTypeBloc.length ; i++) {
			bloc.put(i, listeTypeBloc[i]);
		}
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

}
