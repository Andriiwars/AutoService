package autoservice.service.mapper;

import autoservice.dto.request.RepairmanRequestDto;
import autoservice.dto.response.RepairmanResponseDto;
import autoservice.model.Repairman;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RepairmanMapper {
    private final OrderMapper orderMapper;

    public RepairmanMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public Repairman mapToModel(RepairmanRequestDto repairmanRequestDto) {
        Repairman repairman = new Repairman();
        repairman.setFirstName(repairmanRequestDto.getFirstName());
        repairman.setLastName(repairmanRequestDto.getLastName());
        return repairman;
    }

    public RepairmanResponseDto mapToDto(Repairman repairman) {
        RepairmanResponseDto repairmanResponseDto = new RepairmanResponseDto();
        repairmanResponseDto.setId(repairman.getId());
        repairmanResponseDto.setFirstName(repairman.getFirstName());
        repairmanResponseDto.setLastName(repairman.getLastName());
        repairmanResponseDto.setCompletedOrders(repairman.getCompletedOrders().stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList()));
        return repairmanResponseDto;
    }
}
