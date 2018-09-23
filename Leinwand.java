import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Leinwand ist eine Klasse, die einfache Zeichenoperationen auf einer
 * leinwandartigen Zeichenfl�che erm�glicht. Sie ist eine vereinfachte Version
 * der Klasse Canvas (englisch f�r Leinwand) des JDK und wurde speziell f�r das
 * Projekt "Figuren" geschrieben.
 * 
 * 
 * @author: Bruce Quig
 * @author: Michael K�lling (mik)
 * @author: Axel Schmolitzky
 * 
 * @version: 2006.03.30
 */
public class Leinwand {
    // Hinweis: Die Implementierung dieser Klasse (insbesondere die
    // Verwaltung der Farben und Identit�ten der Figuren) ist etwas
    // komplizierter als notwendig. Dies ist absichtlich so, weil damit
    // die Schnittstellen und Exemplarvariablen der Figuren-Klassen
    // f�r den Lernanspruch dieses Projekts einfacher und klarer
    // sein k�nnen.

    private static Leinwand leinwandSingleton;

    /**
     * Fabrikmethode, die eine Referenz auf das einzige Exemplar dieser Klasse
     * zur�ckliefert. Wenn es von einer Klasse nur genau ein Exemplar gibt, wird
     * dieses als 'Singleton' bezeichnet.
     */
    public static Leinwand gibLeinwand() {
        if (leinwandSingleton == null) {
            leinwandSingleton = new Leinwand("Auto mit Ampel", 800, 400, Color.white);
        }
        leinwandSingleton.setzeSichtbarkeit(true);
        return leinwandSingleton;
    }

    /**
     * Klassenmethode um neues Zeichnen anzufordern.
     */
    public static void neuZeichnen() {
        gibLeinwand().zeichenflaeche.repaint();
    }

    // ----- Exemplarvariablen -----

    private JFrame fenster;

    private Zeichenflaeche zeichenflaeche;

    private Graphics2D graphic;

    private Color hintergrundfarbe;

    private Image leinwandImage;

    private ArrayList<Object> figuren;

    private HashMap<Object, ShapeMitFarbe> figurZuShape;

    /**
     * Erzeuge eine Leinwand.
     * 
     * @param titel
     *            Titel, der im Rahmen der Leinwand angezeigt wird
     * @param breite
     *            die gew�nschte Breite der Leinwand
     * @param hoehe
     *            die gew�nschte H�he der Leinwand
     * @param grundfarbe
     *            die Hintergrundfarbe der Leinwand
     */
    private Leinwand(String titel, int breite, int hoehe, Color grundfarbe) {
        fenster = new JFrame();
        zeichenflaeche = new Zeichenflaeche();
        fenster.setContentPane(zeichenflaeche);
        fenster.setTitle(titel);
        zeichenflaeche.setPreferredSize(new Dimension(breite, hoehe));
        hintergrundfarbe = grundfarbe;
        fenster.pack();
        figuren = new ArrayList<Object>();
        figurZuShape = new HashMap<Object, ShapeMitFarbe>();
    }

