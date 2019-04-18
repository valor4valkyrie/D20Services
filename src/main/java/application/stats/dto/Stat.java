package application.stats.dto;

import application.Route;

import java.io.Serializable;

public class Stat implements Serializable {

    private static final long serialVersionUID = 1l;

    private int value;
    private int tempValue;

    public Stat(int value){ this(value, 0);}

    public Stat(int value, int tempValue) {
        Route r = new Route();
        r.run();
        this.value = value;
        this.tempValue = tempValue;
    }
}
