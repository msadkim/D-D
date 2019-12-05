package dandd;

public class Personatge {

    private String nom;
    private int arquetip;
    private int genere; // 0-null, 1-home, 2-dona
    private double velocitat;
    private double vida;
    private double aguant;
    private double defensa;
    private double fuerza;
    private double coneixement;
    private double voluntat;
    private double percepcio;

    Personatge(String nom, int arquetip) {
        this.setNom(nom);
        this.setArquetip(arquetip);
        this.setVelocitat(0);
        this.setVida(0);
        this.setAguant(0);
        this.setDefensa(0);
        this.setFuerza(0);
        this.setConeixement(0);
        this.setVoluntat(0);
        this.setPercepcio(0);
    }

    public double getPercepcio() {
        return percepcio;
    }

    public void setPercepcio(double percepcio) {
        this.percepcio = percepcio;
    }

    public double getVoluntat() {
        return voluntat;
    }

    public void setVoluntat(double voluntat) {
        this.voluntat = voluntat;
    }

    public double getConeixement() {
        return coneixement;
    }

    public void setConeixement(double coneixement) {
        this.coneixement = coneixement;
    }

    public double getFuerza() {
        return fuerza;
    }

    public void setFuerza(double fuerza) {
        this.fuerza = fuerza;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }

    public double getAguant() {
        return aguant;
    }

    public void setAguant(double aguant) {
        this.aguant = aguant;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(double velocitat) {
        this.velocitat = velocitat;
    }

    public int getGenere() {
        return genere;
    }

    public void setGenere(int genere) {
        this.genere = genere;
    }

    public int getArquetip() {
        return arquetip;
    }

    public void setArquetip(int arquetip) {
        this.arquetip = arquetip;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}