package entities;

import Interfaces.EntitiesApiImpl;
import Interfaces.EntitiesDbImpl;
import Interfaces.CRUDApiImpl;
import models.Car;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import static utils.RestPaths.*;

public class CarEntity extends BaseEntity<Car> {
    public CarEntity() {
        iDbEntities = new EntitiesApiImpl<Car>(car, cars, Car.class);
        String sql = "SELECT car.id, car.mark, car.model, " +
                "car.price, engine_type.type_name as engineType " +
                "FROM car inner join engine_type " +
                "on car.engine_type_id=engine_type.id";
        iApiEntities = new EntitiesDbImpl<Car>(sql, new BeanListHandler<>(Car.class));
        iCrud = new CRUDApiImpl<Car>(addCar, updateCar, deleteCar, Car.class);
    }
}
