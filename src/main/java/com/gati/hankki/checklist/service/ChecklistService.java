package com.gati.hankki.checklist.service;

import com.gati.hankki.checklist.dto.ChecklistRegisterRequest;
import com.gati.hankki.checklist.dto.ChecklistResponse;
import com.gati.hankki.checklist.dto.ChecklistUpdateRequest;
import com.gati.hankki.checklist.entity.Checklist;
import com.gati.hankki.checklist.mapper.ChecklistMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChecklistService {

    private final ChecklistMapper checklistMapper;

    public List<ChecklistResponse> findChecklist(Long memberNo) {
        return checklistMapper.findChecklists(memberNo);
    }

    @Transactional
    public void registerChecklist(ChecklistRegisterRequest request) {
        Checklist checklist = Checklist.builder()
                .memberNo(request.memberNo())
                .checklistContent(request.checklistContent())
                .createdId("hankki")
                .createdAt(LocalDateTime.now())
                .build();

        checklistMapper.insertChecklist(checklist);
    }

    @Transactional
    public void updateChecklist(ChecklistUpdateRequest request) {
        Checklist checklist = Checklist.builder()
                .checkNo(request.checkNo())
                .checklistContent(request.checklistContent())
                .updatedId("hankki")
                .updatedAt(LocalDateTime.now())
                .build();

        checklistMapper.updateChecklist(checklist);
    }

    @Transactional
    public void deleteChecklist(Long checkNo) {
        checklistMapper.deleteChecklist(checkNo);
    }

}
