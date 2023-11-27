package com.charles.pokemoncombat.models;

public class PokemonModel {

    interface PokemonCreationCallback {
        void onPokemonCreating(Pokemons pokemon);
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

        if(pokemons.getHealth() < minHealth || pokemons.getHealth() > maxHealth) {
            callback.onHealthError(minHealth, maxHealth);
            error = true;
        }

        if(pokemons.getAttack() < minAttack || pokemons.getAttack() > maxAttack) {
            callback.onAttackError(minAttack, maxAttack);
            error = true;
        }

        if(pokemons.getDefense() < minDefense || pokemons.getDefense() > maxDefense) {
            callback.onDefenseError(minDefense, maxDefense);
            error = true;
        }

        if(pokemons.getSpecialAttack() < minSpecialAttack || pokemons.getSpecialAttack() > maxSpecialAttack) {
            callback.onSpecialAttackError(minSpecialAttack, maxSpecialAttack);
            error = true;
        }

        if(pokemons.getSpecialDefense() < minSpecialDefense || pokemons.getSpecialDefense() > maxSpecialDefense) {
            callback.onSpecialDefenseError(minSpecialDefense, maxSpecialDefense);
            error = true;
        }

        if(!error) {
            callback.onPokemonCreating(pokemons);
        }

        callback.onPokemonCreationEnd();
    }

    public Pokemons combatPokemon(Pokemons attacker, Pokemons defender) {
        int probabilitySpecialAttack = (int) (Math.random() * 10 + 1);
        int healthAfterAttack = defender.getHealth();

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.getMessage();
        }

        if(probabilitySpecialAttack <= 4) {
            if(attacker.getSpecialAttack() > defender.getSpecialDefense()) {
                healthAfterAttack = defender.getHealth() - (attacker.getSpecialAttack() - defender.getSpecialDefense());
            }
        } else {
            if(attacker.getAttack() > defender.getDefense()) {
                healthAfterAttack = defender.getHealth() - (attacker.getAttack() - defender.getDefense());
            }
        }

        if (healthAfterAttack < 0) {
            healthAfterAttack = 0;
        }

        defender.setHealth(healthAfterAttack);

        return defender;
    }
}
