package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.utils.AppException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws AppException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		if(!NumberUtils.isDigits(saisieMin)){
			throw new AppException("Min doit etre un entier supérieur à 0");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		if(min <= 0){
			throw new AppException("Min doit etre un entier supérieur à 0");
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		int max = Integer.parseInt(saisieMax) * 1000;
		if(!NumberUtils.isDigits(saisieMin)){
			throw new AppException("Max doit etre un entier inférieur à min");
		}
		if(min >= max || max <= 0){
			throw new AppException("Max doit etre un entier inférieur à min");
		}


		boolean findDep = false;
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
					findDep = true;
				}
			}
		}

		if (!findDep) {
			throw new AppException("Département "+choix+" non trouvé");
		}
	}

}
