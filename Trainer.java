package it.unibs.pajc;

import java.util.ArrayList;

public class Trainer {
	private String name;
	private Pokemon [] pokemonList = new Pokemon[6];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Pokemon[] getPokemonList() {
		return pokemonList;
	}
	public void setPokemonList(Pokemon[] pokemonList) {
		this.pokemonList = pokemonList;
	}
	public Trainer(String name, Pokemon[] pokemonList) {
		super();
		this.name = name;
		this.pokemonList = pokemonList;
	}
	
	
}


