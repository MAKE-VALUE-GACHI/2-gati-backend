package com.gati.hankki.checklist.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "체크리스트 등록 요청 DTO")
public record ChecklistRegisterRequest(
        @Schema(description = "회원 번호", example = "1")
        Long memberNo,

        @Schema(description = "체크리스트 내용", example = "그릇 제공 유무")
        String checklistContent
) {
}
