package it.unibs.pajc;

public class Battle {
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private boolean isPlayerTurn;

    public Battle(Pokemon playerPokemon, Pokemon opponentPokemon) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        this.isPlayerTurn = true; 
    }

    public Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }

    public boolean isBattleOver() {
        return playerPokemon.getCurrentHp() <= 0 || opponentPokemon.getCurrentHp() <= 0;
    }
   
    public void executeMove(Pokemon attacker, Move move, Pokemon defender) {
        if (move.getCategoria() == Move.Category.Status) {
            applyMoveEffects(attacker, defender, move);
        } else {
            int damage = Utils.damage(attacker, move, defender);
            defender.setCurrentHp(Math.max(0, defender.getCurrentHp() - damage));
            System.out.println(attacker.getName() + " used " + move.getName() + "!");
            System.out.println(defender.getName() + " took " + damage + " damage.");
        }

        if (defender.getCurrentHp() <= 0) {
            System.out.println(defender.getName() + " fainted!");
        }
    }
    
    public void takeTurn() {
        
        Utils.applyStatusEffects(playerPokemon);
        Utils.applyStatusEffects(opponentPokemon);
        for(Status status : playerPokemon.getStatusManager().getActiveStatuses()) {
        	if(status.equals(Status.DOT)) status.applyDotEffect(playerPokemon);
        }
        
        for(Status status : opponentPokemon.getStatusManager().getActiveStatuses()) {
        	if(status.equals(Status.DOT)) status.applyDotEffect(opponentPokemon);
        }


        if (isBattleOver()) {
            System.out.println("Battle is over!");
            return;
        }

        if (isPlayerTurn) {
            // Player selects a move (to be connected to UI later)
            Move selectedMove = playerSelectMove();
            executeMove(playerPokemon, selectedMove, opponentPokemon);
        } else {
        	Move selectedMove = null;
            for(Move m : opponentPokemon.getMoveList()) {
            	if(playerPokemon.getType1().getWeaknessesList().containsValue(m.getType()) && 
            			m.getCategoria() != Move.Category.Status) m = selectedMove;
            }
            if(selectedMove == null)
            	selectedMove = opponentPokemon.getMoveList()
                                               .get(Utils.extractInt(0, opponentPokemon.getMoveList().size() - 1));
            executeMove(opponentPokemon, selectedMove, playerPokemon);
        }

        
        isPlayerTurn = !isPlayerTurn;
    }

    public void skipTurn(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " flinched and couldn't move!");
    }

    public void applyStatChange(Pokemon target, String stat, int change) {
        switch (stat.toUpperCase()) {
            case "ATK":
                target.setAtkStage(target.getAtkStage() + change);
                break;
            case "DEF":
                target.setDefStage(target.getDefStage() + change);
                break;
            case "SPATK":
            	target.setspAtkStage(target.getspAtkStage() + change);
                break;
            case "SPDEF":
            	target.setspDefStage(target.getspDefStage() + change);
                break;
            case "SPE":
                target.setSpeStage(target.getspeStage() + change);
                break;
        }
        System.out.println(target.getName() + "'s " + stat + " changed by " + change + "!");
    }
    
    public void handleOneHitKO(Pokemon attacker, Pokemon defender, Move move) {
	    int chance = Utils.extractInt(1, 100); // Simulate hit chance
	    if (chance <= move.getAccuracy()) {
	        defender.setCurrentHp(0);
	        System.out.println(defender.getName() + " was instantly knocked out by " + move.getName() + "!");
	    } else {
	        System.out.println(move.getName() + " missed!");
	    }
	}
    
    public void switchPokemon(Pokemon newPokemon, boolean isPlayer) {
        if (isPlayer) {
        	for(Status status : playerPokemon.getStatusManager().getActiveStatuses()) {
        		if (status.isPersistentEffect()) {
        	    status.clearDotEffect();;
        	    System.out.println(playerPokemon.getName() + "'s Leech Seed wore off!");
        		}
        	}
            playerPokemon = newPokemon;
            System.out.println("Player switched to " + newPokemon.getName() + "!");
        } else {
        	for(Status status : opponentPokemon.getStatusManager().getActiveStatuses()) {
        		if (status.isPersistentEffect()) {
        	    status.clearDotEffect();;
        	    System.out.println(opponentPokemon.getName() + "'s Leech Seed wore off!");
        		}
        	}
            opponentPokemon = newPokemon;
            System.out.println("Opponent switched to " + newPokemon.getName() + "!");
        }
    }

    public void forceSwitch(Trainer target, Pokemon currentPokemon, Pokemon opponent) {
        Pokemon newPokemon = Utils.getRandomAvailablePokemon(target, currentPokemon, opponent); // Implement this in Utils
        opponentPokemon = newPokemon;
        System.out.println("Opponent was forced to switch to " + newPokemon.getName());
    }
    
    

    
    public void applyMoveEffects(Pokemon attacker, Pokemon defender, Move move) {
        int totalDamageDealt = 0;

        for (SecondaryEffect effect : move.getSecondaryEffects()) {
            if (effect.getEffectType().equals("COUNTER")) {
                int counterDamage = effect.getLastDamageDealt() * 2; // Counter deals double damage
                attacker.setCurrentHp(attacker.getCurrentHp() - counterDamage);
                System.out.println(attacker.getName() + " was hit by Counter and took " + counterDamage + " damage!");
            } else if (effect.getEffectType().equals("OHKO")) {
                handleOneHitKO(attacker, defender, move);
            } else if (effect.getEffectType().equals("MULTI_HIT")) {
                int hits = Utils.extractInt(effect.getMinHits(), effect.getMaxHits());
                System.out.println(move.getName() + " hit " + hits + " times!");
                for (int i = 0; i < hits; i++) {
                    int damage = Utils.damage(attacker, move, defender);
                    defender.setCurrentHp(defender.getCurrentHp() - damage);
                    totalDamageDealt += damage;
                }
            } else if (effect.getEffectType().equals("RECOIL")) {
                // Apply recoil after damage is dealt
                if (totalDamageDealt > 0) {
                    int recoilDamage = (int) (totalDamageDealt * effect.getRecoilMultiplier());
                    attacker.setCurrentHp(attacker.getCurrentHp() - recoilDamage);
                    System.out.println(attacker.getName() + " took " + recoilDamage + " recoil damage!");
                }
            } else {
                int chance = Utils.extractInt(1, 100);
                if (chance <= effect.getChance()) {
                    switch (effect.getEffectType()) {
                        case "PARALYZE":
                            defender.getStatusManager().addStatus(Status.PARALYSIS);
                            System.out.println(defender.getName() + " is paralyzed!");
                            break;
                        case "BURN":
                            defender.getStatusManager().addStatus(Status.BURN);
                            System.out.println(defender.getName() + " is burned!");
                            break;
                        case "FREEZE":
                            defender.getStatusManager().addStatus(Status.FROZEN);
                            System.out.println(defender.getName() + " is frozen solid!");
                            break;
                        case "SLEEP":
                            defender.getStatusManager().addStatus(Status.SLEEP);
                            System.out.println(defender.getName() + " fell asleep!");
                            break;
                        case "POISON":
                            defender.getStatusManager().addStatus(Status.POISON);
                            System.out.println(defender.getName() + " is now poisoned!");
                            break;
                        case "CONFUSION":
                            defender.getStatusManager().addStatus(Status.CONFUSION);
                            System.out.println(defender.getName() + " is now confused!");
                            break;
                        case "STAT CHANGE":
                            Pokemon target = effect.getStatChange() > 0 ? attacker : defender;
                            applyStatChange(target, effect.getStat(), effect.getStatChange());
                            break;
                        case "FLINCH":
                            skipTurn(defender);
                            break;
                        case "DRAIN_HP":
                        	totalDamageDealt = Utils.damage(attacker, move, defender);
                        	attacker.setCurrentHp((int)(attacker.getCurrentHp() + totalDamageDealt*effect.getDrainPercentage()));
                        	System.out.println(attacker.getName() + "drained " + totalDamageDealt*effect.getDrainPercentage() + "HP!");
                        	break;
                        case "DOT":
                        	defender.getStatusManager().addStatus(Status.DOT);
                        	for(Status status : defender.getStatusManager().getActiveStatuses()) {
                        		if(status.isDotEffect()) {
                        			status.setDotEffect(move.getName(), status.getDotDamagePercentage(),
                        			status.getDotDuration(), status.isPersistentEffect());
                        		}
                        	}
                        
                    }
                }
            }
        }

        // Default case for regular damage
        if (move.getBasePower() > 0) {
            int damage = Utils.damage(attacker, move, defender);
            defender.setCurrentHp(Math.max(0, defender.getCurrentHp() - damage));
            totalDamageDealt = damage;
            System.out.println(defender.getName() + " took " + damage + " damage.");

            // Update lastDamageDealt for Counter
         // Iterate through the defender's moves
            for (Move m : defender.getMoveList()) {
                // Iterate through the secondary effects of each move
                for (SecondaryEffect effect : move.getSecondaryEffects()) {
                    if (effect.getEffectType().equals("COUNTER")) {
                        effect.setLastDamageDealt(damage); // Update the last damage dealt for Counter
                    }
                }
            }

        }
    }


    
    public void startBattle() {
        while (!isBattleOver()) {
            takeTurn();
        }

        if (playerPokemon.getCurrentHp() <= 0) {
            System.out.println("You lost the battle...");
        } else if (opponentPokemon.getCurrentHp() <= 0) {
            System.out.println("You won the battle!");
        }
    }



}
