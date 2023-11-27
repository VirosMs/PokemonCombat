package com.charles.pokemoncombat.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonModelView extends AndroidViewModel {
    public static MutableLiveData<Pokemons> pokemon1 = new MutableLiveData<>();
    public static MutableLiveData<Pokemons> pokemon2 = new MutableLiveData<>();

    int putPokemon = 0;
    MutableLiveData<String> onNameError = new MutableLiveData<>();
    MutableLiveData<Integer> onHealthError = new MutableLiveData<>();
    MutableLiveData<Integer> onAttackError = new MutableLiveData<>();
    MutableLiveData<Integer> onDefenseError = new MutableLiveData<>();
    MutableLiveData<Integer> onSpecialAttackError = new MutableLiveData<>();
    MutableLiveData<Integer> onSpecialDefenseError = new MutableLiveData<>();
    MutableLiveData<Boolean> onPokemonCreation = new MutableLiveData<>();
    MutableLiveData<Boolean> pokemonAttack = new MutableLiveData<>();
    MutableLiveData<Boolean> combatFinished = new MutableLiveData<>();

    PokemonModel pokemonModel;

    Executor executor;

    public PokemonModelView(@NonNull Application application) {
        super(application);

        executor = Executors.newSingleThreadExecutor();
        pokemonModel = new PokemonModel();
    }

    public void addPokemon(String name, int hp, int attack, int defense, int specialAttack, int specialDefense){
        final Pokemons pokemons = new Pokemons(name, hp, attack, defense, specialAttack, specialDefense);

        executor.execute(() -> pokemonModel.addPokemon(pokemons, new PokemonModel.PokemonCreationCallback() {
            @Override
            public void onPokemonCreationStart() {
                onPokemonCreation.postValue(true);
            }

            @Override
            public void onPokemonCreating(Pokemons pokemon) {
                onNameError.postValue(null);
                onHealthError.postValue(null);
                onAttackError.postValue(null);
                onDefenseError.postValue(null);
                onSpecialAttackError.postValue(null);
                onSpecialDefenseError.postValue(null);

                if(putPokemon == 0){
                    pokemon1.postValue(pokemon);
                    putPokemon++;
                } else if(putPokemon == 1){
                    pokemon2.postValue(pokemon);
                    putPokemon = 0;
                }
            }

            @Override
            public void onNameError(String error) {
                onNameError.postValue(error);
            }

            @Override
            public void onHealthError(int minHealth, int maxHealth) {
                onHealthError.postValue(minHealth);
                onHealthError.postValue(maxHealth);
            }

            @Override
            public void onAttackError(int minAttack, int maxAttack) {
                onAttackError.postValue(minAttack);
                onAttackError.postValue(maxAttack);
            }

            @Override
            public void onDefenseError(int minDefense, int maxDefense) {
                onDefenseError.postValue(minDefense);
                onDefenseError.postValue(maxDefense);
            }

            @Override
            public void onSpecialAttackError(int minSpecialAttack, int maxSpecialAttack) {
                onSpecialAttackError.postValue(minSpecialAttack);
                onSpecialAttackError.postValue(maxSpecialAttack);
            }

            @Override
            public void onSpecialDefenseError(int minSpecialDefense, int maxSpecialDefense) {
                onSpecialDefenseError.postValue(minSpecialDefense);
                onSpecialDefenseError.postValue(maxSpecialDefense);
            }

            @Override
            public void onPokemonCreationEnd() {
                onPokemonCreation.postValue(false);
            }
        }));
    }

    public void combatPokemon(){
       executor.execute(() -> {
           boolean pokemonAttacker = true;
           pokemonAttack.postValue(true);

           while(pokemon1.getValue().getHealth() > 0 && pokemon2.getValue().getHealth() > 0){
               if(pokemonAttacker){
                   pokemon2.getValue().setHealth(pokemon2.getValue().getHealth() - pokemon1.getValue().getAttack());
                   pokemonAttacker = false;
               } else {
                   pokemon1.getValue().setHealth(pokemon1.getValue().getHealth() - pokemon2.getValue().getAttack());
                   pokemonAttacker = true;
               }
               try{
                   Thread.sleep(1000);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }

           combatFinished.postValue(true);
       });
    }
}
