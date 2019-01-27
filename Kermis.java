package suikerspin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kermis {
	static boolean start = true;
	static List<Attracties> attracties = new ArrayList<>();
	public static void main(String[] args){
		StartSpel starten = new StartSpel();
		starten.printMenu();
		starten.list(attracties);
		while(start) {
			starten.startie(attracties);
		}
	}
}
class StartSpel{
	Kassa kassa = new Kassa();
	BotsAuto a = new BotsAuto("Botsauto", 0, 2.50, 0.0, 0);
	Spin b = new Spin("Spin", 0, 2.25, 0, 0);
	SpiegelPaleis c = new SpiegelPaleis("Spiegelpaleis", 0, 2.75, 0.0, 0);
	SpookHuis d = new SpookHuis("Spookhuis", 0, 3.20, 0.0, 0);
	Hawaii e = new Hawaii("Hawaii", 0, 2.90, 0.0, 0);
	LadderKlimmen f = new LadderKlimmen("Ladderklimmen", 0, 5.00, 0.0, 0);
	
	void startie(List<Attracties> attracties) {
		while(true) {
		Scanner scan = new Scanner(System.in);
		String keuze = scan.nextLine();{
			switch(keuze){
			case "1": a.attractieKiezen();
				break;
			case "2": b.attractieKiezen();
				break;
			case "3": c.attractieKiezen();
				break;
			case "4": d.attractieKiezen();
				break;
			case "5": e.attractieKiezen();
				break;
			case "6": f.attractieKiezen();
				break;
			case "o": kassa.kassaOmzet(attracties);
				break;
			case "k": kassa.kassaKaartjes(attracties);
				break;
			case "m": b.monteurMaken();
				break;
			case "n": e.monteurMaken();
				break;
			case "b": f.kansSpelBelastingBetalen();
			    break;
				default: System.out.println("Voer geldig getal in");
			}
		}
	    }
	}

	void printMenu() {
		System.out.println("kies 1 voor "+a.naam);
		System.out.println("kies 2 voor "+b.naam);
		System.out.println("kies 3 voor "+c.naam);
		System.out.println("kies 4 voor "+d.naam);
		System.out.println("kies 5 voor "+e.naam);
		System.out.println("kies 6 voor "+f.naam);
	}
	void list(List<Attracties> attracties) {
		attracties.add(a);
		attracties.add(b);
		attracties.add(c);
		attracties.add(d);
		attracties.add(e);
		attracties.add(f);
	}
}
class Kassa{ 

	public void kassaOmzet(List<Attracties> attracties) {
		double opbrengst=0;
		for(Attracties ls : attracties) {
			opbrengst += ls.omzet;
		}
		System.out.println("De totale omzet is : "+opbrengst);
	}
	void kassaKaartjes(List<Attracties> attracties) {
		int totaalKaartjes=0;
		for(Attracties lsTwee : attracties) {
			totaalKaartjes += lsTwee.kaartjes;
		}
		System.out.println("Het totaal aantal verkochte kaartjes is : "+totaalKaartjes);
	}
}
abstract class Attracties{
	String naam;
	int oppervlakte;
	double prijs;
	double omzet;
	int kaartjes;
	
	Attracties(String naam, int oppervlakte, double prijs, double omzet, int kaartjes) {
		this.naam = naam;
		this.oppervlakte = oppervlakte;
		this.prijs = prijs;
		this.omzet = omzet;
		this.kaartjes = kaartjes;
	}	
	public void attractieKiezen() {
		System.out.println("De attractie "+naam+" draait");
		System.out.println("De omzet is : "+(omzet += prijs));
		System.out.println("Het aantal verkochte kaartjes is : "+(++kaartjes));
	}
}
abstract class RisicoRijkeAttracties extends Attracties{
	int draaiLimiet;
	protected int aantalBeurtenGehad;
	
	RisicoRijkeAttracties(String naam, int oppervlakte, double prijs, double omzet, int kaartjes) {
		super(naam, oppervlakte, prijs, omzet, kaartjes);
	}
	@Override
	public void attractieKiezen() {
		if (aantalBeurtenGehad == draaiLimiet) {
			System.out.println("De monteur moet langskomen");
		}	else {
			this.kaartjes++;
			this.aantalBeurtenGehad++;
			this.omzet += prijs;
		}
		System.out.println("De attractie "+naam+" draait");
		System.out.println("De omzet is : "+omzet);
		System.out.println("Het aantal verkochte kaartjes is : "+kaartjes);
		
	}
	public void monteurMaken() {
		System.out.println("De monteur heeft de attractie gemaakt");
		aantalBeurtenGehad = 0;	
	}
}
abstract class GokAttracties extends Attracties{
	GokAttracties(String naam, int oppervlakte, double prijs, double omzet, int kaartjes) {
		super(naam, oppervlakte, prijs, omzet, kaartjes);
	}
}
class BotsAuto extends Attracties{
		
	BotsAuto(String naam, int oppervlakte, double prijs, double omzet, int kaartjes){
			super(naam, oppervlakte, prijs, omzet, kaartjes);
	}
}
class Spin extends RisicoRijkeAttracties{
	
	Spin(String naam, int oppervlakte, double prijs, double omzet, int kaartjes){
		super(naam, oppervlakte, prijs, omzet, kaartjes);
		draaiLimiet = 5;
	} 
}
class SpiegelPaleis extends Attracties{
	
	SpiegelPaleis(String naam, int oppervlakte, double prijs, double omzet, int kaartjes){
		super(naam, oppervlakte, prijs, omzet, kaartjes);
	}
}
class SpookHuis extends Attracties{
	
	SpookHuis(String naam, int oppervlakte, double prijs, double omzet, int kaartjes){
		super(naam, oppervlakte, prijs, omzet, kaartjes);
	}
}
class Hawaii extends RisicoRijkeAttracties{

	Hawaii(String naam, int oppervlakte, double prijs, double omzet, int kaartjes){
		super(naam, oppervlakte, prijs, omzet, kaartjes);
		draaiLimiet = 10;
	}
}
class LadderKlimmen extends GokAttracties implements Belasting{
	LadderKlimmen(String naam, int oppervlakte, double prijs, double omzet, int kaartjes){
		super(naam, oppervlakte, prijs, omzet, kaartjes);
	}

	@Override
	public void kansSpelBelastingBetalen() {
		System.out.println("Je betaalt "+(omzet*percentage)+" belasting over de omzet");	
	}
}
interface Belasting{
	double percentage = 0.30;
	void kansSpelBelastingBetalen();
}