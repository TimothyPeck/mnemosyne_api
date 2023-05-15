package ch.hearc.mnemosyne_api.model.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ch.hearc.mnemosyne_api.model.BookRest;
import ch.hearc.mnemosyne_api.repository.BookRestRepository_I;



@Component
public class DBSeeder implements CommandLineRunner {

	@Autowired
	BookRestRepository_I bookRestRepo;

	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	private void loadData() {
		bookRestRepo.save(new BookRest(books[0][0], books[0][1], books[0][2]));
		bookRestRepo.save(new BookRest(books[1][0], books[1][1], books[1][2]));
		bookRestRepo.save(new BookRest(books[2][0], books[2][1], books[2][2]));
		bookRestRepo.save(new BookRest(books[3][0], books[3][1], books[3][2]));

		bookRestRepo.save(new BookRest(books[4][0], books[4][1], books[4][2]));
		bookRestRepo.save(new BookRest(books[5][0], books[5][1], books[5][2]));
		bookRestRepo.save(new BookRest(books[6][0], books[6][1], books[6][2]));
		bookRestRepo.save(new BookRest(books[7][0], books[7][1], books[7][2]));

		bookRestRepo.save(new BookRest(books[8][0], books[8][1], books[8][2]));
		bookRestRepo.save(new BookRest(books[9][0], books[9][1], books[9][2]));
		bookRestRepo.save(new BookRest(books[10][0], books[10][1], books[10][2]));
		bookRestRepo.save(new BookRest(books[11][0], books[11][1], books[11][2]));

		System.out.println("Database OK");

	}

	private String[][] books = { { "TODAG", "400", "Tales of demons and gods" },
			{ "SSOASK", "32", "Survival story of a sword king" }, { "Dungeon reset", "41", " " },
			{ "Boruto", "75", "Beurk" }, { "Kaiju n°8", "72", "Le Kaiju n°10 veut devenir une épée" },
			{ "One piece", "684", " Desrosa, beaucoup trop long" }, { "Dandadan", "76", " " },
			{ "TBATE", "38", "The beginning after the end" }, { "UQ Holder", "161", "Arc finale" },
			{ "World trigger", "145", "A finir mais aura pas de suite" },
			{ "Grand Blue", "60.5", "Le meilleure manga humour" },
			{ "Kaiju n°8", "72", "Le Kaiju n°10 veut devenir une épée" }, };
}
