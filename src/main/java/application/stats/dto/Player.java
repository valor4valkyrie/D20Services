package application.stats.dto;

import java.io.Serializable;

public class Player implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String playerFirstName;

    public Player(){}

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }
}
