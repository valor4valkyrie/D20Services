package application.magic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "magic_properties")
public class MagicPropertyModel {

    @Id
    @Column(name = "MAGIC_PROPERTIES_ID")
    private int magicPropertiesID;

    @Column(name = "MAGIC_PROPERTY_NAME")
    private String magicPropertyName;

    @Column(name = "MAGIC_PROPERTY_AURA")
    private String magicPropertyAura;

    @Column(name = "MAGIC_PROPERTY_STRENGTH")
    private String magicPropertyStrength;

    @Column(name = "MAGIC_PROPERTY_DESCRIPTION")
    private String magicPropertyDescription;

    @Column(name = "CHARACTER_SKILL_NAME")
    private String characterSkillName;

    @Column(name = "CHARACTER_SKILL_RANKS")
    private String characterSkillRanks;

    @Column(name = "CHARACTER_STAT_NAME")
    private String characterStatName;

    @Column(name = "CHARACTER_STAT_BONUS")
    private int characterStatBonus;

    @Column(name = "CHARACTER_ARMOR_BONUS")
    private int characterArmorBonus;

    @Column(name = "CHARACTER_ATTACK_BONUS")
    private int characterAttackBonus;

    public MagicPropertyModel() {

    }

    public String getMagicPropertyName() {
        return magicPropertyName;
    }

    public void setMagicPropertyName(String magicPropertyName) {
        this.magicPropertyName = magicPropertyName;
    }

    public String getMagicPropertyAura() {
        return magicPropertyAura;
    }

    public void setMagicPropertyAura(String magicPropertyAura) {
        this.magicPropertyAura = magicPropertyAura;
    }

    public String getMagicPropertyStrength() {
        return magicPropertyStrength;
    }

    public void setMagicPropertyStrength(String magicPropertyStrength) {
        this.magicPropertyStrength = magicPropertyStrength;
    }

    public String getMagicPropertyDescription() {
        return magicPropertyDescription;
    }

    public void setMagicPropertyDescription(String magicPropertyDescription) {
        this.magicPropertyDescription = magicPropertyDescription;
    }

    public String getCharacterSkillName() {
        return characterSkillName;
    }

    public void setCharacterSkillName(String characterSkillName) {
        this.characterSkillName = characterSkillName;
    }

    public String getCharacterSkillRanks() {
        return characterSkillRanks;
    }

    public void setCharacterSkillRanks(String characterSkillRanks) {
        this.characterSkillRanks = characterSkillRanks;
    }

    public String getCharacterStatName() {
        return characterStatName;
    }

    public void setCharacterStatName(String characterStatName) {
        this.characterStatName = characterStatName;
    }

    public int getCharacterStatBonus() {
        return characterStatBonus;
    }

    public void setCharacterStatBonus(int characterStatBonus) {
        this.characterStatBonus = characterStatBonus;
    }

    public int getCharacterArmorBonus() {
        return characterArmorBonus;
    }

    public void setCharacterArmorBonus(int characterArmorBonus) {
        this.characterArmorBonus = characterArmorBonus;
    }

    public int getCharacterAttackBonus() {
        return characterAttackBonus;
    }

    public void setCharacterAttackBonus(int characterAttackBonus) {
        this.characterAttackBonus = characterAttackBonus;
    }
}
