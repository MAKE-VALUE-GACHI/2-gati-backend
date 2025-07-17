package com.gati.hankki.checklist.mapper;

import com.gati.hankki.checklist.dto.ChecklistResponse;
import com.gati.hankki.checklist.entity.Checklist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChecklistMapper {
    List<ChecklistResponse> findChecklists(Long memberNo);

    void insertChecklist(Checklist checklist);

    void updateChecklist(Checklist checklist);

    void deleteChecklist(Long checkNo);

}
