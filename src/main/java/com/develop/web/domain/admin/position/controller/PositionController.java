package com.develop.web.domain.admin.position.controller;

import com.develop.web.domain.admin.position.dto.PosDto;
import com.develop.web.domain.admin.position.dto.PosDetailDto;
import com.develop.web.domain.admin.position.service.PosCreationService;
import com.develop.web.domain.admin.position.service.PosDeletionService;
import com.develop.web.domain.admin.position.service.PosListFetcherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "어드민 > 직위 관리", description = "직위 관리 api")
@RequiredArgsConstructor
@RequestMapping(value = "/s1/api/admin/pos")
public class PositionController {
    private final PosCreationService posCreationService;
    private final PosListFetcherService posListFetcherService;
    private final PosDeletionService posDeletionService;

    @PostMapping("/add")
    @Operation(summary = "직위", description = "직위 추가")
    public void positionAdd(@RequestBody PosDto posDto){
        posCreationService.addPos(posDto);
    }

    @DeleteMapping("/delete/{posId}")
    @Operation(summary = "직위 삭제", description = "삭제")
    public void positionRemove(@PathVariable Integer posId){
        posDeletionService.removePos(posId);
    }

    @GetMapping("/list")
    @Operation(summary = "직위 리스트", description = "직위 목록을 조회합니다.")
    public List<PosDetailDto> positionList(){
        return posListFetcherService.findPosList();
    }
}
