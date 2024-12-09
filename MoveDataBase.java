package it.unibs.pajc;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveDataBase {
	
	private static HashMap<String, Move> moves = new HashMap<String, Move>();
	
	static {
		 	moves.put("Pound", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 40, 100, new ArrayList<>()));
	        moves.put("Karate Chop", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 50, 100, new ArrayList<>()));
	        moves.put("Double Slap", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 15, 85, new ArrayList<>() {{
	            add(new SecondaryEffect("MULTI_HIT", 2, 5));
	        }}));
	        moves.put("Comet Punch", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 18, 85, new ArrayList<>()));
	        moves.put("Mega Punch", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 80, 85, new ArrayList<>()));
	        moves.put("Pay Day", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 40, 100, new ArrayList<>()));
	        moves.put("Fire Punch", new Move(Move.Category.Physical, new Type("Fire", null, null, null), 75, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("BURN", 10));
	        }}));
	        moves.put("Ice Punch", new Move(Move.Category.Physical, new Type("Ice", null, null, null), 75, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("FREEZE", 10));
	        }}));
	        moves.put("Thunder Punch", new Move(Move.Category.Physical, new Type("Electric", null, null, null), 75, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("PARALYZE", 10));
	        }}));
	        moves.put("Scratch", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 40, 100, new ArrayList<>()));
	        moves.put("Vice Grip", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 55, 100, new ArrayList<>()));
	        moves.put("Guillotine", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 0, 30, new ArrayList<>() {{
	            add(new SecondaryEffect("OHKO", 100));
	        }}));
	        moves.put("Razor Wind", new Move(Move.Category.Special, new Type("Normal", null, null, null), 80, 100, new ArrayList<>(){ {
	        	add(new SecondaryEffect("TWO_TURN", true));
			}}));
	        moves.put("Swords Dance", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 100, new ArrayList<>() { {
	        	add(new SecondaryEffect("STAT_CHANGE", "ATK", 2, 100));
			}}));
	        moves.put("Cut", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 50, 95, new ArrayList<>()));
	        moves.put("Gust", new Move(Move.Category.Special, new Type("Flying", null, null, null), 40, 100, new ArrayList<>()));
	        moves.put("Wing Attack", new Move(Move.Category.Physical, new Type("Flying", null, null, null), 60, 100, new ArrayList<>()));
	        moves.put("Whirlwind", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("FORCED_SWITCH", 100));
	        }}));
	        moves.put("Fly", new Move(Move.Category.Physical, new Type("Flying", null, null, null), 90, 95, new ArrayList<>(){ {
	        	add(new SecondaryEffect("TWO_TURN", true));
			}}));
	        moves.put("Bind", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 15, 85, new ArrayList<>()));
	        moves.put("Slam", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 80, 75, new ArrayList<>()));
	        moves.put("Vine Whip", new Move(Move.Category.Physical, new Type("Grass", null, null, null), 45, 100, new ArrayList<>()));
	        moves.put("Stomp", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 65, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("FLINCH", 30));
	        }}));
	        moves.put("Double Kick", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 30, 100, new ArrayList<>(){{
	            add(new SecondaryEffect("MULTI_HIT", 2, 2));
	        }}
	        		));
	        moves.put("Mega Kick", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 120, 75, new ArrayList<>()));
	        moves.put("Jump Kick", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 100, 95, new ArrayList<>()));
	        moves.put("Rolling Kick", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 60, 85, new ArrayList<>() {{
	            add(new SecondaryEffect("FLINCH", 30));
	        }}));
	        moves.put("Sand Attack", new Move(Move.Category.Status, new Type("Ground", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("LOWER_ACCURACY", 100));
	        }}));
	        moves.put("Headbutt", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 70, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("FLINCH", 30));
	        }}));
	        moves.put("Horn Attack", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 65, 100, new ArrayList<>()));
	        moves.put("Fury Attack", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 15, 85, new ArrayList<>(){{
	            add(new SecondaryEffect("MULTI_HIT", 2, 5));
	        }}));
	        moves.put("Horn Drill", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 0, 30, new ArrayList<>() {{
	            add(new SecondaryEffect("OHKO", 100));
	        }}));
	        moves.put("Tackle", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 40, 100, new ArrayList<>()));
	        moves.put("Body Slam", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 85, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("PARALYZE", 30));
	        }}));
	        moves.put("Wrap", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 15, 90, new ArrayList<>()));
	        moves.put("Take Down", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 90, 85, new ArrayList<>(){{
	            add(new SecondaryEffect("RECOIL", 0.25)); // 25% recoil
	        }}));
	        moves.put("Thrash", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 120, 100, new ArrayList<>()));
	        moves.put("Double-Edge", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 120, 100, new ArrayList<>(){{
	            add(new SecondaryEffect("RECOIL", 0.33)); // 25% recoil
	        }}));
	        moves.put("Growl", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("STAT_CHANGE", "ATK", -1, 100));
	        }}));
	        moves.put("Roar", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("FORCED_SWITCH", 100));
	        }}));
	        moves.put("Sing", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 55, new ArrayList<>() {{
	            add(new SecondaryEffect("SLEEP", 100));
	        }}));
	        moves.put("Supersonic", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 55, new ArrayList<>() {{
	            add(new SecondaryEffect("CONFUSION", 100));
	        }}));
	        moves.put("Sonic Boom", new Move(Move.Category.Special, new Type("Normal", null, null, null), 0, 90, new ArrayList<>() {{
	            add(new SecondaryEffect("FIXED_DAMAGE", 20)); // Always deals 20 damage
	        }}));
	        moves.put("Disable", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 55, new ArrayList<>()));
	        moves.put("Acid", new Move(Move.Category.Special, new Type("Poison", null, null, null), 40, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("LOWER_DEF", -1, 10));
	        }}));
	        moves.put("Ember", new Move(Move.Category.Special, new Type("Fire", null, null, null), 40, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("BURN", 10));
	        }}));
	        moves.put("Flamethrower", new Move(Move.Category.Special, new Type("Fire", null, null, null), 90, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("BURN", 10));
	        }}));
	        moves.put("Mist", new Move(Move.Category.Status, new Type("Ice", null, null, null), 0, 100, new ArrayList<>()));
	        moves.put("Water Gun", new Move(Move.Category.Special, new Type("Water", null, null, null), 40, 100, new ArrayList<>()));
	        moves.put("Hydro Pump", new Move(Move.Category.Special, new Type("Water", null, null, null), 110, 80, new ArrayList<>()));
	        moves.put("Surf", new Move(Move.Category.Special, new Type("Water", null, null, null), 90, 100, new ArrayList<>()));
	        moves.put("Ice Beam", new Move(Move.Category.Special, new Type("Ice", null, null, null), 90, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("FREEZE", 10));
	        }}));
	        moves.put("Blizzard", new Move(Move.Category.Special, new Type("Ice", null, null, null), 110, 70, new ArrayList<>() {{
	            add(new SecondaryEffect("FREEZE", 10));
	        }}));
	        moves.put("Psybeam", new Move(Move.Category.Special, new Type("Psychic", null, null, null), 65, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("CONFUSION", 10));
	        }}));
	        moves.put("BubbleBeam", new Move(Move.Category.Special, new Type("Water", null, null, null), 65, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("LOWER_SPEED", -1, 10));
	        }}));
	        moves.put("Aurora Beam", new Move(Move.Category.Special, new Type("Ice", null, null, null), 65, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("LOWER_ATK", -1, 10));
	        }}));
	        moves.put("Hyper Beam", new Move(Move.Category.Special, new Type("Normal", null, null, null), 150, 90, new ArrayList<>()));
	        moves.put("Peck", new Move(Move.Category.Physical, new Type("Flying", null, null, null), 35, 100, new ArrayList<>()));
	        moves.put("Drill Peck", new Move(Move.Category.Physical, new Type("Flying", null, null, null), 80, 100, new ArrayList<>()));
	        moves.put("Submission", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 80, 80, new ArrayList<>() {{
	            add(new SecondaryEffect("RECOIL", 0.25)); // 25% recoil
	        }}));
	        moves.put("Low Kick", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("WEIGHT_DEPENDENT", 100));
	        }}));
	        moves.put("Counter", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("COUNTER_DAMAGE", 100));
	        }}));
	        moves.put("Seismic Toss", new Move(Move.Category.Physical, new Type("Fighting", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("FIXED_DAMAGE", 50)); // Deals damage equal to user's level
	        }}));
	        moves.put("Strength", new Move(Move.Category.Physical, new Type("Normal", null, null, null), 80, 100, new ArrayList<>()));
	        moves.put("Absorb", new Move(Move.Category.Special, new Type("Grass", null, null, null), 20, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("DRAIN_HP", 50, 0)); // Heals 50% of damage dealt
	        }}));
	        moves.put("Mega Drain", new Move(Move.Category.Special, new Type("Grass", null, null, null), 40, 100, new ArrayList<>() {{
	        	add(new SecondaryEffect("DRAIN_HP", 50, 0)); 
	        }}));
	        moves.put("Leech Seed", new Move(Move.Category.Status, new Type("Grass", null, null, null), 0, 90, new ArrayList<>() {{
	        	add(new SecondaryEffect("DRAIN_HP", 1/8, -1)); 
	        }}));
	        moves.put("Growth", new Move(Move.Category.Status, new Type("Normal", null, null, null), 0, 100, new ArrayList<>() {{
	            add(new SecondaryEffect("STAT_CHANGE", "SPATK", 1, 100));
	        }}));
	        moves.put("Razor Leaf", new Move(Move.Category.Physical, new Type("Grass", null, null, null), 55, 95, new ArrayList<>() {{
	            add(new SecondaryEffect("HIGH_CRIT", 100));
	        }}));
	        moves.put("SolarBeam", new Move(Move.Category.Special, new Type("Grass", null, null, null), 120, 100, new ArrayList<>(){ {
	        	add(new SecondaryEffect("TWO_TURN", true));
			}}));
	        moves.put("PoisonPowder", new Move(Move.Category.Status, new Type("Poison", null, null, null), 0, 75, new ArrayList<>() {{
	            add(new SecondaryEffect("POISON", 100));
	        }}));
	        moves.put("Stun Spore", new Move(Move.Category.Status, new Type("Grass", null, null, null), 0, 75, new ArrayList<>() {{
	            add(new SecondaryEffect("PARALYZE", 100));
	        }}));
            moves.put("Sleep Powder", new Move(Move.Category.Status, new Type("Grass", null, null, null), 0, 75, new ArrayList<>() {{
	            add(new SecondaryEffect("SLEEP", 100));
	        }}));	
	}
	
	public static Move getMove(String name) {
        return moves.get(name);
    }

    public static boolean hasMove(String name) {
        return moves.containsKey(name);
    }

    public static HashMap<String, Move> getAllMoves() {
        return moves;
    }
}
