package Interfaces;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DbClient;

import java.util.List;

public class EntitiesDbImpl<T> implements IEntities {

    String sql;
    String whereId = "id";
    BeanListHandler beanListHandler;

    public EntitiesDbImpl(String sql, BeanListHandler beanListHandler) {
        this.sql = sql;
        this.beanListHandler = beanListHandler;
    }

    public EntitiesDbImpl(String sql, BeanListHandler beanListHandler, String whereId) {
        this.sql = sql;
        this.beanListHandler = beanListHandler;
        this.whereId = whereId;
    }

    @Override
    public T get(long id) {
        List<T> entity = new DbClient().getList(sql + " where " + whereId + "=" + id, beanListHandler);
        return entity.size() > 0 ? entity.get(0) : null;
    }

    @Override
    public List<T> getAll() {
        return new DbClient().getList(sql, beanListHandler);
    }
}
