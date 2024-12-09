package it.unibs.pajc;
import java.util.HashSet;
import java.util.Set;

public class StatusManager {
    private Set<Status> activeStatuses; // Set to prevent duplicates

    public StatusManager() {
        this.activeStatuses = new HashSet<>();
        
    }

    public Set<Status> getActiveStatuses() {
		return activeStatuses;
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



    public void clearAllStatuses() {
        activeStatuses.clear();
    }

}


