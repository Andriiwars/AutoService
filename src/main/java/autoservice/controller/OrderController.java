package autoservice.controller;

import autoservice.dto.request.FavorRequestDto;
import autoservice.dto.request.GoodsRequestDto;
import autoservice.dto.request.OrderRequestDto;
import autoservice.dto.response.OrderResponseDto;
import autoservice.model.Favor;
import autoservice.model.Order;
import autoservice.model.Owner;
import autoservice.service.CarService;
import autoservice.service.FavorService;
import autoservice.service.GoodsService;
import autoservice.service.OrderService;
import autoservice.service.OwnerService;
import autoservice.service.StatusService;
import autoservice.service.mapper.FavorMapper;
import autoservice.service.mapper.GoodsMapper;
import autoservice.service.mapper.OrderMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final GoodsMapper goodsMapper;
    private final OwnerService ownerService;
    private final CarService carService;
    private final FavorMapper favorMapper;
    private final FavorService favorService;
    private final GoodsService goodsService;
    private final StatusService statusService;

    public OrderController(OrderMapper orderMapper,
                           OrderService orderService,
                           GoodsMapper goodsMapper,
                           OwnerService ownerService,
                           CarService carService,
                           FavorMapper favorMapper,
                           FavorService favorService,
                           GoodsService goodsService,
                           StatusService statusService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.goodsMapper = goodsMapper;
        this.ownerService = ownerService;
        this.carService = carService;
        this.favorMapper = favorMapper;
        this.favorService = favorService;
        this.goodsService = goodsService;
        this.statusService = statusService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderMapper.mapToModel(orderRequestDto);
        order.setGoods(new ArrayList<>());
        order.setFavors(new ArrayList<>());
        order.setStatus(Order.Status.ACCEPTED);
        orderService.createOrder(order);
        Owner owner = carService.getCarById(orderRequestDto.getCarId()).getOwner();
        owner.getOrders().add(order);
        ownerService.createOwner(owner);
        return orderMapper.mapToDto(order);
    }

    @PostMapping("/{id}/goods")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto addGoods(@PathVariable Long id,
                                     @Valid @RequestBody GoodsRequestDto goodsRequestDto) {
        Order order = orderService.getOrderById(id);
        order.getGoods().add(goodsService.createGoods(goodsMapper.mapToModel(goodsRequestDto)));
        return orderMapper.mapToDto(orderService.createOrder(order));
    }

    @PostMapping("/{id}/favors")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto addFavor(@PathVariable Long id,
                                     @Valid @RequestBody FavorRequestDto favorRequestDto) {

        Order order = orderService.getOrderById(id);
        Favor favor = favorMapper.mapToModel(favorRequestDto);
        favor.setStatus(Favor.Status.NOT_PAID);
        order.getFavors().add(favorService.createFavor(favor));
        return orderMapper.mapToDto(orderService.createOrder(order));
    }

    @PutMapping("/{id}")
    public OrderResponseDto updateOrder(@PathVariable Long id,
                                      @Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderMapper.mapToModel(orderRequestDto);
        order.setId(id);
        return orderMapper.mapToDto(orderService.createOrder(order));
    }

    @PutMapping({"/{id}/status"})
    public OrderResponseDto updateOrderStatus(@PathVariable Long id,
                                              @RequestParam String newStatus) {
        return orderMapper
                .mapToDto(statusService
                        .changeOrderStatus(id, Order.Status.valueOf(newStatus)));
    }

    @GetMapping({"/{id}/price"})
        public BigDecimal getOrderPrice(@PathVariable Long id, @RequestParam Integer bonus) {
        Order order = orderService.getOrderById(id);
        BigDecimal sum = orderService.getFavorsPrice(id, bonus)
                .add(orderService.getGoodsPrice(id, bonus));
        order.setFinalPrice(sum);
        orderService.createOrder(order);
        return sum;
    }
}
