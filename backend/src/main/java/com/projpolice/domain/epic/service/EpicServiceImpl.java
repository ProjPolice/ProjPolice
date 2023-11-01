package com.projpolice.domain.epic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projpolice.domain.epic.domain.Epic;
import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.repository.EpicRepository;
import com.projpolice.domain.epic.request.EpicCreateRequest;
import com.projpolice.domain.epic.request.EpicUpdateRequest;
import com.projpolice.domain.project.domain.Project;
import com.projpolice.domain.project.repository.ProjectRepository;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.error.exception.EpicException;
import com.projpolice.global.common.error.exception.TaskException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {
    private final EpicRepository epicRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    /**
     * 해당 프로젝트에 할일 생성하는 메소드
     *
     * @param epicCreateRequest
     * @return EpicDetailData
     */
    @Override
    @Transactional
    public EpicDetailData createEpic(EpicCreateRequest epicCreateRequest) {
        // todo: 인증인가 추가 후 해당 프로젝트의 팀원인지 확인 필요
        Project project = projectRepository.findById(epicCreateRequest.getProjectId())
            .orElseThrow(() -> new TaskException(ExceptionInfo.INVALID_PROJECT));

        Epic epic = epicRepository.save(Epic.of(epicCreateRequest, project));
        return EpicDetailData.from(epic);
    }

    /**
     * 해당 할일 상셍 조회
     *
     * @param id
     * @return EpicDetailData
     */
    @Override
    @Transactional(readOnly = true)
    public EpicDetailData getEpic(Long id) {
        // todo: 인증인가 후 해당 epic의 프로젝트 멤버인지 확인 필요
        Epic epic = epicRepository.findById(id).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_EPIC));

        return EpicDetailData.from(epic);
    }

    /**
     * 할일 수정 기능
     *
     * @param id
     * @param epicUpdateRequest
     * @return 할일 상세 정보
     */
    @Override
    @Transactional
    public EpicDetailData updateEpic(Long id, EpicUpdateRequest epicUpdateRequest) {
        // todo: 인증인가 후 해당 epic의 프로젝트 멤버인지 확인 필요
        Epic epic = epicRepository.findById(id).orElseThrow(() -> new EpicException(ExceptionInfo.INVALID_EPIC));
        if (epicUpdateRequest.getName() != null) {
            epic.setName(epicUpdateRequest.getName());
        }
        if (epicUpdateRequest.getDescription() != null) {
            epic.setDescription(epicUpdateRequest.getDescription());
        }
        if (epicUpdateRequest.getStartDate() != null) {
            epic.setStartDate(epicUpdateRequest.getStartDate());
        }
        if (epicUpdateRequest.getEndDate() != null) {
            epic.setEndDate(epicUpdateRequest.getEndDate());
        }

        return EpicDetailData.from(epic);
    }

    /**
     * 할일 삭제 기능
     * @param id
     * @return 삭제한 id
     */
    @Override
    @Transactional
    public BaseIdItem deleteEpic(Long id) {
        taskRepository.deleteAllByEpicId(id);
        epicRepository.deleteById(id);
        return BaseIdItem.from(id);
    }
}
