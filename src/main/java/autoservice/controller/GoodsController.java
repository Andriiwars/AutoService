package autoservice.controller;

import autoservice.dto.request.GoodsRequestDto;
import autoservice.dto.response.GoodsResponseDto;
import autoservice.model.Goods;
import autoservice.service.GoodsService;
import autoservice.service.mapper.GoodsMapper;
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
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final GoodsMapper goodsMapper;

    public GoodsController(GoodsService goodsService, GoodsMapper goodsMapper) {
        this.goodsService = goodsService;
        this.goodsMapper = goodsMapper;
    }

    @PutMapping("/{id}")
    public GoodsResponseDto updateGoods(@PathVariable Long id,
                                       @Valid @RequestBody GoodsRequestDto goodsRequestDto) {
        Goods goods = goodsMapper.mapToModel(goodsRequestDto);
        goods.setId(id);
        return goodsMapper.mapToDto(goodsService.createGoods(goods));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoodsResponseDto createGoods(@Valid @RequestBody GoodsRequestDto goodsRequestDto) {
        return goodsMapper.mapToDto(goodsService.createGoods(
                goodsMapper.mapToModel(goodsRequestDto)));
    }
}
