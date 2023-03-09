package com.microservices.user.controller;

import com.microservices.user.controller.dto.UserDTO;
import com.microservices.user.model.Car;
import com.microservices.user.model.Motorcycle;
import com.microservices.user.model.User;
import com.microservices.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listsUsers(){
        List<User> users = userService.getAll();
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDTO user){
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> listsCars(@PathVariable("userId") int id){
        User user = userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Car> cars = userService.getCars(id);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/motorcycles/{userId}")
    public ResponseEntity<List<Motorcycle>> listsMotorcycles(@PathVariable("userId") int id){
        User user = userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Motorcycle> motorcycles = userService.getMotorcycles(id);
        return ResponseEntity.ok(motorcycles);
    }

    @PostMapping("/car/{userId}")
    public ResponseEntity<Car>saveCar(@PathVariable("userId") int userId,@RequestBody Car car){
        Car newCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(newCar);
    }

    @PostMapping("/motorcycle/{userId}")
    public ResponseEntity<Motorcycle> saveMotorcycle(@PathVariable("userId") int userId,@RequestBody Motorcycle motorcycle){
        Motorcycle newMoto = userService.saveMoto(userId, motorcycle);
        return ResponseEntity.ok(newMoto);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> listAllVehicles(@PathVariable("userId") int userId){
        Map<String,Object> result = userService.getUserAndVehicles(userId);
        if(result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
