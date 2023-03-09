package com.microservices.user.feing;

import com.microservices.user.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "car-service",url = "http://localhost:8081")
public interface CarFeign  {

    @PostMapping("/car")
    Car save(@RequestBody Car car);

    @GetMapping("/car/user/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);

}
