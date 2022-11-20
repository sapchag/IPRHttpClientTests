package handlers;

import models.User;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;

public class UserHandler extends BeanListHandler<User> {

	public UserHandler() {
		super(User.class, new UserRowProcessor());
	}
}
