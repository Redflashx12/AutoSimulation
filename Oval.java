import java.awt.geom.Ellipse2D;

/**
 * Ein Oval, das manipuliert werden kann und sich selbst auf einer Leinwand
 * zeichnet.
 * 
 * @author Jenny Kühlmann und Tessa Friedrich
 * @version 2010.01.18
 */

public class Oval 
{
    private int hoehe;
    private int breite;
    private int xPosition;
    private int yPosition;
    public String farbe;
    private boolean istSichtbar;

    /**
     * Erzeuge ein neues Oval an einer Standardposition mit einer
     * Standardfarbe.
     */
    public Oval() 
    {
        hoehe = 30;
        breite = 50;
        xPosition = 100;
        yPosition = 100;
        farbe = "blau";
        istSichtbar = true;
        zeichnen();
    }

    /**
     * Mache dieses Oval sichtbar. Wenn es bereits sichtbar ist, tue nichts.
     */
    public void sichtbarMachen() 
    {
        istSichtbar = true;
        zeichnen();
    }

    /**
     * Mache dieses Oval unsichtbar. Wenn es bereits unsichtbar ist, tue
     * nichts.
     */
    public void unsichtbarMachen() 
    {
        loeschen();
        istSichtbar = false;
    }

    /**
     * Ändere die Größe dieses Kreises in 'neueBreite' und 'neueHoehe" (Angabe
     * in Bildschirmpunkten). 'neueHoehe' und 'neueBreite' müssen größer gleich
     * Null sein.
     */
    public void groesseAendern(int neueBreite, int neueHoehe) 
    {
        breite = neueBreite;
        hoehe =  neueHoehe;
        zeichnen();
    }

	/**
	 * Ändere die Position dieses Ovals.
	 */
	public void positionAendern(int neueXPosition, int neueYPosition) 
	{
		xPosition = neueXPosition;
		yPosition = neueYPosition;
		zeichnen();
	}

    /**
     * Ändere die Farbe dieses Ovals in 'neueFarbe'. Gültige Angaben sind
     * "rot", "schwarz, "blau", "hellblau", "dunkelgrau", "grau", "hellgrau",
     * "gelb", "orange", "pink", "gruen", "lila" und "weiss".
     */
    public void farbeAendern(String neueFarbe) 
    {
        farbe = neueFarbe;
        zeichnen();
    }
    

    /**
     * Zeichne dieses Oval mit seinen aktuellen Werten auf den Bildschirm.
     */
    private void zeichnen() 
    {
        if (istSichtbar) {
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.zeichne(this, farbe, new Ellipse2D.Double(xPosition,
                    yPosition, breite, hoehe));
        }
    }

    /**
     * Lösche dieses Oval vom Bildschirm.
     */
    private void loeschen() 
    {
        if (istSichtbar) {
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.entferne(this);
        }
    }

    public void warte(int millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (Exception e) {
            // Exception ignorieren
        }
    }
}
