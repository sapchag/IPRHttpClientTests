package Interfaces;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DbClient;

import java.sql.SQLException;
import java.util.List;

public class IEntitiesDbImpl<T> implements IEntities {

    String sql;
    BeanListHandler beanListHandler;

    public IEntitiesDbImpl(String sql, BeanListHandler beanListHandler) {
        this.sql = sql;
        this.beanListHandler = beanListHandler;
    }

    @Override
    public T get(long id) {
        List<T> entity = null;
        try {
            entity = new DbClient().getList(sql + " where id=" + id, beanListHandler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity.size() > 0 ? entity.get(0) : null;
    }

    @Override
    public List<T> getAll() {
        try {
            return new DbClient().getList(sql, beanListHandler);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
