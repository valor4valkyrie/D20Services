package application.stats.dto;

import application.stats.model.StatsModel;

import java.io.Serializable;

public class Stats implements Serializable {

    private static final long serialVersionUID = 1l;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public static StatsModel toModel(Stats stats) {
        StatsModel model = new StatsModel();

        model.setStrength(stats.getStrength());
        model.setDexterity(stats.getDexterity());
        model.setConstitution(stats.getConstitution());
        model.setIntelligence(stats.getIntelligence());
        model.setWisdom(stats.getWisdom());
        model.setCharisma(stats.getCharisma());

        return model;
    }

    public Stats(){}

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
}