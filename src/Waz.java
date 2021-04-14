import java.awt.*;
import java.util.LinkedList;

public class Waz {
    private Point glowa;
    private LinkedList<Point> cialoweza = new LinkedList<>();

    public Waz() {
        // ustawienie weza na srodku planszy oraz dodanie 2 bazowych cial do weza
        glowa = new Point(main.SZEROKOSC / 20 * 10, main.WYSOKOSC / 20 * 10);
        cialoweza.add(new Point(glowa.x, glowa.y + 10));
        cialoweza.add(new Point(glowa.x, glowa.y + 20));
    }

    //metoda skracajaca rozmiar weza
    public void zmniejszRozmiar() throws NieMaCiala {
        if (cialoweza.size() == 0) throw new NieMaCiala("wąż nie posiada ciała");
        cialoweza.removeLast();
    }

    //metoda zwiekszajaca rozmiar weza
    public void zwiekszRozmiar(int x, int y) {
        cialoweza.addFirst(new Point(glowa));
        glowa.x += x;
        glowa.y += y;
    }

    //metoda zwracajaca glowe weza
    public Point getGlowa() {
        return glowa;
    }


    //metoda do zmiany kierunku weza
    public void zmianaKierunku(int x, int y) {
        //porusza sie poprzes przesuniecie glowy do przodu oraz dodaniu nowego elemetu ciala z przodu oraz usunieciu z tylu
        cialoweza.addFirst(new Point(glowa));
        cialoweza.removeLast();
        glowa.x += x;
        glowa.y += y;
    }

    //metoda ustawiajaca glowe w innym polozeniu
    public void setGlowa(int x, int y) {
        this.glowa.x = x;
        this.glowa.y = y;
    }

    //metoda zwracajaca liste punktów ciała węża
    public LinkedList<Point> getCialoweza() {
        return cialoweza;
    }
}
