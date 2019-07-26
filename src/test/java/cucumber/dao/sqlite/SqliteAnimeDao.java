package cucumber.dao.sqlite;

import cucumber.beans.Anime;
import cucumber.constants.CucumberConstants;
import cucumber.db.init.SqliteConnection;
import io.brosoft.dao.SQLDao;
import io.brosoft.dao.annotation.SQLTable;

@SQLTable(bean = Anime.class, dbInitializer = SqliteConnection.class, table = CucumberConstants.ANIME_TABLE)
public class SqliteAnimeDao extends SQLDao<Anime> {

}
