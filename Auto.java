public class Auto implements Runnable {
    private int x, y;   // Position des Autos (genauer: der Karosserie)
    private int laenge, hoehe;  // Groesse der Karosserie
    private Rechteck karosserie;
    private Oval rad1, rad2;
    private String zustand;

    private Ampel ampel;

    public void ampelAendern( Ampel neueAmpel ) {
        ampel = neueAmpel;
    }

    public void fahren() {
        if( zustand=="vorHL" ) {
            if (x < 500) {
                x = x + 1;
                zustand="vorHL";
            } 
            else { zustand="Halt";  
                x=x+1;

            }
        }

        else         if (zustand=="Halt") {
            if(ampel.zustandGeben()=="gruen") {
                x=x+1;
                zustand="nachHL";
            }   
        }
        else { if ( x<1000) {
                x=x+1;
            }
            else { x=0;
                zustand="vorHL";
            }

        }

        aktualisieren();
    }

    public Auto() {
        x = 0;
        y = 220;
        laenge = 60;
        hoehe = 20;

        rad1 = new Oval();
        rad1.farbeAendern( "schwarz" );
        rad1.groesseAendern( laenge/4, laenge/4);
        rad2 = new Oval();
        rad2.farbeAendern( "schwarz" );
        rad2.groesseAendern( laenge/4, laenge/4 );
        karosserie = new Rechteck();
        karosserie.farbeAendern( "blau" );
        karosserie.groesseAendern( laenge, hoehe );
        zustand="vorHL";

        aktualisieren();
    }

    public void starten() {
        new Thread( this ).start();  // startet die run-Methode 
        // in einem eigenem Thread
    }

    public void run() {
        while( true ) {             // Endlosschleife
            fahren();
        }
    }

    public void aktualisieren() {
        rad1.positionAendern( x + laenge/8, y + hoehe );
        rad2.positionAendern( x + laenge*5/8, y + hoehe );
        karosserie.positionAendern( x, y );
        Leinwand.neuZeichnen();
        warten( 10 );
    }

    private void warten(int millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (Exception e) {
            // Exception ignorieren
        }
    }

    public void positionAendern( int neuesX, int neuesY ) {
        x = neuesX;
        y = neuesY;
        aktualisieren();
    }

    public void farbeAendern( String neueFarbe ) {
        karosserie.farbeAendern( neueFarbe );
        aktualisieren();
    }
}    