package com.charles.pokemoncombat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.charles.pokemoncombat.databinding.FragmentBattlePokemonBinding;
import com.charles.pokemoncombat.models.PokemonModelView;


public class BattlePokemon extends Fragment {

    private FragmentBattlePokemonBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentBattlePokemonBinding.inflate(inflater, container, false))
                .getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final PokemonModelView pokemonModelView = new ViewModelProvider(this)
                .get(PokemonModelView.class);

        if (PokemonModelView.pokemon1.getValue() != null && PokemonModelView.pokemon2.getValue() != null) {


            PokemonModelView.pokemon1.observe(getViewLifecycleOwner(), pokemons -> {
                binding.nombrePokemonTextView1.setText(pokemons.getName());
                binding.hpTextView1.setText(pokemons.getHealthString());
                binding.atackTextView1.setText(pokemons.getAttackString());
                binding.defenseTextView1.setText(pokemons.getDefenseString());
                binding.specialAtackTextView1.setText(pokemons.getSpecialAttackString());
                binding.specialDefenseTextView1.setText(pokemons.getSpecialDefenseString());

            });
            PokemonModelView.pokemon2.observe(getViewLifecycleOwner(), pokemons -> {
                binding.nombrePokemonTextView2.setText(pokemons.getName());
                binding.hpTextView2.setText(pokemons.getHealthString());
                binding.atackTextView2.setText(pokemons.getAttackString());
                binding.defenseTextView2.setText(pokemons.getDefenseString());
                binding.specialAtackTextView2.setText(pokemons.getSpecialAttackString());
                binding.specialDefenseTextView2.setText(pokemons.getSpecialDefenseString());
            });


            binding.pokebolaImageView.setOnClickListener(v -> pokemonModelView.combatPokemon());

            pokemonModelView.getPokemonAttack().observe(getViewLifecycleOwner(), pokemonAttack -> {
                binding.pokebolaImageView.setImageResource(R.drawable.fight_svgrepo_com);

                binding.lineaIzquierda.setVisibility(View.GONE);
                binding.lineaDerecha.setVisibility(View.GONE);
                if (pokemonAttack) {
                    binding.nombrePokemonTextView1.setTextColor(getResources().getColor(R.color.soft_blue, this.requireActivity().getTheme()));
                    binding.nombrePokemonTextView2.setTextColor(getResources().getColor(R.color.red, this.requireActivity().getTheme()));
                    binding.hpTextView2.setTextColor(getResources().getColor(R.color.red, this.requireActivity().getTheme()));
                    binding.hpTextView1.setTextColor(getResources().getColor(R.color.soft_blue, this.requireActivity().getTheme()));
                } else {
                    binding.nombrePokemonTextView1.setTextColor(getResources().getColor(R.color.red, this.requireActivity().getTheme()));
                    binding.nombrePokemonTextView2.setTextColor(getResources().getColor(R.color.soft_blue, this.requireActivity().getTheme()));
                    binding.hpTextView1.setTextColor(getResources().getColor(R.color.red, this.requireActivity().getTheme()));
                    binding.hpTextView2.setTextColor(getResources().getColor(R.color.soft_blue, this.requireActivity().getTheme()));
                }
            });

            pokemonModelView.getCombatFinished().observe(getViewLifecycleOwner(), combatFinished -> {
                if (combatFinished) {
                    Toast.makeText(requireContext(), "Combate finalizado", Toast.LENGTH_SHORT).show();
                    binding.pokebolaImageView.setImageResource(R.drawable.pokeball_pokemon_svgrepo_com);
                    binding.lineaIzquierda.setVisibility(View.VISIBLE);
                    binding.lineaDerecha.setVisibility(View.VISIBLE);
                }
            });


        } else {
            binding.nombrePokemonTextView1.setVisibility(View.GONE);
            binding.nombrePokemonTextView2.setVisibility(View.GONE);
            binding.battleStartButton.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "No hay pokemons", Toast.LENGTH_SHORT).show();
        }


    }
}