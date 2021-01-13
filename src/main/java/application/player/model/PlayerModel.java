package application.player.model;

import application.player.dto.Player;

import javax.persistence.*;

@Entity
@Table(name = "player_info")
public class PlayerModel {

    @Id
    @Column(name = "PLAYER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playerID;

    @Column(name = "PLAYER_FIRST_NAME")
    private String playerFirstName;

    @Column(name = "PLAYER_MIDDLE_NAME")
    private String playerMiddleName;

    @Column(name = "PLAYER_LAST_NAME")
    private String playerLastName;

    @Column(name = "PLAYER_EMAIL")
    private String playerEmail;

    @Column(name = "PLAYER_USERNAME")
    private String playerUserName;

    @Column(name = "PLAYER_PASSWORD")
    private String playerPassword;

    public PlayerModel() {
    }

    public PlayerModel(Player player) {
        this.playerFirstName = player.getPlayerFirstName();
        this.playerMiddleName = player.getPlayerMiddleName();
        this.playerLastName = player.getPlayerLastName();
        this.playerEmail = player.getPlayerEmail();
        this.playerUserName = player.getPlayerUserName();
        this.playerPassword = player.getPlayerPassword();
    }

    public Integer getId() {
        return playerID;
    }

    public void setId(int id) {
        this.playerID = playerID;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerMiddleName() {
        return playerMiddleName;
    }

    public void setPlayerMiddleName(String playerMiddleName) {
        this.playerMiddleName = playerMiddleName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String email) {
        this.playerEmail = playerEmail;
    }

    public String getUserName() {
        return playerUserName;
    }

    public void setUserName(String userName) {
        this.playerUserName = playerUserName;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }
}
