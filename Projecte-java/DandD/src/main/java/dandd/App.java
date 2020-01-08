package dandd;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.*;

public class App 
{
    public static Personatge personatge;
    public static Enemic enemic;
    public static void main(String[] args){
        
        Personatge[] personatges = {
            new Personatge("Guerrer", 1),
            new Personatge("Mag", 2),
            new Personatge("Elfling", 3)
        };

        System.out.println("Selecciona un personatge");
        for(int i = 0; i < personatges.length; i++)  {
            System.out.println((i+1) + " - " + personatges[i].getNom());
        }
        System.out.println("Premeu 0 per seleccionar aleatoriament");
        int seleccio = (int) preguntarNumero(1, personatges.length, true);
        if (seleccio == 0) {
            seleccio = getRandomNumberInRange(1, personatges.length);
        }
        seleccio--;

        clearScreen(); // Esborra la pantalla
        personatge = new Personatge(personatges[seleccio].getNom(), personatges[seleccio].getArquetip());
        System.out.println("Heu seleccionat el personatge " + personatges[seleccio].getNom());
        pause();

        if (personatge.getArquetip() == 1 || personatge.getArquetip() == 2) {
            System.out.println("Seleccioneu un genere");
            System.out.println("1 - Mascle");
            System.out.println("2 - Femella");
            System.out.println("Premeu 0 per seleccionar aleatoriament");
            seleccio = (int) preguntarNumero(1, 2, true);
            if (seleccio == 0) {
                seleccio = getRandomNumberInRange(1,2);
            }
            personatge.setGenere(seleccio);
        }
        
        personatge.setVelocitat(preguntarStat("la Velocitat"));
        personatge.setAtac(preguntarStat("l'Atac"));
        personatge.setDefensa(preguntarStat("la Defensa"));
        personatge.setAguant(preguntarStat("l'Aguant"));
        personatge.setConeixement(preguntarStat("el Coneixement"));
        personatge.setVoluntat(preguntarStat("la Voluntat"));
        personatge.setPercepcio(preguntarStat("la Percepcio"));

        clearScreen(); // Esborra la pantalla
        System.out.println("Seleccioneu la vida [5 - 20]");
        System.out.println("Premeu 0 per seleccionar aleatoriament");
        seleccio = (int) preguntarNumero(5, 20, true);
        if (seleccio == 0) {
            seleccio = getRandomNumberInRange(5, 20);
        }
        personatge.setVida(seleccio);

        mostarMenuPreBatalla();
        
    }

    private static void mostarMenuPreBatalla() {
        boolean menu = true;
        while (menu) {
            clearScreen(); // Esborra la pantalla
            System.out.println("Que voleu fer?");
            System.out.println("1 - Veure stats del jugador");
            System.out.println("2 - Lluitar amb el seguent enemic");
            int seleccio = (int) preguntarNumero(1, 2, false);
            switch (seleccio) {
                case 1:
                    mostrarStats();
                    break;
                case 2:
                    enemic = seleccionarEnemic();
                    batalla();
                    break;
            }
        }
    }

    private static void batalla() {
        boolean menu = true;
        while (menu) {
            clearScreen(); // Esborra la pantalla
            System.out.println("Que voleu fer?");
            System.out.println("1 - Veure stats del jugador");
            System.out.println("2 - veure stats del enemic");
            System.out.println("3 - Atacar");
            System.out.println("4 - Utilitzar objecte");
            System.out.println("5 - Escapar");
            int seleccio = (int) preguntarNumero(1, 5, false);
            switch (seleccio) {
                case 1:
                    mostrarStats();
                    break;
                case 2:
                    mostrarStatsEnemic();
                    break;
                case 3:
                    if (personatge.getVelocitat() == enemic.getVelocitat()) {
                        int prioritat = getRandomNumberInRange(0,1);
                        if (prioritat == 0) {
                            personatge.atacar(enemic);
                            enemic.atacar(personatge);
                        } else {
                            enemic.atacar(personatge);
                            personatge.atacar(enemic);
                        }
                    } else if (personatge.getVelocitat() > enemic.getVelocitat()) {
                        personatge.atacar(enemic);
                        enemic.atacar(personatge);
                    } else if (personatge.getVelocitat() < enemic.getVelocitat()) {
                        enemic.atacar(personatge);
                        personatge.atacar(enemic);
                    }
                    clearScreen(); // Esborra la pantalla
                    System.out.println("Vida actual del personatge: " + personatge.getVida());
                    System.out.println("Vida actual del enemic: " + enemic.getVida());
                    pause();
                    if (personatge.getVida() <= 0.0) {
                        System.out.println("Heu mort.");
                        pause();
                        System.exit(0);
                    }
                    if (enemic.getVida() <= 0.0) {
                        System.out.println("L'enemic a mort.");
                        pause();
                        menu = false;
                    }
                    break;
                case 4:
                    Objecte objecte = seleccionarObjecte();
                    objecte.utilitzar(personatge);
                    break;
                case 5:
                    clearScreen(); // Esborra la pantalla
                    System.out.println("Heu escapat.");
                    pause();
                    menu = false;
                    break;
            }
        }
    }

