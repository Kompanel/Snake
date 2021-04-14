import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//klasa wyswietlajaca tlo menu
class ObrazekMenu extends JComponent {
    private Image tlo;

    public ObrazekMenu(Image tlo) {
        this.tlo = tlo;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tlo, 50, 20, this);
    }
}


public class Menu extends JFrame implements ActionListener {

    //dodanie przycisków oraz nagłówka
    JButton poziomTrudnosciSredni = new JButton("Średni");
    JButton poziomTrudnosciLatwy = new JButton("Łatwy");
    JButton poziomTrudnosciTrudny = new JButton("Trudny");
    JLabel naglowekWyboruPoziomuTrudnsci = new JLabel("Wybierz poziom trudności");


    public Menu() throws IOException {
        //tlo w menu
        BufferedImage obrazWeza = ImageIO.read(new File("waz.PNG"));
        setContentPane(new ObrazekMenu(obrazWeza));

        //ustawienia oraz ulozenie okna menu
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Menu");
        setSize(800, 600);
        setLocation(dim.width / 2 - 400, dim.height / 2 - 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        //wprowadzenie ustawien poziomu gry
        naglowekWyboruPoziomuTrudnsci.setFont(new Font("arial", Font.PLAIN, 24));
        naglowekWyboruPoziomuTrudnsci.setBounds(260, 200, 300, 50);
        poziomTrudnosciSredni.setBounds(340, 300, 100, 50);
        poziomTrudnosciLatwy.setBounds(140, 300, 100, 50);
        poziomTrudnosciTrudny.setBounds(540, 300, 100, 50);
        //dodanie naglowka i przyciskow
        add(naglowekWyboruPoziomuTrudnsci);
        add(poziomTrudnosciLatwy);
        add(poziomTrudnosciTrudny);
        add(poziomTrudnosciSredni);
        //dodanie funkcjonalnosci przycisku
        poziomTrudnosciSredni.addActionListener(this);
        poziomTrudnosciLatwy.addActionListener(this);
        poziomTrudnosciTrudny.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //reakcje na nacisniete przyciski
        Object zrodlo = e.getSource();

        if (zrodlo == poziomTrudnosciLatwy) {
            Gra plansza = new Gra(main.SZEROKOSC, main.SZEROKOSC);
            plansza.trudnosc = 10;
        }
        if (zrodlo == poziomTrudnosciSredni) {
            Gra plansza = new Gra(main.SZEROKOSC, main.SZEROKOSC);
            plansza.trudnosc = 5;
        }
        if (zrodlo == poziomTrudnosciTrudny) {
            Gra plansza = new Gra(main.SZEROKOSC, main.SZEROKOSC);
            plansza.trudnosc = 2;
        }
    }

}
