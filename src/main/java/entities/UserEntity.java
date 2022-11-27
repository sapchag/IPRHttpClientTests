package entities;

import Interfaces.EntitiesApiImpl;
import Interfaces.EntitiesDbImpl;
import Interfaces.CRUDApiImpl;
import handlers.UserHandler;
import models.User;
import utils.RestPaths;

import static utils.RestPaths.*;

public class UserEntity extends BaseEntity<User> {

    public UserEntity() {
        iApiEntities = new EntitiesApiImpl<User>(RestPaths.user, RestPaths.users, User.class);
        iDbEntities = new EntitiesDbImpl<User>("SELECT * FROM person", new UserHandler());
        iCrud = new CRUDApiImpl<User>(addUser, updateUser, deleteUser, User.class);
    }
}
