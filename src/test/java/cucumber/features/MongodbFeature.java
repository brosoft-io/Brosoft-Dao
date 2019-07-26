package cucumber.features;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.beans.Anime;
import cucumber.constants.CucumberConstants;
import cucumber.dao.mongo.MongoAnimeDao;
import io.brosoft.dao.MongoDao;
import io.brosoft.dao.exceptions.ExecutionException;
import io.brosoft.dao.util.KeyPair;

public class MongodbFeature {

	MongoDao<Anime> animeDao;
	Anime anime1;
	Anime anime2;
	
	@Given("a Mongo DB with an anime table")
	public void a_Mongo_DB_with_an_anime_table() throws ExecutionException {
	    animeDao = new MongoAnimeDao();
	    List<Anime> animes = animeDao.readAll();
	    for (Anime anime : animes) {
	    	animeDao.delete(new KeyPair(CucumberConstants.ANIME_TITLE, anime.getAnime()));
	    }
	}

	@Given("an anime was inserted into the Mongo DB anime table")
	public void an_anime_was_inserted_into_the_Mongo_DB_anime_table() throws ExecutionException {
		anime1 = new Anime();
		anime1.setAnime("Black Butler");
		anime1.setYear(2008);
		anime1.setEpisodeCount(24);
	    assert animeDao.create(anime1);
	}

	@When("the anime is updated in the Mongo DB")
	public void the_anime_is_updated_in_the_Mongo_DB() throws ExecutionException {
		anime2 = new Anime();
		anime2.setAnime("Land of the Lustrous");
		anime2.setYear(2017);
		anime2.setEpisodeCount(12);
		assert animeDao.update(anime2, new KeyPair(CucumberConstants.ANIME_TITLE, anime1.getAnime()));
	}

	@Then("the change should be persistent in the Mongo DB")
	public void the_change_should_be_persistent_in_the_Mongo_DB() throws ExecutionException {
		List<Anime> animes = animeDao.read(new KeyPair(CucumberConstants.ANIME_TITLE, anime2.getAnime()));
	    assert !animes.isEmpty();
	    assert animes.get(0).getAnime().equals(anime2.getAnime());
	    assert animes.get(0).getYear() == (anime2.getYear());
	    assert animes.get(0).getEpisodeCount() == (anime2.getEpisodeCount());
	}

	@Then("the anime should be deleatable from the Mongo DB")
	public void the_anime_should_be_deleatable_from_the_Mongo_DB() throws ExecutionException {
		assert animeDao.delete(new KeyPair(CucumberConstants.ANIME_TITLE, anime2.getAnime()));
	}
}
