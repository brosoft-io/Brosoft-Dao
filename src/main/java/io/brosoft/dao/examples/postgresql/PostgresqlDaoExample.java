package io.brosoft.dao.examples.postgresql;

import io.brosoft.dao.PostgresqlDao;
import io.brosoft.dao.annotation.SQLTable;

@SQLTable(dbInitializer = PostgresqlInit.class, bean = PostgresqlBean.class, table = "sampleTable")
public class PostgresqlDaoExample extends PostgresqlDao<PostgresqlBean> {
}
