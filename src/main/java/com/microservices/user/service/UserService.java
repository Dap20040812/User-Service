package com.microservices.user.service;

import com.microservices.user.controller.dto.UserDTO;
import com.microservices.user.feing.CarFeign;
import com.microservices.user.feing.MotorcycleFeign;
import com.microservices.user.model.Car;
import com.microservices.user.model.Motorcycle;
import com.microservices.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {

    private RestTemplate restTemplate;
    private BDService bd;
    private CarFeign carFeign;
    private MotorcycleFeign motorcycleFeign;

    public List<User> getAll() {
        return bd.findAll();
    }

    public User getUserById(int id) {
        return bd.findById(id);
    }

    public User save(UserDTO user) {

        User newUser = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
        return bd.save(newUser);
    }

    public List<Car> getCars(int userId) {
        return restTemplate.getForObject("http://localhost:8081/car/user/" + userId, List.class);
    }

    public List<Motorcycle> getMotorcycles(int userId) {
        return restTemplate.getForObject("http://localhost:8082/motorcycle/user/" + userId, List.class);
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        return carFeign.save(car);
    }

    public Motorcycle saveMoto(int userId,Motorcycle moto) {
        moto.setUserId(userId);
        return motorcycleFeign.save(moto);
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String,Object> result = new HashMap<>();
        User user = bd.findById(userId);

        if(user == null) {
            result.put("Mensaje", "El usuario no existe");
            return result;
        }

        result.put("User",user);
        List<Car> cars = carFeign.getCars(userId);
        if(cars == null || cars.isEmpty()) {
            result.put("Carros", "El usuario no tiene carros");
        }
        else {
            result.put("Cars", cars);
        }

        List<Motorcycle> motorcycles = motorcycleFeign.getMotorcycle(userId);
        if(motorcycles == null || motorcycles.isEmpty()) {
            result.put("Motos", "El usuario no tiene motos");
        }
        else {
            result.put("Motos", motorcycles);
        }
        return result;
    }

}
