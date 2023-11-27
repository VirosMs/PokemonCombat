package com.charles.pokemoncombat.models;

public class PokemonModel {

    interface PokemonCreationCallback {
        void onPokemonCreated(Pokemons pokemon);
        void onNameError(String error);
        void onHealthError(int minHealth, int maxHealth);
        void onAttackError(int minAttack, int maxAttack);
        void onDefenseError(int minDefense, int maxDefense);
        void onSpecialAttackError(int minSpecialAttack, int maxSpecialAttack);
        void onSpecialDefenseError(int minSpecialDefense, int maxSpecialDefense);
        void onPokemonCreationStart();
        void onPokemonCreationEnd();
    }

    public void addPokemon(Pokemons pokemons, PokemonCreationCallback callback) {
        boolean error = false;

        callback.onPokemonCreationStart();

        int minHealth = 0;
        int maxHealth = 999;

        int minAttack = 0;
        int maxAttack = 999;

        int minDefense = 0;
        int maxDefense = 999;

        int minSpecialAttack = 0;
        int maxSpecialAttack = 999;

        int minSpecialDefense = 0;
        int maxSpecialDefense = 999;

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(pokemons.getName().isEmpty() || pokemons.getName() == null) {
            callback.onNameError("Name cannot be empty");
            error = true;
        } else if (!pokemons.getName().matches("[a-zA-Z]*")) {
            callback.onNameError("Name must be a string");
            error = true;
        }
    }
}