    private static void mostrarStats() {
        clearScreen(); // Esborra la pantalla
        System.out.println("Vida: "+personatge.getVida());
        System.out.println("Velocitat: "+personatge.getVelocitat());
        System.out.println("Atac: "+personatge.getAtac());
        System.out.println("Defensa: "+personatge.getDefensa());
        System.out.println("Aguant: "+personatge.getAguant());
        System.out.println("Coneixement: "+personatge.getConeixement());
        System.out.println("Voluntat: "+personatge.getVoluntat());
        System.out.println("Percepcio: "+personatge.getPercepcio());
        pause();
    }

    private static void mostrarStatsEnemic() {
        clearScreen(); // Esborra la pantalla
        System.out.println("Vida: "+enemic.getVida());
        System.out.println("Velocitat: "+enemic.getVelocitat());
        System.out.println("Defensa: "+enemic.getDefensa());
        System.out.println("Atac: "+enemic.getAtac());
        pause();
    }

    public static Enemic seleccionarEnemic() {
        Enemic enemic = new Enemic(0, 0, 0, 0);
        double seleccio;
        seleccio = preguntarStat("la Velocitat");
        if (seleccio == 0) {
            seleccio = getRandomNumberInRange(1,4);
        }
        enemic.setVelocitat(seleccio);
        seleccio = preguntarStat("la Vida");
        if (seleccio == 0) {
            seleccio = getRandomNumberInRange(1,4);
        }
        enemic.setVida(seleccio);
        seleccio = preguntarStat("l'Atac");
        if (seleccio == 0) {
            seleccio = getRandomNumberInRange(1,4);
        }
        enemic.setAtac(seleccio);
        seleccio = preguntarStat("la Defensa");
        if (seleccio == 0) {
            seleccio = getRandomNumberInRange(1,4);
        }
        enemic.setDefensa(seleccio);
        return enemic;
    }

    public static Objecte seleccionarObjecte() {
        boolean menu = true; //variable centinella per sortir del menu
        while (menu) {
            
            Objecte[] objectes = personatge.getObjectes();

            clearScreen(); // Esborra la pantalla
            System.out.println("Quin objecte voleu fer servir?");
            for (int i = 0; i > objectes.length; i++) {
                if (objectes[i].getTipus() < 1) {
                    System.out.println( (i + 1) + " - " + objectes[i].getNom());
                }
            }
            System.out.println("0 - Tornar");
            int seleccio = (int) preguntarNumero(1, objectes.length, true);
            if (seleccio == 0) {
                return null;
            } else if (seleccio > 0 && seleccio <= objectes.length) {
                return objectes[seleccio];
            } else {
                menu = true;
            }
        }
        return null;
    }

    public static double preguntarNumero(int min, int max, boolean zero) {
        double number = 0;
        boolean error = true;
        while (error) {
            try {
                Scanner sc = new Scanner(System.in);
                number = sc.nextDouble();
                if (zero && number == 0) {
                    return number;
                }
                if (number < min || number > max) {
                    throw new EmptyStackException();
                }
                error = false;
            } catch (Exception e) {
                System.out.println("Input invalid");
                error = true;
            }
        }
        return number;
    }

    public static double preguntarStat(String nom) {
            double seleccio;
            clearScreen(); // Esborra la pantalla
            System.out.println("Seleccioneu "+nom+" [1 - 4]");
            System.out.println("Premeu 0 per seleccionar aleatoriament");
            seleccio = (int) preguntarNumero(1, 4, true);
            if (seleccio == 0) {
                seleccio = getRandomNumberInRange(1,4);
            }
            return seleccio;
    }

    public static void pause() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Prem la tecla Enter per continuar . . . ");
        scan.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }
}
