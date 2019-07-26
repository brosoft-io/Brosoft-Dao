package cucumber.beans;

import cucumber.constants.CucumberConstants;
import io.brosoft.dao.annotation.MongoField;
import io.brosoft.dao.annotation.SQLField;

public class Anime {

	@MongoField(key = CucumberConstants.ANIME_TITLE)
	@SQLField(name = CucumberConstants.ANIME_TITLE)
	private String anime;
	@MongoField(key = CucumberConstants.ANIME_YEAR)
	@SQLField(name = CucumberConstants.ANIME_YEAR)
	private int year;
	@MongoField(key = CucumberConstants.ANIME_EPS)
	@SQLField(name = CucumberConstants.ANIME_EPS)
	private int episodeCount;
	public String getAnime() {
		return anime;
	}
	public void setAnime(String anime) {
		this.anime = anime;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getEpisodeCount() {
		return episodeCount;
	}
	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}
	
}
