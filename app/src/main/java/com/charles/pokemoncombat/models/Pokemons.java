package com.charles.pokemoncombat.models;

public class Pokemons {

    private String nombre;
    private int hp;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;

    public Pokemons(String nombre, int hp, int ataque, int defensa, int ataqueEspecial, int defensaEspecial) {
        this.nombre = nombre;
        setHp(hp);
        setAtaque(ataque);
        setDefensa(defensa);
        setAtaqueEspecial(ataqueEspecial);
        setDefensaEspecial(defensaEspecial);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (validateStatus(hp)) {
            this.hp = hp;
        }else {
            this.hp = 0;
        }

    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if (validateStatus(ataque)) {
            this.ataque = ataque;
        }else {
            this.ataque = 0;
        }
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if (validateStatus(defensa)) {
            this.defensa = defensa;
        }else {
            this.defensa = 0;
        }
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        if (validateStatus(ataqueEspecial)) {
            this.ataqueEspecial = ataqueEspecial;
        }else {
            this.ataqueEspecial = 0;
        }
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        if (validateStatus(defensaEspecial)) {
            this.defensaEspecial = defensaEspecial;
        }else {
            this.defensaEspecial = 0;
        }
    }

    public boolean validateStatus(int status) {
        return status >= 0 && status <= 999;
    }
}
