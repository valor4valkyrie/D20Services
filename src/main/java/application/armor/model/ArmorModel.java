package application.armor.model;

import application.character.model.CharacterModel;
import application.magic.model.MagicPropertyModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(name = "Armor.magicProperties",
        attributeNodes = @NamedAttributeNode("magicModelList")
)
@Entity
@Table(name = "PLAYER_CHARACTER_ARMOR")
public class ArmorModel {

    @Id
    @Column(name = "ARMOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "CHARACTER_ARMOR_BONUS")
    private int characterArmorBonus;

    @Column(name = "CHARACTER_ARMOR_WEIGHT")
    private int characterArmorWeight;

    @Column(name = "CHARACTER_ARMOR_WEIGHT_UNIT")
    private String characterArmorWeightUnit;

    @Column(name = "CHARACTER_ARMOR_TYPE")
    private String characterArmorType;

    @Column(name = "CHARACTER_ARMOR_MAX_DEX_BONUS")
    private int characterArmorMaxDexBonus;

    @Column(name = "CHARACTER_ARMOR_SPELL_FAILURE")
    private int characterArmorSpellFailure;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGIC_PROPERTIES_ID")
    private List<MagicPropertyModel> magicModelList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_CHARACTER_ID")
    private CharacterModel characterModel;

    public ArmorModel() {

    }

    public int getCharacterArmorBonus() {
        return characterArmorBonus;
    }

    public void setCharacterArmorBonus(int characterArmorBonus) {
        this.characterArmorBonus = characterArmorBonus;
    }

    public int getCharacterArmorWeight() {
        return characterArmorWeight;
    }

    public void setCharacterArmorWeight(int characterArmorWeight) {
        this.characterArmorWeight = characterArmorWeight;
    }

    public String getCharacterArmorWeightUnit() {
        return characterArmorWeightUnit;
    }

    public void setCharacterArmorWeightUnit(String characterArmorWeightUnit) {
        this.characterArmorWeightUnit = characterArmorWeightUnit;
    }

    public String getCharacterArmorType() {
        return characterArmorType;
    }

    public void setCharacterArmorType(String characterArmorType) {
        this.characterArmorType = characterArmorType;
    }

    public int getCharacterArmorMaxDexBonus() {
        return characterArmorMaxDexBonus;
    }

    public void setCharacterArmorMaxDexBonus(int characterArmorMaxDexBonus) {
        this.characterArmorMaxDexBonus = characterArmorMaxDexBonus;
    }

    public int getCharacterArmorSpellFailure() {
        return characterArmorSpellFailure;
    }

    public void setCharacterArmorSpellFailure(int characterArmorSpellFailure) {
        this.characterArmorSpellFailure = characterArmorSpellFailure;
    }

    public CharacterModel getCharacterModel() {
        return characterModel;
    }

    public void setCharacterModel(CharacterModel characterModel) {
        this.characterModel = characterModel;
    }

    public List<MagicPropertyModel> getMagicModelList() {
        return magicModelList;
    }

    public void setMagicModelList(List<MagicPropertyModel> magicModelList) {
        this.magicModelList = magicModelList;
    }
}
