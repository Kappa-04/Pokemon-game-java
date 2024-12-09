package it.unibs.pajc;
import java.util.HashSet;
import java.util.Set;

public class StatusManager {
    private Set<Status> activeStatuses; // Set to prevent duplicates
    private boolean hasDotEffect;
    private String dotEffectName;
    private int dotDuration;
    private double dotDamagePercentage;

    public StatusManager() {
        this.activeStatuses = new HashSet<>();
        this.hasDotEffect = false;
    }

    public Set<Status> getActiveStatuses() {
		return activeStatuses;
	}



	public boolean isHasDotEffect() {
		return hasDotEffect;
	}



	public String getDotEffectName() {
		return dotEffectName;
	}



	public int getDotDuration() {
		return dotDuration;
	}



	public double getDotDamagePercentage() {
		return dotDamagePercentage;
	}



	public boolean hasStatus(Status status) {
        return activeStatuses.contains(status);
    }

    public void addStatus(Status status) {
        activeStatuses.add(status);
    }

    public void removeStatus(Status status) {
        activeStatuses.remove(status);
    }

    public Set<Status> getStatuses() {
        return activeStatuses;
    }

    public void clearAllStatuses() {
        activeStatuses.clear();
    }

    public void setDotEffect(String name, double damagePercentage, int duration) {
        this.hasDotEffect = true;
        this.dotEffectName = name;
        this.dotDamagePercentage = damagePercentage;
        this.dotDuration = duration;
    }

    public void clearDotEffect() {
        this.hasDotEffect = false;
        this.dotEffectName = null;
        this.dotDuration = 0;
        this.dotDamagePercentage = 0.0;
    }
}

