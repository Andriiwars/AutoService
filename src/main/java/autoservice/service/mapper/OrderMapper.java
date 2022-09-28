package autoservice.service.mapper;

import autoservice.dto.request.OrderRequestDto;
import autoservice.dto.response.OrderResponseDto;
import autoservice.model.Order;
import autoservice.service.CarService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final CarService carService;
    private final GoodsMapper goodsMapper;
    private final FavorMapper favorMapper;

    public OrderMapper(CarService carService,
                       GoodsMapper goodsMapper,
                       FavorMapper favorMapper) {
        this.carService = carService;
        this.goodsMapper = goodsMapper;
        this.favorMapper = favorMapper;
    }

    public Order mapToModel(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setCar(carService.getCarById(orderRequestDto.getCarId()));
        order.setAcceptanceDate(orderRequestDto.getAcceptanceDate());
        order.setProblemDescription(orderRequestDto.getProblemDescription());
        return order;
    }

    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setCarId(order.getCar().getId());
        orderResponseDto.setGoods(order.getGoods().stream()
                .map(goodsMapper::mapToDto)
                .collect(Collectors.toList()));
        orderResponseDto.setFavors(order.getFavors().stream()
                .map(favorMapper::mapToDto)
                .collect(Collectors.toList()));
        orderResponseDto.setAcceptanceDate(order.getAcceptanceDate());
        orderResponseDto.setFinalPrice(order.getFinalPrice());
        orderResponseDto.setEndDate(order.getEndDate());
        orderResponseDto.setProblemDescription(order.getProblemDescription());
        orderResponseDto.setStatus(order.getStatus().name());
        return orderResponseDto;
    }
}
