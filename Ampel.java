public class Ampel {
    private String zustand;
    // rot / rotgelb / grün / gelb
    private Rechteck gehaeuse;
    private Oval lampeOben, lampeMitte, lampeUnten;
    
    public Ampel() {
        gehaeuse = new Rechteck();
        gehaeuse.farbeAendern( "schwarz" );
        gehaeuse.groesseAendern( 100, 300 );
        gehaeuse.positionAendern( 700, 100 );
        
        lampeOben = new Oval();
        lampeOben.farbeAendern( "rot" );
        lampeOben.groesseAendern( 80, 80 );
        lampeOben.positionAendern( 710, 110 );
        
        lampeMitte = new Oval();
        lampeMitte.farbeAendern( "gelb" );
        lampeMitte.groesseAendern( 80, 80 );
        lampeMitte.positionAendern( 710, 210 );
        
        lampeUnten = new Oval();
        lampeUnten.farbeAendern( "gruen" );
        lampeUnten.groesseAendern( 80, 80 );
        lampeUnten.positionAendern( 710, 310 );
        
        zustand = "rot";
        lampeOben.sichtbarMachen();
        lampeMitte.unsichtbarMachen();
        lampeUnten.unsichtbarMachen();
        
        Leinwand.neuZeichnen();
    }
    
    public String zustandGeben() {
        return zustand;
    }
    
    public void weiterschalten() {
        if( zustand == "rot" ) {
            zustand = "rotgelb";
            lampeMitte.sichtbarMachen();
        } else {
            if( zustand == "rotgelb" ) {
                zustand = "gruen";
                lampeOben.unsichtbarMachen();
                lampeMitte.unsichtbarMachen();
                lampeUnten.sichtbarMachen();
            } else {
                if( zustand == "gruen" ) {
                    zustand = "gelb";
                    lampeUnten.unsichtbarMachen();
                    lampeMitte.sichtbarMachen();
                } else {
                    if( zustand == "gelb" ) {
                        zustand = "rot";
                        lampeMitte.unsichtbarMachen();
                        lampeOben.sichtbarMachen();
                    }
                }
            }
        }

        Leinwand.neuZeichnen();
    }
}