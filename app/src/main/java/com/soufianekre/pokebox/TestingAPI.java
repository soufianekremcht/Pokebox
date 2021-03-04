package com.soufianekre.pokebox;

import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.ContestType;
import me.sargunvohra.lib.pokekotlin.model.EvolutionChain;
import me.sargunvohra.lib.pokekotlin.model.Location;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonStat;
import me.sargunvohra.lib.pokekotlin.model.PokemonType;

public class TestingAPI {



    public static void  main(String[] args) {
        int POKEMON_ID = 1;
        PokeApiClient pokeApi = new PokeApiClient();

        // Pokemon Info

        Pokemon simplePokemon = pokeApi.getPokemon(POKEMON_ID);
        List<PokemonStat> pokemonStats = simplePokemon.getStats();
        List<PokemonType> types  = simplePokemon.getTypes();

        System.out.println("Pokemone Name : " + simplePokemon.getName() + "-> Base Exp : "+simplePokemon.getBaseExperience());

        // Pokemon Icon
        System.out.println("Pokemon Icons :" +simplePokemon.getSprites().getFrontShiny());

        System.out.println("Pokemon Type : ");
        for (PokemonType type : types){
            System.out.println(type.getType().getName());
        }
        System.out.println("Pokemon Stats :");
        for ( PokemonStat stat : pokemonStats){
            System.out.println("Stat " + stat.getStat() + " -> Effort " + stat.getBaseStat());
        }
        //Contests Info
        ContestType contestName = pokeApi.getContestType(1);

        System.out.println(contestName.getNames());
        System.out.println(contestName.getBerryFlavor().getName());



        // Locations Info
        int i = 1;
        while (i <14){
            Location pokeLocation = pokeApi.getLocation(i);
            print("Location " + i);
            System.out.println(pokeLocation.getNames());
            i++;

        }

        System.out.println("Pokemon Evolution : ");
        EvolutionChain evolutionChain = pokeApi.getEvolutionChain(POKEMON_ID);
        print("Evolution Chain : " + evolutionChain.getChain().getEvolutionDetails());




        // Evolutions Info

        //System.out.println(bulbasaur.getStats().toString());
    }


    public static void print(Object o){
        System.out.println(o);
    }

}
