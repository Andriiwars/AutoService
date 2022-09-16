package autoservice.service.mapper;

import autoservice.dto.request.GoodsRequestDto;
import autoservice.dto.response.GoodsResponseDto;
import autoservice.model.Goods;
import org.springframework.stereotype.Component;

@Component
public class GoodsMapper {
    public Goods mapToModel(GoodsRequestDto goodsRequestDto) {
        Goods goods = new Goods();
        goods.setName(goodsRequestDto.getName());
        goods.setPrice(goodsRequestDto.getPrice());
        return goods;
    }

    public GoodsResponseDto mapToDto(Goods goods) {
        GoodsResponseDto goodsResponseDto = new GoodsResponseDto();
        goodsResponseDto.setId(goods.getId());
        goodsResponseDto.setPrice(goods.getPrice());
        goodsResponseDto.setName(goods.getName());
        return goodsResponseDto;
    }
}
