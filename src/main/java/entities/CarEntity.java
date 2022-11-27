package entities;

import Interfaces.CRUDApiImpl;
import Interfaces.EntitiesApiImpl;
import Interfaces.EntitiesDbImpl;
import models.Car;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import static utils.RestPaths.*;

public class CarEntity extends BaseEntity<Car> {
    public CarEntity() {
        iApiEntities = new EntitiesApiImpl<Car>(car, cars, Car.class);
        String sql = "SELECT car.id, car.mark, car.model, " +
                "car.price, engine_type.type_name as engineType " +
                "FROM car inner join engine_type " +
                "on car.engine_type_id=engine_type.id";
        iDbEntities = new EntitiesDbImpl<Car>(sql, new BeanListHandler<>(Car.class), "car.id");
        iCrud = new CRUDApiImpl<Car>(addCar, updateCar, deleteCar, Car.class);
    }
}
