package entities;

import Interfaces.IEntitiesApiImpl;
import Interfaces.IEntitiesDbImpl;
import Interfaces.IOperationsApiImpl;
import models.Car;
import models.User;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import static utils.RestPaths.*;

public class CarEntity extends BaseEntity<Car> {
    public CarEntity() {
        iDbEntities = new IEntitiesApiImpl<Car>(car, cars, Car.class);
        String sql = "SELECT car.id, car.mark, car.model, " +
                "car.price, engine_type.type_name as engineType " +
                "FROM car inner join engine_type " +
                "on car.engine_type_id=engine_type.id";
        iApiEntities = new IEntitiesDbImpl<Car>(sql, new BeanListHandler<>(Car.class));
        iOperations = new IOperationsApiImpl<Car>(addCar, updateCar, deleteCar, Car.class);
    }
}
