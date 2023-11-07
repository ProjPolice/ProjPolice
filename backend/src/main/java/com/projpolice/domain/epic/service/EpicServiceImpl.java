package com.projpolice.domain.epic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projpolice.domain.epic.domain.Epic;
import com.projpolice.domain.epic.dto.EpicDetailData;
import com.projpolice.domain.epic.dto.EpicProjectedItem;
import com.projpolice.domain.epic.dto.EpicProjectionDataItem;
import com.projpolice.domain.epic.dto.TaskProjectedItem;
import com.projpolice.domain.epic.repository.EpicRepository;
import com.projpolice.domain.epic.request.EpicCreateRequest;
import com.projpolice.domain.epic.request.EpicUpdateRequest;
import com.projpolice.domain.project.domain.rdb.Project;
import com.projpolice.domain.project.repository.rdb.ProjectRepository;
import com.projpolice.domain.task.repository.TaskRepository;
import com.projpolice.global.common.base.BaseIdItem;
import com.projpolice.global.common.error.exception.EpicException;
import com.projpolice.global.common.error.exception.TaskException;
import com.projpolice.global.common.error.info.ExceptionInfo;
import com.projpolice.global.common.manager.ProjectAuthManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {
    private final EpicRepository epicRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ProjectAuthManager projectAuthManager;

    /**
     * 해당 프로젝트에 할일 생성하는 메소드
     *
     * @param epicCreateRequest
     * @return EpicDetailData
     */
    @Override
    @Transactional
    public EpicDetailData createEpic(EpicCreateRequest epicCreateRequest) {
        projectAuthManager.checkProjectMembershipOrThrow(epicCreateRequest.getProjectId());

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
        projectAuthManager.checkEpicMembershipOrThrow(id);

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
        projectAuthManager.checkEpicMembershipOrThrow(id);

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
        projectAuthManager.checkEpicMembershipOrThrow(id);

        taskRepository.deleteAllByEpicId(id);
        epicRepository.deleteById(id);
        return BaseIdItem.from(id);
    }

    /**
     * Selects project epics with a date range.
     *
     * @param project_id the ID of the project
     * @param startDate the start date of the date range (if null, defaults to one month ago)
     * @param endDate the end date of the date range (if null, defaults to one month from now)
     * @return a list of EpicProjectedItem objects representing the selected epics and their associated tasks
     */
    @Override
    public List<EpicProjectedItem> selectProjectEpicsWithDateRange(long project_id, LocalDate startDate,
        LocalDate endDate) {
        projectAuthManager.checkProjectMembershipOrThrow(project_id);
        if (startDate == null) {
            startDate = LocalDate.now().minusMonths(1);
        }
        if (endDate == null) {
            endDate = LocalDate.now().plusMonths(1);
        }
        Map<Long, EpicProjectedItem> map = new TreeMap<>();
        List<EpicProjectionDataItem> items = epicRepository.selectProjectEpicsWithDateRange(project_id,
            startDate, endDate);
        for (EpicProjectionDataItem item : items) {
            EpicProjectedItem epicItem = map.get(item.getEpicId());
            if (epicItem == null) {
                epicItem = EpicProjectedItem.builder()
                    .id(item.getEpicId())
                    .name(item.getEpicName())
                    .startDate(item.getEpicStartDate())
                    .endDate(item.getEpicEndDate())
                    .build();
                map.put(item.getEpicId(), epicItem);
            }
            TaskProjectedItem taskItem = TaskProjectedItem.builder()
                .id(item.getTaskId())
                .name(item.getTaskName())
                .startDate(item.getTaskStartDate())
                .endDate(item.getTaskEndDate())
                .build();
            epicItem.getTasks().add(taskItem);
        }

        List<EpicProjectedItem> result = new ArrayList<>(map.size());
        result.addAll(map.values());

        return result;
    }
}
