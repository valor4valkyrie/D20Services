package application.skills.model;

import application.character.model.CharacterModel;

import javax.persistence.*;

@Entity
@Table(name = "player_character_skills")
public class SkillsModel {
    @Id
    @Column(name = "PLAYER_SKILLS_ID")
    private int id;

    @Column(name = "CHARACTER_SKILL_NAME")
    private String characterSkillName;

    @Column(name = "CHARACTER_SKILL_RANKS")
    private String characterSkillRanks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_CHARACTER_ID")
    private CharacterModel characterModel;
}
