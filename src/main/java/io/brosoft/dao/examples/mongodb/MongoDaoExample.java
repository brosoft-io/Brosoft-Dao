package io.brosoft.dao.examples.mongodb;

import io.brosoft.dao.MongoDao;
import io.brosoft.dao.annotation.MongoCollection;

@MongoCollection(bean = MongoBean.class, collection = "testCollection", mongoInitializer = MongoInitExample.class)
public class MongoDaoExample extends MongoDao<MongoBean> {
}
