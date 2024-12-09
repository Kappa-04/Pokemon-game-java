package it.unibs.pajc;

public enum Status {
	NONE, BURN, SLEEP, PARALYSIS, POISON, FROZEN, CONFUSION, DOT;
	
	private boolean hasDotEffect; // True if the Pokémon has a DOT effect
	private String dotEffectName; // Name of the DOT effect (e.g., "Leech Seed")
	private int dotDuration;      // Remaining turns for the effect
	private double dotDamagePercentage; // Percentage of max HP dealt each turn
	private boolean isPersistent; // True for infinite duration effects like Leech Seed
	public boolean isPersistentEffect() {
	    return hasDotEffect && dotDuration == -1; // True if the DOT effect is persistent
	}

	public boolean isHasDotEffect() {
		return hasDotEffect;
	}
	public void setHasDotEffect(boolean hasDotEffect) {
		this.hasDotEffect = hasDotEffect;
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
	
	public void applyDotEffect(Pokemon target) {
	    if (hasDotEffect && dotDuration > 0) {
	        int damage = (int) (target.getMaxHp() * dotDamagePercentage);
	        target.setCurrentHp(Math.max(0, target.getCurrentHp() - damage));
	        System.out.println(target.getName() + " is hurt by " + dotEffectName + " for " + damage + " damage!");
	        dotDuration--;

	        if (dotDuration == 0) {
	            System.out.println(target.getName() + " is no longer affected by " + dotEffectName + ".");
	            hasDotEffect = false;
	        }
	    }
	}
	
	

	public void setDotEffect(String name, double damagePercentage, int duration, boolean isPersistent) {
	    this.hasDotEffect = true;
	    this.dotEffectName = name;
	    this.dotDamagePercentage = damagePercentage;
	    this.dotDuration = duration;
	    this.isPersistent = isPersistent;
	}







	public void applyStatusEffects(Pokemon pokemon) {
	    StatusManager statusManager = pokemon.getStatusManager();

	    // Apply each active status
	    for (Status status : statusManager.getStatuses()) {
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
	                        statusManager.removeStatus(Status.SLEEP);
	                        System.out.println(pokemon.getName() + " woke up!");
	                    } else {
	                        pokemon.setStatusDuration(pokemon.getStatusDuration() + 1);
	                    }
	                } else {
	                    statusManager.removeStatus(Status.SLEEP);
	                    System.out.println(pokemon.getName() + " woke up!");
	                }
	                break;

	            case PARALYSIS:
	                if (Utils.extractInt(0, 100) <= 25) { // 25% chance to skip turn
	                    System.out.println(pokemon.getName() + " is paralyzed! It can't move!");
	                    pokemon.setSkipTurn(true); // Assuming skipTurn flag exists
	                }
	                break;

	            case POISON:
	                int poisonDamage = pokemon.getMaxHp() / 16;
	                pokemon.setCurrentHp(pokemon.getCurrentHp() - poisonDamage);
	                System.out.println(pokemon.getName() + " is hurt by its poison! (" + poisonDamage + " HP)");
	                break;

	            case FROZEN:
	                if (Utils.extractInt(0, 100) < 25) { // 25% chance to thaw
	                    statusManager.removeStatus(Status.FROZEN);
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
	                        statusManager.removeStatus(Status.CONFUSION);
	                        System.out.println(pokemon.getName() + " snapped out of confusion!");
	                    }
	                }
	                break;

	            default:
	                break;
	        }
	    }

	    // Apply DOT effects if active
	    if (statusManager.hasDotEffect()) {
	        int dotDamage = (int) (pokemon.getMaxHp() * statusManager.getDotDamagePercentage());
	        pokemon.setCurrentHp(Math.max(0, pokemon.getCurrentHp() - dotDamage));
	        System.out.println(pokemon.getName() + " is hurt by " + statusManager.getDotEffectName() + "! (" + dotDamage + " HP)");
	        statusManager.decrementDotDuration();

	        if (statusManager.getDotDuration() <= 0 && !statusManager.isPersistentEffect()) {
	            statusManager.clearDotEffect();
	            System.out.println(pokemon.getName() + " is no longer affected by " + statusManager.getDotEffectName() + ".");
	        }
	    }
	}


	

}


