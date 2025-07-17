package com.gati.hankki.checklist.controller;

import com.gati.hankki.checklist.dto.ChecklistRegisterRequest;
import com.gati.hankki.checklist.dto.ChecklistResponse;
import com.gati.hankki.checklist.dto.ChecklistUpdateRequest;
import com.gati.hankki.checklist.service.ChecklistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/checklists")
@RequiredArgsConstructor
@Tag(name = "Checklists", description = "Checklist API")
public class ChecklistController {

    private final ChecklistService checklistService;

    @GetMapping("/{memberNo}")
    @Operation(summary = "체크리스트 조회 API", description = "체크리스트 조회")
    public ResponseEntity<List<ChecklistResponse>> findChecklist(@PathVariable Long memberNo) {
        return ResponseEntity
                .ok(checklistService.findChecklist(memberNo));
    }

    @PostMapping
    @Operation(summary = "체크리스트 등록 API", description = "체크리스트 등록")
    public ResponseEntity<Void> registerChecklist(ChecklistRegisterRequest request) {
        checklistService.registerChecklist(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{checkNo}")
    @Operation(summary = "체크리스트 수정 API", description = "체크리스트 수정")
    public ResponseEntity<Void> updateChecklist(ChecklistUpdateRequest request) {
        checklistService.updateChecklist(request);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/{checkNo}")
    @Operation(summary = "체크리스트 삭제 API", description = "체크리스트 삭제")
    public ResponseEntity<Void> deleteChecklist(@PathVariable Long checkNo) {
        checklistService.deleteChecklist(checkNo);
        return ResponseEntity
                .noContent()
                .build();
    }

}
