package com.charles.pokemoncombat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charles.pokemoncombat.databinding.FragmentHomeBinding;
import com.charles.pokemoncombat.models.PokemonModelView;

public class Home extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentHomeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        final PokemonModelView pokemonModelView = new ViewModelProvider(this)
                .get(PokemonModelView.class);

        binding.newPokemonBotton.setOnClickListener(v -> {
            navController.navigate(R.id.newPokemon);
        });

        binding.battleStart.setOnClickListener(v -> {
            navController.navigate(R.id.battlePokemon);
        });
    }
}