package com.gati.hankki.checklist.dto;

public record ChecklistUpdateRequest(
        Long checkNo,
        String checklistContent
) {
}
