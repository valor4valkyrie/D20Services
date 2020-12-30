package application.player.dto;

import application.player.model.PlayerModel;

import java.io.Serializable;

public class Player implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String playerFirstName;
    private String playerLastName;
    private String playerEmail;
    private String playerUserName;
    private String playerPassword;

    public Player() {
    }

    public Player(PlayerModel playerModel) {
        playerFirstName = playerModel.getPlayerFirstName();
        playerLastName = playerModel.getPlayerLastName();
        playerEmail = playerModel.getEmail();
        playerUserName = playerModel.getUserName();
    }

    public static PlayerModel toModel(Player player) {
        PlayerModel model = new PlayerModel();

        model.setPlayerFirstName(player.getPlayerFirstName());
        model.setPlayerLastName(player.getPlayerLastName());
        model.setEmail(player.getPlayerEmail());
        model.setUserName(player.getPlayerUserName());
        model.setPlayerPassword(player.getPlayerPassword());

        return model;
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
