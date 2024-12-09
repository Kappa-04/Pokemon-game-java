package it.unibs.pajc;
import java.util.HashMap;

public class Type {
	
	private String type;
	private HashMap<String, Type> weaknessesList;
	private HashMap<String, Type> immunitiesList;
	private HashMap<String, Type> resistancesList;

	
	
	
	public Type(String type, HashMap<String, Type> weaknesses,
				HashMap<String, Type> immunities, HashMap<String, Type> resistances ) {
		this.type = type;
		this.weaknessesList = weaknesses;
		this.immunitiesList = immunities;
		this.resistancesList = resistances;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<String, Type> getWeaknessesList() {
		return weaknessesList;
	}

	public void setWeaknessesList(HashMap<String, Type> weaknesses) {
		this.weaknessesList = weaknesses;
	}

	public HashMap<String, Type> getResistancesList() {
		return resistancesList;
	}

	public void setResistancesList(HashMap<String, Type> resistancesList) {
		this.resistancesList = resistancesList;
	}

	public HashMap<String, Type> getImmunitiesList() {
		return immunitiesList;
	}

	public void setImmunitiesList(HashMap<String, Type> immunities) {
		this.immunitiesList = immunities;
	}

	
	
	
}
