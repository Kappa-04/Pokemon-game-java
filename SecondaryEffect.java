package it.unibs.pajc;

public class SecondaryEffect {
    private String effectType; 
    private String stat;       
    private int statChange;    
    private int chance;   
    private int minHits;
    private int maxHits;
    private double recoilMultiplier;
    private int duration; // Turns the effect lasts
    private double drainPercentage; // Percentage of damage/healing per turn
    private boolean isTwoTurn; // True if move requires two turns
    private int fixedDamage;
    private int lastDamageDealt;
    

 // Constructor for Counter
 public SecondaryEffect(String effectType) {
     this.effectType = effectType;
     if (effectType.equals("COUNTER")) {
         this.lastDamageDealt = 0; // Initialize counter
     }
 }

 // Getter and Setter
 public int getLastDamageDealt() {
     return lastDamageDealt;
 }

 public void setLastDamageDealt(int lastDamageDealt) {
     this.lastDamageDealt = lastDamageDealt;
 }

 // Constructor for fixed-damage moves
    public SecondaryEffect(String effectType, int fixedDamage, boolean isFixedDamage) {
        this.effectType = effectType;
        this.fixedDamage = fixedDamage; // Only used for fixed-damage moves
    }


 // Constructor for two-turn moves
 public SecondaryEffect(String effectType, boolean isTwoTurn) {
     this.effectType = effectType;
     this.isTwoTurn = isTwoTurn;
 }


    // Constructor for DoT/Drain effects
    public SecondaryEffect(String effectType, double drainPercentage, int duration) {
        this.effectType = effectType;
        this.drainPercentage = drainPercentage;
        this.duration = duration;
    }
    
    


    // Constructor for non-stat effects
    public SecondaryEffect(String effectType, int chance) {
        this.effectType = effectType;
        this.chance = chance;
    }

    // Constructor for stat-changing effects
    public SecondaryEffect(String effectType, String stat, int statChange, int chance) {
        this.effectType = effectType;
        this.stat = stat;
        this.statChange = statChange;
        this.chance = chance;
    }
    
    public SecondaryEffect(String effectType, int minHits, int maxHits) {
        this.effectType = effectType;
        this.minHits = minHits;
        this.maxHits = maxHits;
    }
    
    public SecondaryEffect (String effectType, double recoilMultiplier) {
    	this.effectType = effectType;
    	this.recoilMultiplier = recoilMultiplier;
    }
    
    public double getRecoilMultiplier() {
    	return recoilMultiplier;
    }
    

    public int getMinHits() {
		return minHits;
	}

	public int getMaxHits() {
		return maxHits;
	}

	public String getEffectType() {
        return effectType;
    }

    public String getStat() {
        return stat;
    }

    public int getStatChange() {
        return statChange;
    }

    public int getChance() {
        return chance;
    }

	public double getDrainPercentage() {
		// TODO Auto-generated method stub
		return drainPercentage;
	}

	public int getDuration() {
		return duration;
	}
}


