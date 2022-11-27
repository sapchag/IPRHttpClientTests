package entities;

import Interfaces.IEntitiesApiImpl;
import Interfaces.IEntitiesDbImpl;
import Interfaces.IOperationsApiImpl;
import handlers.UserHandler;
import models.User;
import utils.RestPaths;

import static utils.RestPaths.*;

public class UserEntity extends BaseEntity<User> {

    public UserEntity() {
        iDbEntities = new IEntitiesApiImpl<User>(RestPaths.user, RestPaths.users, User.class);
        iApiEntities = new IEntitiesDbImpl<User>("SELECT * FROM person", new UserHandler());
        iOperations = new IOperationsApiImpl<User>(addUser, updateUser, deleteUser, User.class);
    }
}
