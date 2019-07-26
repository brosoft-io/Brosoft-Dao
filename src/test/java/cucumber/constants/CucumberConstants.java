package cucumber.constants;

public abstract class CucumberConstants {

	public static final String ENV = "BROSOFT_DAO_ENV";
	public static final String ENV_VALUE = "gitlab";
	public static final String SQLITE_URI = "jdbc:sqlite:test.db";
	public static final String POSTGRES_URI = "jdbc:postgresql://localhost:5432/test";
	public static final String POSTGRES_GITLAB_URI = "jdbc:postgresql://potgres:5432/test";
	public static final String MONGO_URI = "localhost";
	public static final String MONGO_GITLAB_URI = "mongo";
	public static final int MONGO_PORT = 27017;
	
	public static final String ANIME_TABLE = "ANIME";
	public static final String VIDEO_GAME_TABLE = "VGGAME";
	public static final String FE_CHAR_TABLE = "FECHAR";
	
	public static final String ANIME_TITLE = "title";
	public static final String ANIME_YEAR = "year";
	public static final String ANIME_EPS = "episodes";
}
