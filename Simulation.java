public class Simulation {
    private Ampel ampel1;
    private Auto auto1, auto2;
    
    public Simulation() {
        ampel1 = new Ampel();
        auto1 = new Auto();
        auto2 = new Auto();
        auto2.farbeAendern( "rot");
        auto2.positionAendern( 200, 50 );
        auto1.ampelAendern( ampel1 );
        auto2.ampelAendern( ampel1 );
        auto1.starten();
        auto2.starten();
    }
    
    public void weiterschalten() {
        ampel1.weiterschalten();
    }
}
        

        