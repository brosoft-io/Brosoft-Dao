package io.brosoft.dao.examples.mongodb;

import io.brosoft.dao.CollectionDao;
import io.brosoft.dao.annotation.DocumentCollection;

@DocumentCollection(bean = MongoBean.class, collection = "testCollection", collectionInitializer = MongoInit.class)
public class MongoDao extends CollectionDao<MongoBean> {
}