    /**
     * Setze, ob diese Leinwand sichtbar sein soll oder nicht. Wenn die Leinwand
     * sichtbar gemacht wird, wird ihr Fenster in den Vordergrund geholt. Diese
     * Operation kann auch benutzt werden, um ein bereits sichtbares
     * Leinwandfenster in den Vordergrund (vor andere Fenster) zu holen.
     * 
     * @param sichtbar
     *            boolean f�r die gew�nschte Sichtbarkeit: true f�r sichtbar,
     *            false f�r nicht sichtbar.
     */
    public void setzeSichtbarkeit(boolean sichtbar) {
        if (graphic == null) {
            // erstmaliger Aufruf: erzeuge das Bildschirm-Image und f�lle
            // es mit der Hintergrundfarbe
            Dimension size = zeichenflaeche.getSize();
            leinwandImage = zeichenflaeche.createImage(size.width, size.height);
            graphic = (Graphics2D) leinwandImage.getGraphics();
            graphic.setColor(hintergrundfarbe);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        fenster.setVisible(sichtbar);
    }

    /**
     * Zeichne f�r das gegebene Figur-Objekt eine Java-Figur (einen Shape) auf
     * die Leinwand.
     * 
     * @param figur
     *            das Figur-Objekt, f�r das ein Shape gezeichnet werden soll
     * @param farbe
     *            die Farbe der Figur
     * @param shape
     *            ein Objekt der Klasse Shape, das tats�chlich gezeichnet wird
     */
    public synchronized void zeichne(Object figur, String farbe, Shape shape) {
        if( !figuren.contains( figur ) ) figuren.add(figur); // am Ende hinzufuegen, falls noch nicht vorhanden
        figurZuShape.put(figur, new ShapeMitFarbe(shape, farbe));  // Shape aktualisieren
    }

    /**
     * Entferne die gegebene Figur von der Leinwand.
     * 
     * @param figur
     *            die Figur, deren Shape entfernt werden soll
     */
    public synchronized void entferne(Object figur) {
        figuren.remove(figur); // entfernen,falls schon eingetragen
        figurZuShape.remove(figur);
    }

    /**
     * Setze die Zeichenfarbe der Leinwand.
     * 
     * @param farbname
     *            der Name der neuen Zeichenfarbe.
     */
    public void setzeZeichenfarbe(String farbname)
    {
        if (farbname.equals("rot"))
            graphic.setColor(Color.red);
        else if (farbname.equals("schwarz"))
            graphic.setColor(Color.black);
        else if (farbname.equals("blau"))
            graphic.setColor(Color.blue);
        else if (farbname.equals("hellblau"))
            graphic.setColor(Color.cyan);
        else if (farbname.equals("dunkelgrau"))
            graphic.setColor(Color.DARK_GRAY);
        else if (farbname.equals("grau"))
            graphic.setColor(Color.gray);
        else if (farbname.equals("hellgrau"))
            graphic.setColor(Color.LIGHT_GRAY);
        else if (farbname.equals("gelb"))
            graphic.setColor(Color.yellow);
        else if (farbname.equals("orange"))
            graphic.setColor(Color.orange);
        else if (farbname.equals("pink"))
            graphic.setColor(Color.pink);
        else if (farbname.equals("gruen"))
            graphic.setColor(Color.green);
        else if (farbname.equals("lila"))
            graphic.setColor(Color.magenta);
        else if (farbname.equals("weiss"))
            graphic.setColor(Color.white);
        else
            graphic.setColor(Color.black);
    }

    /***************************************************************************
     * Interne Klasse Zeichenflaeche - die Klasse f�r die GUI-Komponente, die
     * tats�chlich im Leinwand-Fenster angezeigt wird. Diese Klasse definiert
     * ein JPanel mit der zus�tzlichen M�glichkeit, das auf ihm gezeichnet Image
     * aufzufrischen (erneut zu zeichnen).
     */
    private class Zeichenflaeche extends JPanel {
        private static final long serialVersionUID = 20060330L;

        public synchronized void paint(Graphics g) {
            // Loesche leinwandImage (=graphic).
            graphic.setColor(hintergrundfarbe);
            graphic.fill( new Rectangle(0, 0, zeichenflaeche.getWidth(), zeichenflaeche.getHeight() ) );
            
            // Zeichne alle Figuren (d.h. Shape der Figur) auf leinwandImage.
            // Reihenfolge ist durch die Liste figuren festgelegt!
            for (Object figur : figuren) {
                figurZuShape.get(figur).draw(graphic);
            }           
            
            // Zeichne leinwandImage auf die Leinwand.
            g.drawImage(leinwandImage, 0, 0, null);
        }
    }

    /***************************************************************************
     * Interne Klasse ShapeMitFarbe - Da die Klasse Shape des JDK nicht auch
     * eine Farbe mitverwalten kann, muss mit dieser Klasse die Verkn�pfung
     * modelliert werden.
     */
    private class ShapeMitFarbe {
        private Shape shape;

        private String farbe;

        public ShapeMitFarbe(Shape shape, String farbe) {
            this.shape = shape;
            this.farbe = farbe;
        }

        public void draw(Graphics2D graphic) {
            setzeZeichenfarbe(farbe);
            graphic.fill(shape);
        }
    }

}
