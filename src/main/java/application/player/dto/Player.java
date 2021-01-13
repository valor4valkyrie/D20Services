package application.player.dto;

import application.player.model.PlayerModel;

import java.io.Serializable;

public class Player implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String playerFirstName;
    private String playerMiddleName;
    private String playerLastName;
    private String playerEmail;
    private String playerUserName;
    private String playerPassword;

    public Player() {
    }

    public Player(PlayerModel playerModel) {
        playerFirstName = playerModel.getPlayerFirstName();
        playerMiddleName = playerModel.getPlayerMiddleName();
        playerLastName = playerModel.getPlayerLastName();
        playerEmail = playerModel.getPlayerEmail();
        playerUserName = playerModel.getUserName();
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

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    public String getPlayerUserName() {
        return playerUserName;
    }

    public void setPlayerUserName(String playerUserName) {
        this.playerUserName = playerUserName;
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public void setPlayerPassword(String playerPassword) {
        this.playerPassword = playerPassword;
    }
}
