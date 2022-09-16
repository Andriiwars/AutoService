package autoservice.service.mapper;

import autoservice.dto.request.OwnerRequestDto;
import autoservice.dto.response.OwnerResponseDto;
import autoservice.model.Owner;
import autoservice.service.CarService;
import autoservice.service.OrderService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    private final CarService carService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CarMapper carMapper;

    public OwnerMapper(CarService carService,
                       OrderService orderService,
                       OrderMapper orderMapper,
                       CarMapper carMapper) {
        this.carService = carService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.carMapper = carMapper;
    }

    public Owner mapToModel(OwnerRequestDto ownerRequestDto) {
        Owner owner = new Owner();
        owner.setFirstName(ownerRequestDto.getFirstName());
        owner.setLastName(ownerRequestDto.getLastName());
        return owner;
    }

    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(owner.getId());
        ownerResponseDto.setFirstName(owner.getFirstName());
        ownerResponseDto.setLastName(owner.getLastName());
        ownerResponseDto.setCars(owner.getCars().stream()
                .map(carMapper::mapToDto)
                .collect(Collectors.toList()));
        ownerResponseDto.setOrders(owner.getOrders().stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList()));
        return ownerResponseDto;
    }
}
