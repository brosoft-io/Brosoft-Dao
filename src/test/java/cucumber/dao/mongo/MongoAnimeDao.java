package cucumber.dao.mongo;

import cucumber.beans.Anime;
import cucumber.constants.CucumberConstants;
import cucumber.db.init.MongoConnection;
import io.brosoft.dao.MongoDao;
import io.brosoft.dao.annotation.MongoCollection;

@MongoCollection(bean = Anime.class, collection = CucumberConstants.ANIME_TABLE, mongoInitializer = MongoConnection.class)
public class MongoAnimeDao extends MongoDao<Anime> {

}
