package com.microservices.user.feing;

import com.microservices.user.model.Motorcycle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "motorcycle-service", url = "http://localhost:8082")
public interface MotorcycleFeign {
    @PostMapping("/motorcycle")
    Motorcycle save(@RequestBody Motorcycle motorcycle);

    @GetMapping("/motorcycle/user/{userId}")
    List<Motorcycle> getMotorcycle(@PathVariable("userId") int userId);
}
