import java.awt.Rectangle;

/**
 * Ein Rechteck, das manipuliert werden kann und sich selbst auf einer Leinwand
 * zeichnet.
 * 
 * @author Jenny Kühlmann und Tessa Friedrich
 * @version 2010.01.14
 */

public class Rechteck
{
    private int hoehe;
    private int breite;
    private int xPosition;
    private int yPosition;
    private String farbe;
    private boolean istSichtbar;

    /**
     * Erzeuge ein neues Rechteck mit einer Standardfarbe an einer
     * Standardposition.
     */
    public Rechteck() 
    {
        hoehe = 50;
        breite = 50;
        xPosition = 0;
        yPosition = 0;
        farbe = "rot";
        istSichtbar = true;
        zeichnen();
    }

	/**
	 * Ändere die Größe dieses Rechtecks in 'neueGroesse'. 'neueGroesse' muss
	 * groesser gleich Null sein.
	 */
	public void groesseAendern(int neueBreite, int neueHoehe) 
	{
		breite = neueBreite;
		hoehe = neueHoehe;
		zeichnen();
	}

	/**
	 * Ändere die Position dieses Rechtecks.
	 */
	public void positionAendern(int neueXPosition, int neueYPosition) 
	{
		xPosition = neueXPosition;
		yPosition = neueYPosition;
		zeichnen();
	}

	/**
	 * Ändere die Farbe dieses Rechtecks in 'neueFarbe'. Gültige Angaben sind
	 * "rot", "schwarz, "blau", "hellblau", "dunkelgrau", "grau", "hellgrau",
	 * "gelb", "orange", "pink", "gruen", "lila" und "weiss".
	 */
	public void farbeAendern(String neueFarbe) 
	{
		farbe = neueFarbe;
		zeichnen();
	}
	
    /**
     * Zeichne dieses Rechteck mit seinen aktuellen Werten auf den Bildschirm.
     */
    private void zeichnen() 
    {
        if (istSichtbar) {
            Leinwand leinwand = Leinwand.gibLeinwand();
            leinwand.zeichne(this, farbe, new Rectangle(xPosition, yPosition,
                    breite, hoehe));
        }
    }
}
