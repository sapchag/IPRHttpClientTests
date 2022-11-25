package handlers;

import models.User;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class UserHandler extends BeanListHandler<User> {

	public UserHandler() {
		super(User.class, new UserRowProcessor());
	}
}
