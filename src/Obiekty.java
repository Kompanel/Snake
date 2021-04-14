import java.util.Random;

import static java.lang.StrictMath.abs;

public abstract class Obiekty {

    protected int x;
    protected int y;

    Random random = new Random();

    public Obiekty() {

        //losowanie w konstruktorze miejsca na planszy
        this.x = abs(((random.nextInt() % main.SZEROKOSC - 10) / 10) * 10 + 10);
        this.y = abs(((random.nextInt() % main.WYSOKOSC - 10) / 10) * 10 + 10);
    }

    public void losowanie() {
        //ponowne losowanie miejsca na mapie
        this.x = abs(((random.nextInt() % main.SZEROKOSC - 10) / 10) * 10 + 10);
        this.y = abs(((random.nextInt() % main.WYSOKOSC - 10) / 10) * 10 + 10);
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }
}
