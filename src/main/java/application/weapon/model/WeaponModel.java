package application.weapon.model;

import application.character.model.CharacterModel;
import application.magic.model.MagicPropertyModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PLAYER_CHARACTER_WEAPONS")
public class WeaponModel {
    @Id
    @Column(name = "WEAPON_ID")
    private int weaponID;

    @Column(name = "CHARACTER_WEAPON_NAME")
    private String characterWeaponName;

    @Column(name = "CHARACTER_WEAPON_ATTACK_BONUS")
    private int characterWeaponAttackBonus;

    @Column(name = "CHARACTER_WEAPON_CRITICAL")
    private int characterWeaponCritical;

    @Column(name = "CHARACTER_WEAPON_RANGE")
    private int characterWeaponRange;

    @Column(name = "CHARACTER_WEAPON_AMMUNITION")
    private int characterWeaponAmmunition;

    @Column(name = "CHARACTER_WEAPON_WEIGHT")
    private int characterWeaponWeight;

    @Column(name = "CHARACTER_WEAPON_WEIGHT_UNIT")
    private String characterWeaponWeightUnit;

    @Column(name = "CHARACTER_WEAPON_TYPE")
    private String characterWeaponType;

    @OneToMany(fetch = FetchType.LAZY)
    private List<MagicPropertyModel> magicModelList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_CHARACTER_ID")
    private CharacterModel characterModel;

    public String getCharacterWeaponName() {
        return characterWeaponName;
    }

    public void setCharacterWeaponName(String characterWeaponName) {
        this.characterWeaponName = characterWeaponName;
    }

    public int getCharacterWeaponAttackBonus() {
        return characterWeaponAttackBonus;
    }

    public void setCharacterWeaponAttackBonus(int characterWeaponAttackBonus) {
        this.characterWeaponAttackBonus = characterWeaponAttackBonus;
    }

    public int getCharacterWeaponCritical() {
        return characterWeaponCritical;
    }

    public void setCharacterWeaponCritical(int characterWeaponCritical) {
        this.characterWeaponCritical = characterWeaponCritical;
    }

    public int getCharacterWeaponRange() {
        return characterWeaponRange;
    }

    public void setCharacterWeaponRange(int characterWeaponRange) {
        this.characterWeaponRange = characterWeaponRange;
    }

    public int getCharacterWeaponAmmunition() {
        return characterWeaponAmmunition;
    }

    public void setCharacterWeaponAmmunition(int characterWeaponAmmunition) {
        this.characterWeaponAmmunition = characterWeaponAmmunition;
    }

    public int getCharacterWeaponWeight() {
        return characterWeaponWeight;
    }

    public void setCharacterWeaponWeight(int characterWeaponWeight) {
        this.characterWeaponWeight = characterWeaponWeight;
    }

    public String getCharacterWeaponWeightUnit() {
        return characterWeaponWeightUnit;
    }

    public void setCharacterWeaponWeightUnit(String characterWeaponWeightUnit) {
        this.characterWeaponWeightUnit = characterWeaponWeightUnit;
    }

    public String getCharacterWeaponType() {
        return characterWeaponType;
    }

    public void setCharacterWeaponType(String characterWeaponType) {
        this.characterWeaponType = characterWeaponType;
    }

    public List<MagicPropertyModel> getMagicModelList() {
        return magicModelList;
    }

    public void setMagicModelList(List<MagicPropertyModel> magicModelList) {
        this.magicModelList = magicModelList;
    }

    public CharacterModel getCharacterModel() {
        return characterModel;
    }

    public void setCharacterModel(CharacterModel characterModel) {
        this.characterModel = characterModel;
    }
}
