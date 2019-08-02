package io.brosoft.dao.examples.sqlite;

import io.brosoft.dao.SQLDao;
import io.brosoft.dao.annotation.SQLTable;

@SQLTable(table = "TESTTABLE", bean = SqliteBeanExample.class, dbInitializer = SqliteInitExmple.class)
public class SqliteDaoExample extends SQLDao<SqliteBeanExample> {

}
