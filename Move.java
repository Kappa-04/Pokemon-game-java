package it.unibs.pajc;

import java.util.ArrayList;

public class Move {
	
	private String name;
	public enum Category {
		Physical, Special, Status
	}
	private Category cat;
	private Type type;
	private static double critRate = 1/16;
	private int basePower;
	private ArrayList<SecondaryEffect> secondaryEffects;
	private int accuracy;
	
	public int getAccuracy() {
		return accuracy;
	}


	public Move(Category cat, Type type, int basePower, int accuracy, ArrayList<SecondaryEffect> secondaryEffects) {
		this.cat = cat;
		this.type = type;
		this.basePower = basePower;
		this.accuracy = accuracy;
		this.secondaryEffects = secondaryEffects;
		
	}
	
	
	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public ArrayList<SecondaryEffect> getSecondaryEffects() {
		return secondaryEffects;
	}

	public void setSecondaryEffects(ArrayList<SecondaryEffect> secondaryEffects) {
		this.secondaryEffects = secondaryEffects;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCritRate(double critRate) {
		Move.critRate = critRate;
	}

	public int getBasePower() {
		return basePower;
	}

	public void setBasePower(int basePower) {
		this.basePower = basePower;
	}

	public String getName() {
		return this.name;
	}
	
	public double getCritRate() {
		return this.critRate;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	public Category getCategoria () {
		return this.cat;
	}
	
	
}

