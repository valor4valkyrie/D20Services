package application.character.model;

import application.player.model.PlayerModel;

import javax.persistence.*;

@Entity
@Table(name = "PLAYER_CHARACTER")
public class CharacterModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CHARACTER_FIRST_NAME")
    private String characterFirstName;

    @Column(name = "CHARACTER_MIDDLE_NAME")
    private String characterMiddleName;

    @Column(name = "CHARACTER_NICK_NAME")
    private String characterNickName;

    @Column(name = "CHARACTER_RACE")
    private String characterRace;

    @Column(name = "CHARACTER_HEIGHT")
    private String characterHeight;

    @Column(name = "CHARACTER_HEIGHT_UNIT")
    private String characterHeightUnit;

    @Column(name = "CHARACTER_WEIGHT")
    private int characterWeight;

    @Column(name = "CHARACTER_WEIGHT_UNIT")
    private String characterWeightUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    private PlayerModel playerModel;

    public CharacterModel() {

    }

    public String getCharacterFirstName() {
        return characterFirstName;
    }

    public void setCharacterFirstName(String characterFirstName) {
        this.characterFirstName = characterFirstName;
    }

    public String getCharacterMiddleName() {
        return characterMiddleName;
    }

    public void setCharacterMiddleName(String characterMiddleName) {
        this.characterMiddleName = characterMiddleName;
    }

    public String getCharacterNickName() {
        return characterNickName;
    }

    public void setCharacterNickName(String characterNickName) {
        this.characterNickName = characterNickName;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
    }

    public String getCharacterHeight() {
        return characterHeight;
    }

    public void setCharacterHeight(String characterHeight) {
        this.characterHeight = characterHeight;
    }

    public String getCharacterHeightUnit() {
        return characterHeightUnit;
    }

    public void setCharacterHeightUnit(String characterHeightUnit) {
        this.characterHeightUnit = characterHeightUnit;
    }

    public int getCharacterWeight() {
        return characterWeight;
    }

    public void setCharacterWeight(int characterWeight) {
        this.characterWeight = characterWeight;
    }

    public String getCharacterWeightUnit() {
        return characterWeightUnit;
    }

    public void setCharacterWeightUnit(String characterWeightUnit) {
        this.characterWeightUnit = characterWeightUnit;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
}
