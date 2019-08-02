package cucumber.dao.postgres;

import cucumber.beans.Anime;
import cucumber.constants.CucumberConstants;
import cucumber.db.init.PostgresqlConnection;
import io.brosoft.dao.SQLDao;
import io.brosoft.dao.annotation.SQLTable;

@SQLTable(bean = Anime.class, dbInitializer = PostgresqlConnection.class, table = CucumberConstants.ANIME_TABLE)
public class PostgresAnimeDao extends SQLDao<Anime> {

}
