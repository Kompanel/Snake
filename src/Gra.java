import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Plansza extends JPanel {

    public Waz waz = new Waz();
    public Jablko jablko = new Jablko();
    public Kamien kamien = new Kamien();
    public Color kolor_planszy = new Color(0x194506);
    public Color kolor_glowa = new Color(0, 22, 1);
    public Color kolor_cialo = new Color(203, 237, 207);
    public int wynik = 0, koniec = 0;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //wypelnienie planszy kolorem
        g.setColor(kolor_planszy);
        g.fillRect(0, 0, 700, 700);

        //dodanie kamienia oraz jablka na plansze
        g.setColor(Color.RED);
        g.fillRect(jablko.getx(), jablko.gety(), main.rozmiarElementu, main.rozmiarElementu);
        g.setColor(Color.GRAY);
        g.fillRect(kamien.getx(), kamien.gety(), main.rozmiarElementu, main.rozmiarElementu);

        //dodanie weza na plansze
        g.setColor(kolor_glowa);
        g.fillRect(waz.getGlowa().x, waz.getGlowa().y, main.rozmiarElementu, main.rozmiarElementu);
        g.setColor(kolor_cialo);
        for (Point punkt : waz.getCialoweza()) {
            g.fillRect(punkt.x, punkt.y, main.rozmiarElementu, main.rozmiarElementu);
        }

        //dodanie wyniku na plansze
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Wynik: " + wynik, 35, 35);

        //gdy zakonczy sie gra wypisanie informacji na ekranie
        if (koniec == 1) {
            g.setFont(new Font("arial", Font.PLAIN, 40));
            g.drawString("KONIEC GRY", main.SZEROKOSC / 2 - 140, main.WYSOKOSC / 2 - 20);
        }
    }
}

public class Gra extends JFrame implements ActionListener, KeyListener {

    public final int GORA = 0, DOL = 1, LEWO = 2, PRAWO = 3;
    public int trudnosc, tykanie, kierunek = GORA;
    Plansza plansza;
    Timer czasomierz = new Timer(20, this);

    public Gra(int szerokosc, int wysokosc) {
        //ustawienia okna gry
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("snake2.jpg"));
        this.setTitle("Snake");
        this.addKeyListener(this);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - 350, dim.height / 2 - 350);
        setSize(szerokosc + 16, wysokosc + 39);
        setVisible(true);

        //dodanie planszy oraz start czasomierza
        plansza = new Plansza();
        add(plansza);
        czasomierz.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //wprowadzenie odswierzania planszy
        plansza.repaint();
        tykanie++;

        if (tykanie % trudnosc == 0) {
            //gdy waz uderzy w samego siebie
            for (Point pkt : plansza.waz.getCialoweza()) {
                if (plansza.waz.getGlowa().equals(pkt)) {
                    plansza.koniec = 1;
                    czasomierz.stop();
                }
            }

            //gdy waz najedzie na kamien
            if (plansza.waz.getGlowa().x == plansza.kamien.x && plansza.waz.getGlowa().y == plansza.kamien.y) {
                try {
                    plansza.waz.zmniejszRozmiar();
                } catch (NieMaCiala nieMaCiala) {
                    plansza.koniec = 1;
                    czasomierz.stop();
                }
                plansza.kamien.losowanie();
                if (trudnosc == 2) {
                    plansza.wynik -= 10;
                } else {
                    if (trudnosc == 5) {
                        plansza.wynik -= 5;
                    } else {
                        plansza.wynik--;
                    }
                }

            }

            //gdy waz najedzie na jablko
            if (plansza.waz.getGlowa().x == plansza.jablko.x && plansza.waz.getGlowa().y == plansza.jablko.y) {
                plansza.jablko.losowanie();
                //z dzialania naszego programu aby dodac element do weza nalezy wiedziec w jakim kierunku on sie porusza
                if (kierunek == 0) {
                    plansza.waz.zwiekszRozmiar(0, -10);
                }
                if (kierunek == 1) {
                    plansza.waz.zwiekszRozmiar(0, 10);
                }
                if (kierunek == 2) {
                    plansza.waz.zwiekszRozmiar(-10, 0);
                }
                if (kierunek == 3) {
                    plansza.waz.zwiekszRozmiar(10, 0);
                }

                if (trudnosc == 2) {
                    plansza.wynik += 10;
                } else {
                    if (trudnosc == 5) {
                        plansza.wynik += 5;
                    } else {
                        plansza.wynik++;
                    }
                }


            }

            //poruszanie weza
            if (kierunek == 0) {
                plansza.waz.zmianaKierunku(0, -10);
            }
            if (kierunek == 1) {
                plansza.waz.zmianaKierunku(0, 10);
            }
            if (kierunek == 2) {
                plansza.waz.zmianaKierunku(-10, 0);
            }
            if (kierunek == 3) {
                plansza.waz.zmianaKierunku(10, 0);
            }

            //przeniesienie weza na druga stronejezeli wyjdzie poza mape
            if (plansza.waz.getGlowa().x >= main.SZEROKOSC) plansza.waz.setGlowa(0, plansza.waz.getGlowa().y);
            if (plansza.waz.getGlowa().x < 0) plansza.waz.setGlowa(main.SZEROKOSC, plansza.waz.getGlowa().y);
            if (plansza.waz.getGlowa().y >= main.WYSOKOSC) plansza.waz.setGlowa(plansza.waz.getGlowa().x, 0);
            if (plansza.waz.getGlowa().y < 0) plansza.waz.setGlowa(plansza.waz.getGlowa().x, main.WYSOKOSC);

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //wprowadzenie klawiszy do dzialania programu
        if (e.getKeyCode() == KeyEvent.VK_UP && kierunek != DOL) {
            kierunek = GORA;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && kierunek != GORA) {
            kierunek = DOL;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && kierunek != PRAWO) {
            kierunek = LEWO;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && kierunek != LEWO) {
            kierunek = PRAWO;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
