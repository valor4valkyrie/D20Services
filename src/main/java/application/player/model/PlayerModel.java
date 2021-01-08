package application.player.model;

import javax.persistence.*;

@Entity
@Table(name = "player_info")
public class PlayerModel {

    @Id
    @Column(name = "PLAYER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer playerID;

    @Column(name = "PLAYER_FIRST_NAME")
    private String playerFirstName;

    @Column(name = "PLAYER_LAST_NAME")
    private String playerLastName;

    @Column(name = "PLAYER_EMAIL")
    private String email;

    @Column(name = "PLAYER_USERNAME")
    private String userName;

    @Column(name = "PLAYER_PASSWORD")
    private String playerPassword;

    public PlayerModel() {
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

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }
}
