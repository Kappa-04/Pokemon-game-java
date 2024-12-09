package it.unibs.pajc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Pokemon {
	
	private String name;
	private BufferedImage img;
	private int level;
	private Type type1;
	private Type type2;
	private int baseAtk; private int baseDef; private int baseSpAtk; private int baseSpDef; private int baseSpe; private int basehp;
	private int currentAtk; private int currentDef; private int currentSpAtk; private int currentSpDef; private int currentSpe; private int currentHp;
	private int atkStage; private int defStage; private int spAtkStage; private int spDefStage; private int speStage;
	private int maxHp;
	private int evolutionLvl;
	private ArrayList<Move> moveList;
	private StatusManager statusManager;
	private long exp;
	private int statusDuration; 
	private boolean isStatusActive; 
	public enum Stat {
		ATTACK, DEFENSE, SP_ATTACK, SP_DEFENSE, SPEED, HP
	}
	public int getSpAtkStage() {
		return spAtkStage;
	}
	public void setSpAtkStage(int spAtkStage) {
		this.spAtkStage = spAtkStage;
	}
	public int getSpDefStage() {
		return spDefStage;
	}
	public void setSpDefStage(int spDefStage) {
		this.spDefStage = spDefStage;
	}
	public boolean isStatusActive() {
		return isStatusActive;
	}
	public void setStatusActive(boolean isStatusActive) {
		this.isStatusActive = isStatusActive;
	}
	public int getSpeStage() {
		return speStage;
	}
	public StatusManager getStatusManager() {
		return statusManager;
	}
	
	
	public Pokemon(String name, int explevel, Type type1, Type type2, int evolutionLvl,
								ArrayList<Move> moveList, BufferedImage img, long exp) {
		this.name = name;
		this.level = explevel;
		this.type1 = type1;
		this.type2 = type2;
		this.evolutionLvl = evolutionLvl;
		this.moveList = new ArrayList<Move>();
		this.statusManager = new StatusManager();
		this.exp = 0;
		try {
			this.img = ImageIO.read(new File(name + "png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void reduceStatusDuration(Pokemon pokemon, Status status) {
	    if (statusDuration > 0) {
	        statusDuration--;
	        if (statusDuration == 0) {
	            pokemon.getStatusManager().removeStatus(status);
	            isStatusActive = false;
	        }
	    }
	}
	
	public int getAtkStage() {
	    return atkStage;
	}

	public void setAtkStage(int atkStage) {
	    this.atkStage = Math.max(-6, Math.min(6, atkStage)); 
	}
	
	public int getDefStage() {
	    return defStage;
	}

	public void setDefStage(int defStage) {
	    this.defStage = Math.max(-6, Math.min(6, atkStage)); 
	}
	
	public int getspAtkStage() {
	    return spAtkStage;
	}

	public void setspAtkStage(int spAtkStage) {
	    this.spAtkStage = Math.max(-6, Math.min(6, atkStage)); 
	}
	
	public int getspDefStage() {
	    return spDefStage;
	}

	public void setspDefStage(int atkStage) {
	    this.spDefStage = Math.max(-6, Math.min(6, spDefStage)); 
	}
	
	public int getspeStage() {
	    return speStage;
	}

	public void setSpeStage(int atkStage) {
	    this.speStage = Math.max(-6, Math.min(6, speStage));
	}
	
	public void adjustStatStage(Stat stat, int change) {
	    switch (stat) {
	        case ATTACK:
	            setAtkStage(atkStage + change);
	            break;
	        case DEFENSE:
	        	setDefStage(defStage + change);
	        	break;
	        case SP_ATTACK:
	        	setspAtkStage(spAtkStage + change);
	        	break;
	        case SP_DEFENSE: 
	        	setspDefStage(spDefStage + change);
	        	break;
	        case SPEED: 
	        	setSpeStage(speStage + change);
	        	break;
	        case HP: break;
	    }
	}
	
	public int getModifiedStat(int baseStat, int stage) {
	    double[] stageMultipliers = {0.25, 0.29, 0.33, 0.4, 0.5, 0.66, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
	    return (int) (baseStat * stageMultipliers[stage + 6]);
	}


	
	public String getName() {return name;}
	public void setName(String nome) {this.name = nome;}
	public int getLvl() {return level;}
	public void setLvl(int livello) {this.level = livello;}
	public Type getType1() {return type1;}
	public void setType1(Type type1) {this.type1 = type1;}
	public Type getType2() {return type2;}
	public BufferedImage getImg() {return img;}
	public void setImg(BufferedImage img) {this.img = img;}
	public int getLevel() {return level;}
	public void setLevel(int level) {this.level = level;}
	public int getBaseAtk() {return baseAtk;}
	public void setBaseAtk(int baseAtk) {this.baseAtk = baseAtk;}
	public int getBaseDef() {return baseDef;}
	public void setBaseDef(int baseDef) {this.baseDef = baseDef;}
	public int getBaseSpAtk() {return baseSpAtk;}
	public void setBaseSpAtk(int baseSpAtk) {this.baseSpAtk = baseSpAtk;}
	public int getBaseSpDef() {return baseSpDef;}
	public void setBaseSpDef(int baseSpDef) {this.baseSpDef = baseSpDef;}
	public int getBaseSpe() {return baseSpe;}
	public void setBaseSpe(int baseSpe) {this.baseSpe = baseSpe;}
	public int getBasehp() {return basehp;}
	public void setBasehp(int basehp) {this.basehp = basehp;}
	public int getCurrentAtk() {return currentAtk;}
	public void setCurrentAtk(int currentAtk) {this.currentAtk = currentAtk;}
	public int getCurrentDef() {return currentDef;}
	public void setCurrentDef(int currentDef) {this.currentDef = currentDef;}
	public int getCurrentSpAtk() {return currentSpAtk;}
	public void setCurrentSpAtk(int currentSpAtk) {this.currentSpAtk = currentSpAtk;}
	public int getCurrentSpDef() {return currentSpDef;}
	public void setCurrentSpDef(int currentSpDef) {this.currentSpDef = currentSpDef;}
	public int getCurrentSpe() {return currentSpe;}
	public void setCurrentSpe(int currentSpe) {this.currentSpe = currentSpe;}
	public int getCurrentHp() {return currentHp;}
	public void setCurrentHp(int currentHp) {this.currentHp = currentHp;}
	public int getMaxHp() {return maxHp;}
	public long getExp() {return exp;}
	public void setExp(long exp) {this.exp = exp;}
	public void setType2(Type type2) {this.type2 = type2;}
	public int getEvolutionLvl() {return evolutionLvl;}
	public void setEvolutionLvl(int evolutionLvl) {this.evolutionLvl = evolutionLvl;}
	public ArrayList<Move> getMoveList() {return moveList;}
	public void setMoveList(ArrayList<Move> listaMosse) {this.moveList = listaMosse;}
	public int getStatusDuration() {return statusDuration;}
	public void setStatusDuration(int statusDuration) {this.statusDuration = statusDuration;}
	
	

	
	
}
