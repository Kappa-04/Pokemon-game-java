package it.unibs.pajc;

import java.util.Random;

import it.unibs.pajc.Move.Category;

public abstract class Utils {
	private static Random extractor = new Random();
	
	public static int extractInt(int min, int max)
	{
		 int range = max + 1 - min;
		 int casual = extractor.nextInt(range);
		 return casual + min;
	}
	
	public static double extractDouble(double min, double max)
	{
	 double range = max - min;
	 double casual = extractor.nextDouble();
	 
	 double posExtracted = range*casual;
	 
	 return posExtracted + min;
	}
	
	public static void applyStatusEffects(Pokemon pokemon) {
	    switch (pokemon.getStatus()) {
	        case BURN:
	            pokemon.setCurrentHp(pokemon.getCurrentHp() - 10); // Example fixed damage
	            System.out.println(pokemon.getName() + " is hurt by its burn!");
	            break;
	        case POISON:
	            pokemon.setCurrentHp(pokemon.getCurrentHp() - pokemon.getBasehp() / 8); // Fractional damage
	            System.out.println(pokemon.getName() + " is hurt by poison!");
	            break;
	        case SLEEP:
	            if (pokemon.getStatusDuration() > 0) {
	                System.out.println(pokemon.getName() + " is fast asleep.");
	                pokemon.reduceStatusDuration();
	            }
	            break;
	        case FROZEN:
	            if (extractInt(0, 100) < 20) { 
	                System.out.println(pokemon.getName() + " thawed out!");
	                pokemon.setStatus(Status.NONE);
	            } else {
	                System.out.println(pokemon.getName() + " is frozen solid.");
	            }
	            break;
	        default:
	            break;
	    }
	}
	
	

	
	

	
	public static int damage (Pokemon attacker, Move move, Pokemon defender) {
		
		double damage = 1;
		int crit = extractInt(0, 100);
		double critDmg;
		double rng = extractDouble(0.85, 1);
		double STAB;
		double effectiveness = 1;
		double burn = 1;
		if(crit<move.getCritRate()) {
			critDmg = 1.5;
		}else critDmg = 1;
		
		if(attacker.getType1() == move.getType() || attacker.getType2() == move.getType()) STAB = 1.5;
		else STAB = 1;
		if(attacker.getStatus() == Status.BURN) burn = 0.5;
		if(defender.getType1()
				.getWeaknessesList()
				.containsKey(move.getType().toString())) 
				effectiveness = effectiveness * 2;
		if(defender.getType2()
				.getWeaknessesList()
				.containsKey(move.getType().toString())) 
				effectiveness = effectiveness * 2;
		
		if(move.getCategoria() == Category.Status) return 0;
		
		if (attacker.getStatus() == Status.PARALYSIS) {
		    if (extractInt(0, 100) < 25) { 
		        System.out.println(attacker.getName() + " is paralyzed and cannot move!");
		        return 0;
		    }
		}
		
		if(move.getCategoria() == Category.Physical) {
		damage = 		damage + (((((2* attacker.getLvl()/5)+2)
						*move.getBasePower()*attacker.getCurrentAtk()/
						defender.getCurrentDef()/50)+2)
						*STAB*critDmg*effectiveness*burn*rng);
		}
		
		if(move.getCategoria() == Category.Special) {
			damage = 		damage + (((((2* attacker.getLvl()/5)+2)
							*move.getBasePower()*attacker.getCurrentSpAtk()/
							defender.getCurrentSpDef()/50)+2)
							*STAB*critDmg*effectiveness*rng);
			}
		
		return (int)damage;
	}

	public static Pokemon getRandomAvailablePokemon(Trainer target, Pokemon currentPokemon, Pokemon opponent) {
		Pokemon selected;
	    do {
	        int extracted = extractInt(0, target.getPokemonList().length - 1); 
	        selected = target.getPokemonList()[extracted];
	    } while (selected == currentPokemon || selected.getCurrentHp() <= 0); 
	    return selected;
	}
	}
	
	
	
	
	

