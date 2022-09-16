package autoservice.controller;

import autoservice.dto.request.CarRequestDto;
import autoservice.dto.response.CarResponseDto;
import autoservice.model.Car;
import autoservice.model.Owner;
import autoservice.service.CarService;
import autoservice.service.OwnerService;
import autoservice.service.mapper.CarMapper;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;
    private final OwnerService ownerService;

    public CarController(CarService carService, CarMapper carMapper, OwnerService ownerService) {
        this.carService = carService;
        this.carMapper = carMapper;
        this.ownerService = ownerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarResponseDto createCar(@Valid @RequestBody CarRequestDto carRequestDto) {
        Owner owner = ownerService.getOwnerById(carRequestDto.getOwnerId());
        owner.getCars().add(carMapper.mapToModel(carRequestDto));
        ownerService.createOwner(owner);
        return carMapper.mapToDto(carService.createCar(carMapper.mapToModel(carRequestDto)));
    }

    @PutMapping("/{id}")
    public CarResponseDto updateCar(@PathVariable Long id,
                                      @Valid @RequestBody CarRequestDto carRequestDto) {
        Car car = carMapper.mapToModel(carRequestDto);
        car.setId(id);
        return carMapper.mapToDto(carService.createCar(car));
    }
}
