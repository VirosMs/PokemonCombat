package com.charles.pokemoncombat.models;


import androidx.annotation.NonNull;

public class Pokemons {

    private String name;
    private int health;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;

    public Pokemons(String name, int Health, int attack, int defense, int specialAttack, int specialDefense) {
        this.name = name;
        this.health = Health;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }
    public String getHealthString() {
        return "HP: " + health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public String getAttackString() {
        return "ATK: " + attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getDefenseString() {
        return "DEF: " + defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public String getSpecialAttackString() {
        return "SATK: " + specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public String getSpecialDefenseString() {
        return "SDEF: " + specialDefense;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pokemons{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                '}';
    }
}
