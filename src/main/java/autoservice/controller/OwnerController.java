package autoservice.controller;

import autoservice.dto.request.OwnerRequestDto;
import autoservice.dto.response.OrderResponseDto;
import autoservice.dto.response.OwnerResponseDto;
import autoservice.model.Owner;
import autoservice.service.OwnerService;
import autoservice.service.mapper.OrderMapper;
import autoservice.service.mapper.OwnerMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OrderMapper orderMapper;
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    public OwnerController(OrderMapper orderMapper,
                           OwnerService ownerService,
                           OwnerMapper ownerMapper) {
        this.orderMapper = orderMapper;
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping("/{id}/orders")
    public List<OrderResponseDto> getAllOrdersById(@PathVariable Long id) {
        return ownerService.getOwnerById(id).getOrders().stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponseDto createOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        Owner owner = ownerMapper.mapToModel(ownerRequestDto);
        owner.setCars(new ArrayList<>());
        owner.setOrders(new ArrayList<>());
        return ownerMapper.mapToDto(ownerService.createOwner(owner));
    }

    @PutMapping("/{id}")
    public OwnerResponseDto updateOwner(@PathVariable Long id,
                                      @Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        Owner owner = ownerMapper.mapToModel(ownerRequestDto);
        owner.setId(id);
        return ownerMapper.mapToDto(ownerService.createOwner(owner));
    }
}
