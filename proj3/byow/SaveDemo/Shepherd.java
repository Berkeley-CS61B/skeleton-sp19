package byow.SaveDemo;

import java.io.Serializable;

public class Shepherd implements Serializable {
    private int sheep;
    private String[] herd = new String[]{"Dolly", "Baa Baa Black", "Shaun", "Lay", "Doug"};

    public Shepherd() {
        sheep = 0;
    }

    public void baa() {
        sheep = (sheep + 1) % herd.length;
    }

    public String getSheep() {
        return herd[sheep];
    }
}
