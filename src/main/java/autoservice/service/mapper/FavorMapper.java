package autoservice.service.mapper;

import autoservice.dto.request.FavorRequestDto;
import autoservice.dto.response.FavorResponseDto;
import autoservice.model.Favor;
import autoservice.service.OrderService;
import autoservice.service.RepairmanService;
import org.springframework.stereotype.Component;

@Component
public class FavorMapper {
    private final OrderService orderService;
    private final RepairmanService repairmanService;

    public FavorMapper(OrderService orderService, RepairmanService repairmanService) {
        this.orderService = orderService;
        this.repairmanService = repairmanService;
    }

    public Favor mapToModel(FavorRequestDto favorRequestDto) {
        Favor favor = new Favor();
        favor.setFavorName(favorRequestDto.getFavorName());
        favor.setOrder(orderService.getOrderById(favorRequestDto.getOrderId()));
        favor.setRepairman(repairmanService.getRepairmanById(favorRequestDto.getRepairmanId()));
        favor.setPrice(favorRequestDto.getPrice());
        return favor;
    }

    public FavorResponseDto mapToDto(Favor favor) {
        FavorResponseDto favorResponseDto = new FavorResponseDto();
        favorResponseDto.setId(favor.getId());
        favorResponseDto.setFavorName(favor.getFavorName());
        favorResponseDto.setOrderId(favor.getOrder().getId());
        favorResponseDto.setPrice(favor.getPrice());
        favorResponseDto.setRepairmanId(favor.getRepairman().getId());
        favorResponseDto.setStatus(favor.getStatus().name());
        return favorResponseDto;
    }
}
