package application.stats.dto;

import application.stats.model.StatsModel;

import java.io.Serializable;

public class Stats implements Serializable {

    private static final long serialVersionUID = 1l;

    private Stat strength;
    private Stat dexterity;
    private Stat constitution;
    private Stat intelligence;
    private Stat wisdom;
    private Stat charisma;

    public Stats() {
    }

    public static StatsModel toModel(Integer playerID, Stats stats) {
        StatsModel model = new StatsModel();

        model.setId(playerID);
        model.setStrength(stats.getStrength().getStat());
        model.setDexterity(stats.getDexterity().getStat());
        model.setConstitution(stats.getConstitution().getStat());
        model.setIntelligence(stats.getIntelligence().getStat());
        model.setWisdom(stats.getWisdom().getStat());
        model.setCharisma(stats.getCharisma().getStat());

        return model;
    }

    public Stat getStrength() {
        return strength;
    }

    public void setStrength(Stat strength) {
        this.strength = strength;
    }

    public Stat getDexterity() {
        return dexterity;
    }

    public void setDexterity(Stat dexterity) {
        this.dexterity = dexterity;
    }

    public Stat getConstitution() {
        return constitution;
    }

    public void setConstitution(Stat constitution) {
        this.constitution = constitution;
    }

    public Stat getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Stat intelligence) {
        this.intelligence = intelligence;
    }

    public Stat getWisdom() {
        return wisdom;
    }

    public void setWisdom(Stat wisdom) {
        this.wisdom = wisdom;
    }

    public Stat getCharisma() {
        return charisma;
    }

    public void setCharisma(Stat charisma) {
        this.charisma = charisma;
    }
}