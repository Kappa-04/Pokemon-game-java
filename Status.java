package it.unibs.pajc;

public enum Status {
	NONE, BURN, SLEEP, PARALYSIS, POISON, FROZEN, CONFUSION, DOT;
	
	private boolean isDotEffect; // True if the Pokémon has a DOT effect
	private String dotEffectName; // Name of the DOT effect (e.g., "Leech Seed")
	private int dotDuration;      // Remaining turns for the effect
	private double dotDamagePercentage; // Percentage of max HP dealt each turn
	private boolean isPersistent; // True for infinite duration effects like Leech Seed
	public boolean isPersistentEffect() {
	    return isDotEffect && dotDuration == -1; // True if the DOT effect is persistent
	}

	public boolean isDotEffect() {
		return isDotEffect;
	}
	public String getDotEffectName() {
		return dotEffectName;
	}
	public void setDotEffectName(String dotEffectName) {
		this.dotEffectName = dotEffectName;
	}
	public int getDotDuration() {
		return dotDuration;
	}
	public void setDotDuration(int dotDuration) {
		this.dotDuration = dotDuration;
	}
	public double getDotDamagePercentage() {
		return dotDamagePercentage;
	}
	public void setDotDamagePercentage(double dotDamagePercentage) {
		this.dotDamagePercentage = dotDamagePercentage;
	}
	
	

	public void setDotEffect(String name, double damagePercentage, int duration, boolean isPersistent) {
	    this.isDotEffect = true;
	    this.dotEffectName = name;
	    this.dotDamagePercentage = damagePercentage;
	    this.dotDuration = duration;
	    this.isPersistent = isPersistent;
	}
	
    

    public void clearDotEffect() {
        this.isDotEffect = false;
        this.dotEffectName = null;
        this.dotDuration = 0;
        this.dotDamagePercentage = 0.0;
    }

	public void decrementDotDuration() {
		dotDuration--;
	}
	
	public void applyDotEffect(Pokemon target) {
		for(Status s : target.getStatusManager().getActiveStatuses()) {
			if(s.isDotEffect()) {
				target.setCurrentHp((int)(target.getCurrentHp()- (target.getMaxHp()*s.getDotDamagePercentage())));
				System.out.println(target.getName() + "is hurt by " + s.getDotEffectName());
				if(!s.isPersistentEffect())
			    s.decrementDotDuration();
				
			}
		}
		
		
	}
	







	public void applyStatusEffects(Pokemon pokemon) {
	    

	    // Apply each active status
	    for (Status status : pokemon.getStatusManager().getActiveStatuses()) {
	        switch (status) {
	            case BURN:
	                int burnDamage = pokemon.getMaxHp() / 16;
	                pokemon.setCurrentHp(pokemon.getCurrentHp() - burnDamage);
	                System.out.println(pokemon.getName() + " is hurt by its burn! (" + burnDamage + " HP)");
	                break;

	            case SLEEP:
	                if (pokemon.getStatusDuration() == 0) {
	                    System.out.println(pokemon.getName() + " is fast asleep.");
	                } else if (pokemon.getStatusDuration() < 3) {
	                    if (Utils.extractInt(0, 100) <= 25) {
	                        pokemon.getStatusManager().removeStatus(Status.SLEEP);
	                        System.out.println(pokemon.getName() + " woke up!");
	                    } else {
	                        pokemon.setStatusDuration(pokemon.getStatusDuration() + 1);
	                    }
	                } else {
	                	pokemon.getStatusManager().removeStatus(Status.SLEEP);
	                    System.out.println(pokemon.getName() + " woke up!");
	                }
	                break;

	            case PARALYSIS:
	                if (Utils.extractInt(0, 100) <= 25) { // 25% chance to skip turn
	                    System.out.println(pokemon.getName() + " is paralyzed! It can't move!");
	                   // pokemon.setSkipTurn(true); // Assuming skipTurn flag exists
	                }
	                break;

	            case POISON:
	                int poisonDamage = pokemon.getMaxHp() / 16;
	                pokemon.setCurrentHp(pokemon.getCurrentHp() - poisonDamage);
	                System.out.println(pokemon.getName() + " is hurt by its poison! (" + poisonDamage + " HP)");
	                break;

	            case FROZEN:
	                if (Utils.extractInt(0, 100) < 25) { // 25% chance to thaw
	                	pokemon.getStatusManager().removeStatus(Status.FROZEN);
	                    System.out.println(pokemon.getName() + " thawed out!");
	                } else {
	                    System.out.println(pokemon.getName() + " is frozen solid!");
	                }
	                break;

	            case CONFUSION:
	                if (pokemon.getStatusDuration() > 0) {
	                    if (Utils.extractInt(0, 100) <= 50) { // 50% chance to hurt itself
	                        int confusionDamage = pokemon.getMaxHp() / 8; // Example damage
	                        pokemon.setCurrentHp(pokemon.getCurrentHp() - confusionDamage);
	                        System.out.println(pokemon.getName() + " hurt itself in its confusion! (" + confusionDamage + " HP)");
	                    } else {
	                    	pokemon.getStatusManager().removeStatus(Status.CONFUSION);
	                        System.out.println(pokemon.getName() + " snapped out of confusion!");
	                    }
	                }
	                break;
	            case DOT: 
	            	applyDotEffect(pokemon);
	            default:
	                break;
	        }
	    }

	    
	    

	}


	

}


