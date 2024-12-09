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
        Utils.applyStatusEffects(playerPokemon);
        playerPokemon.getStatus().applyDotEffect(playerPokemon);

        Utils.applyStatusEffects(opponentPokemon);
        opponentPokemon.getStatus().applyDotEffect(opponentPokemon);


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

    private void skipTurn(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " flinched and couldn't move!");
    }

    private void applyStatChange(Pokemon target, String stat, int change) {
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
    
    private void handleOneHitKO(Pokemon attacker, Pokemon defender, Move move) {
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
        	if (playerPokemon.getStatus().isPersistentEffect()) {
        	    playerPokemon.getStatus().clearDotEffect();
        	    System.out.println(playerPokemon.getName() + "'s Leech Seed wore off!");
        	}
            playerPokemon = newPokemon;
            System.out.println("Player switched to " + newPokemon.getName() + "!");
        } else {
        	if (opponentPokemon.getStatus().isPersistentEffect()) {
        	    opponentPokemon.getStatus().clearDotEffect();
        	    System.out.println(opponentPokemon.getName() + "'s Leech Seed wore off!");
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
        	if (effect.getEffectType().equals("DRAIN_HP") || effect.getEffectType().equals("DOT")) {
        	    int damage = Utils.damage(attacker, move, defender);
        	    defender.setCurrentHp(Math.max(0, defender.getCurrentHp() - damage)); // Apply immediate damage
        	    int drainedHp = (int) (damage * effect.getDrainPercentage());
        	    if (effect.getDuration() == -1 || defender.getStatus().isPersistentEffect()) { 
        	        defender.getStatus().setDotEffect(move.getName(), effect.getDrainPercentage(), -1, true);
        	        System.out.println(defender.getName() + " is seeded by " + move.getName() + "!");
        	    }


        	    if (effect.getDuration() == 0) { // Instant drain (e.g., Giga Drain)
        	        attacker.setCurrentHp(Math.min(attacker.getMaxHp(), attacker.getCurrentHp() + drainedHp));
        	        System.out.println(attacker.getName() + " drained " + drainedHp + " HP from " + defender.getName() + "!");
        	    } else { // DoT effect (e.g., Leech Seed)
        	        defender.getStatus().setDotEffect(move.getName(), effect.getDrainPercentage(), effect.getDuration(), false);
        	        System.out.println(defender.getName() + " is affected by " + move.getName() + " for " + effect.getDuration() + " turns!");
        	    }
        	}



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
                            defender.setStatus(Status.PARALYSIS);
                            System.out.println(defender.getName() + " is paralyzed!");
                            break;
                        case "BURN":
                            defender.setStatus(Status.BURN);
                            System.out.println(defender.getName() + " is burned!");
                            break;
                        case "FREEZE":
                            defender.setStatus(Status.FROZEN);
                            System.out.println(defender.getName() + " is frozen solid!");
                            break;
                        case "SLEEP":
                            defender.setStatus(Status.SLEEP);
                            System.out.println(defender.getName() + " fell asleep!");
                            break;
                        case "POISON":
                            defender.setStatus(Status.POISON);
                            System.out.println(defender.getName() + " is now poisoned!");
                            break;
                        case "CONFUSION":
                            defender.setStatus(Status.CONFUSION);
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
                        	break;
                        
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
