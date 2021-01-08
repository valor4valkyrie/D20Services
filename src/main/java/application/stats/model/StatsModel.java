package application.stats.model;

import application.player.model.PlayerModel;

import javax.persistence.*;

@Entity
@Table(name = "player_character_stats")
public class StatsModel {
    @Id
    @Column(name = "STATS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int statsID;

    @Column(name = "STR")
    private int strength;

    @Column(name = "DEX")
    private int dexterity;

    @Column(name = "CON")
    private int constitution;

    @Column(name = "INTEL")
    private int intelligence;

    @Column(name = "WIS")
    private int wisdom;

    @Column(name = "CHA")
    private int charisma;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_CHARACTER_ID")
    private PlayerModel playerModel;

    public StatsModel() {
    }

    public int getStatsId() {
        return statsID;
    }

    public void setId(int statsID) {
        this.statsID = statsID;
    }

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

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
}