package com.charles.pokemoncombat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.charles.pokemoncombat.databinding.FragmentNewPokemonBinding;
import com.charles.pokemoncombat.models.PokemonModelView;
import com.charles.pokemoncombat.models.Pokemons;

import java.util.Objects;


public class NewPokemon extends Fragment {
    private FragmentNewPokemonBinding binding;
    private Pokemons addPokemon;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentNewPokemonBinding.inflate(inflater, container, false))
                .getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        final PokemonModelView pokemonModelView = new ViewModelProvider(this).get(PokemonModelView.class);

        binding.addPokemonButton.setOnClickListener(v -> {
            boolean error = false;

            if(Objects.requireNonNull(binding.playerName.getText()).toString().isEmpty()){
                error = true;
                binding.playerName.setError("El nombre es obligatorio");
            }
            try{
                Integer.parseInt(Objects.requireNonNull(binding.healthPoints.getText()).toString());
            }catch (Exception e){
                error = true;
                binding.healthPoints.setError("El nivel es obligatorio");
            }
            try{
                Integer.parseInt(Objects.requireNonNull(binding.atackNormal.getText()).toString());
            }catch (Exception e){
                error = true;
                binding.atackNormal.setError("El nivel es obligatorio");
            }
            try{
                Integer.parseInt(Objects.requireNonNull(binding.normalDefense.getText()).toString());
            }catch (Exception e){
                error = true;
                binding.normalDefense.setError("El nivel es obligatorio");
            }
            try{
                Integer.parseInt(Objects.requireNonNull(binding.specialAtack.getText()).toString());
            }catch (Exception e){
                error = true;
                binding.specialAtack.setError("El nivel es obligatorio");
            }
            try{
                Integer.parseInt(Objects.requireNonNull(binding.specialShield.getText()).toString());
            }catch (Exception e){
                error = true;
                binding.specialShield.setError("El nivel es obligatorio");
            }

            if(!error){
                pokemonModelView.addPokemon(binding.playerName.getText().toString(),
                        Integer.parseInt(binding.healthPoints.getText().toString()),
                        Integer.parseInt(binding.atackNormal.getText().toString()),
                        Integer.parseInt(binding.normalDefense.getText().toString()),
                        Integer.parseInt(binding.specialAtack.getText().toString()),
                        Integer.parseInt(binding.specialShield.getText().toString()));
                clean();
            }
        });

        pokemonModelView.getOnNameError().observe(getViewLifecycleOwner(), s -> {
            if(s != null){
                binding.playerName.setError(s);
            }else {
                binding.playerName.setError(null);
            }
        });

        pokemonModelView.getOnHealthError().observe(getViewLifecycleOwner(), integer -> {
            if(integer != null){
                binding.healthPoints.setError("La vida debe estar entre 0 y 999");
            }else {
                binding.healthPoints.setError(null);
            }
        });

        pokemonModelView.getOnAttackError().observe(getViewLifecycleOwner(), integer -> {
            if(integer != null){
                binding.atackNormal.setError("El ataque debe estar entre 0 y 999");
            }else {
                binding.atackNormal.setError(null);
            }
        });

        pokemonModelView.getOnDefenseError().observe(getViewLifecycleOwner(), integer -> {
            if(integer != null){
                binding.normalDefense.setError("La defensa debe estar entre 0 y 999");
            }else {
                binding.normalDefense.setError(null);
            }
        });

        pokemonModelView.getOnSpecialAttackError().observe(getViewLifecycleOwner(), integer -> {
            if(integer != null){
                binding.specialAtack.setError("El ataque especial debe estar entre 0 y 999");
            }else {
                binding.specialAtack.setError(null);
            }
        });

        pokemonModelView.getOnSpecialDefenseError().observe(getViewLifecycleOwner(), integer -> {
            if(integer != null){
                binding.specialShield.setError("La defensa especial debe estar entre 0 y 999");
            }else {
                binding.specialShield.setError(null);
            }
        });

        PokemonModelView.pokemon1.observe(getViewLifecycleOwner(), pokemon -> Toast.makeText(getContext(), "Pokemon 1 añadido", Toast.LENGTH_SHORT).show());

        PokemonModelView.pokemon2.observe(getViewLifecycleOwner(), pokemon -> Toast.makeText(getContext(), "Pokemon 2 añadido", Toast.LENGTH_SHORT).show());

        pokemonModelView.getOnPokemonCreation().observe(getViewLifecycleOwner(), aBoolean -> {
            if(aBoolean){
                binding.calculateProgressBar.setVisibility(View.VISIBLE);
                binding.addPokemonButton.setVisibility(View.GONE);
            }else {
                binding.calculateProgressBar.setVisibility(View.GONE);
                binding.addPokemonButton.setVisibility(View.VISIBLE);
            }
        });

    }

    private void clean() {
        binding.playerName.setText("");
        binding.healthPoints.setText("");
        binding.atackNormal.setText("");
        binding.normalDefense.setText("");
        binding.specialAtack.setText("");
        binding.specialShield.setText("");
    }
}