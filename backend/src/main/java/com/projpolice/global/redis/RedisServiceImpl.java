// package com.projpolice.global.redis;
//
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import java.util.Set;
//
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.RequestParam;
//
// import com.projpolice.domain.epic.domain.redis.EpicDetailRedisData;
// import com.projpolice.domain.epic.domain.redis.EpicEssentialRedisData;
// import com.projpolice.domain.epic.domain.redis.ProjectEpicWithRangeRedisItem;
// import com.projpolice.domain.epic.domain.redis.ProjectEssentialRedisItem;
// import com.projpolice.domain.epic.dto.EpicDetailData;
// import com.projpolice.domain.epic.dto.EpicProjectedItem;
// import com.projpolice.domain.epic.dto.TaskProjectedItem;
// import com.projpolice.domain.epic.repository.redis.EpicDetailRedisRepository;
// import com.projpolice.domain.epic.repository.redis.EpicEssentialRedisRepository;
// import com.projpolice.domain.epic.repository.redis.ProjectEpicWithRangeRedisRepository;
// import com.projpolice.domain.epic.repository.redis.ProjectEssentialRedisRepository;
// import com.projpolice.domain.epic.service.EpicRedisInterface;
// import com.projpolice.domain.project.domain.redis.ProjectDetailRedisData;
// import com.projpolice.domain.project.domain.redis.ProjectUserIdNameImgListRedisData;
// import com.projpolice.domain.project.dto.ProjectDetailData;
// import com.projpolice.domain.project.repository.redis.ProjectDetailDataRedisRepository;
// import com.projpolice.domain.project.repository.redis.ProjectUserIdNameImgListRedisRepository;
// import com.projpolice.domain.project.service.ProjectRedisInterface;
// import com.projpolice.domain.task.domain.redis.TaskEssentialRedisData;
// import com.projpolice.domain.task.domain.redis.UserTaskRedisData;
// import com.projpolice.domain.task.dto.TaskRelatedProjectionData;
// import com.projpolice.domain.task.repository.redis.TaskEssentialRepository;
// import com.projpolice.domain.task.repository.redis.UserTaskRedisRepository;
// import com.projpolice.domain.task.service.TaskRedisInterface;
// import com.projpolice.domain.user.domain.redis.UserRedisData;
// import com.projpolice.domain.user.dto.UserIdNameImgItem;
// import com.projpolice.domain.user.repository.redis.UserDataRedisRepository;
// import com.projpolice.domain.user.service.UserRedisInterface;
//
// import lombok.RequiredArgsConstructor;
//
// @Service
// @RequiredArgsConstructor
// public class RedisServiceImpl implements RedisService {
//     private final ProjectDetailDataRedisRepository projectDetailDataRedisRepository;
//     private final ProjectUserIdNameImgListRedisRepository projectUserIdNameImgListRedisRepository;
//     private final EpicDetailRedisRepository epicDetailRedisRepository;
//     private final ProjectEssentialRedisRepository projectEssentialRedisRepository;
//     private final ProjectEpicWithRangeRedisRepository projectEpicWithRangeRedisRepository;
//     private final UserTaskRedisRepository userTaskRedisRepository;
//     private final UserDataRedisRepository userDataRedisRepository;
//     private final TaskEssentialRepository taskEssentialRepository;
//     private final EpicEssentialRedisRepository epicEssentialRedisRepository;
//
//     @Override
//     public Optional<ProjectDetailData> findProjectDetailById(long id) {
//         Optional<ProjectDetailRedisData> cache = projectDetailDataRedisRepository.findById(id);
//         return cache.map(ProjectDetailRedisData::of);
//     }
//
//     @Override
//     public void saveProjectDetail(ProjectDetailData data) {
//         projectDetailDataRedisRepository.save(new ProjectDetailRedisData(data));
//     }
//
//     @Override
//     public Optional<List<UserIdNameImgItem>> findProjectUserIdNameImgById(long id) {
//         Optional<ProjectUserIdNameImgListRedisData> cache = projectUserIdNameImgListRedisRepository.findById(id);
//         if (cache.isEmpty()) {
//             return Optional.empty();
//         }
//         return cache.map(ProjectUserIdNameImgListRedisData::getList);
//     }
//
//     @Override
//     public void saveProjectUserIdNameImgList(long id, List<UserIdNameImgItem> list) {
//         projectUserIdNameImgListRedisRepository.save(new ProjectUserIdNameImgListRedisData(id, list));
//     }
//
//     @Override
//     public void invalidateProjectUser(long id) {
//         projectUserIdNameImgListRedisRepository.deleteById(id);
//     }
//
//     @Override
//     public Optional<EpicDetailData> findEpicDetailById(long epicId) {
//         Optional<EpicDetailRedisData> cache = epicDetailRedisRepository.findById(epicId);
//         return cache.map(EpicDetailRedisData::of);
//     }
//
//     @Override
//     public void saveEpicDetail(EpicDetailData data) {
//         epicDetailRedisRepository.save(new EpicDetailRedisData(data));
//         Optional<ProjectEssentialRedisItem> projectRedisCache = projectEssentialRedisRepository.findById(
//             data.getProjectId());
//         ProjectEssentialRedisItem project = projectRedisCache.orElseGet(
//             () -> new ProjectEssentialRedisItem(data.getProjectId()));
//         project.getEpicIds().add(data.getId());
//         projectEssentialRedisRepository.save(project);
//     }
//
//     @Override
//     public Optional<List<EpicProjectedItem>> selectProjectEpicsWithDateRange(long projectId, LocalDate startDate,
//         LocalDate endDate) {
//         String id = ProjectEpicWithRangeRedisItem.id(projectId, startDate, endDate);
//         Optional<ProjectEpicWithRangeRedisItem> cache = projectEpicWithRangeRedisRepository.findById(id);
//         return cache.map(ProjectEpicWithRangeRedisItem::getList);
//     }
//
//     @Override
//     public void saveProjectEpicsWithDateRange(long projectId, LocalDate startDate, LocalDate endDate,
//         List<EpicProjectedItem> list) {
//         Optional<ProjectEssentialRedisItem> projectRedisCache = projectEssentialRedisRepository.findById(projectId);
//         String formattedId = ProjectEpicWithRangeRedisItem.id(projectId, startDate, endDate);
//
//         ProjectEssentialRedisItem project = projectRedisCache.orElseGet(() -> new ProjectEssentialRedisItem(projectId));
//         project.getFormattedEpicProjectionId().add(formattedId);
//
//         // for de-referencing
//         List<EpicEssentialRedisData> epicEssentialList = new ArrayList<>(list.size());
//         List<TaskEssentialRedisData> taskEssentialList = new ArrayList<>(
//             list.stream().map(EpicProjectedItem::getTasks).map(List::size).reduce(0, Integer::sum)
//         );
//         for (EpicProjectedItem epicProjection : list) {
//             long epicId = epicProjection.getId();
//             project.getEpicIds().add(epicId);
//             epicEssentialList.add(new EpicEssentialRedisData(epicId, projectId));
//             for (TaskProjectedItem taskProjection : epicProjection.getTasks()) {
//                 long taskId = taskProjection.getId();
//                 taskEssentialList.add(
//                     new TaskEssentialRedisData(taskId, epicId, projectId, taskProjection.getUserId()));
//                 project.getTaskIds().add(taskId);
//             }
//         }
//         taskEssentialRepository.saveAll(taskEssentialList);
//         epicEssentialRedisRepository.saveAll(epicEssentialList);
//         projectEssentialRedisRepository.save(project);
//         projectEpicWithRangeRedisRepository.save(
//             new ProjectEpicWithRangeRedisItem(projectId, startDate, endDate, list));
//     }
//
//     @Override
//     public Optional<List<TaskRelatedProjectionData>> selectUserProjectsWithDateRange(long userId, LocalDate startDate,
//         LocalDate endDate) {
//         String id = UserTaskRedisData.id(userId, startDate, endDate);
//         return userTaskRedisRepository.findById(id).map(UserTaskRedisData::getTask);
//     }
//
//     @Override
//     public void saveUserProjectsWithDateRange(long userId, LocalDate startDate, LocalDate endDate,
//         List<TaskRelatedProjectionData> tasks) {
//         UserTaskRedisData task = new UserTaskRedisData(userId, startDate, endDate, tasks);
//         userTaskRedisRepository.save(task);
//         Map<Long, Long> epicToProject = new HashMap<>();
//         Map<Long, List<Long>> projectTasks = new HashMap<>();
//         Map<Long, List<Long>> epicTasks = new HashMap<>();
//         for (TaskRelatedProjectionData taskProjection : tasks) {
//             long taskId = taskProjection.getId();
//             long projectId = taskProjection.getProject().getId();
//             projectTasks.putIfAbsent(projectId, new ArrayList<>());
//             projectTasks.get(projectId).add(taskId);
//             long epicId = taskProjection.getEpic().getId();
//             epicTasks.putIfAbsent(epicId, new ArrayList<>());
//             epicTasks.get(epicId).add(taskId);
//             epicToProject.put(epicId, projectId);
//         }
//
//         for (Map.Entry<Long, List<Long>> projectTask : projectTasks.entrySet()) {
//             long projectId = projectTask.getKey();
//             ProjectEssentialRedisItem projectEssential = projectEssentialRedisRepository
//                 .findById(projectId).orElse(new ProjectEssentialRedisItem(projectId));
//             Set<Long> taskIdSet = new HashSet<>(projectEssential.getTaskIds());
//             taskIdSet.addAll(projectTask.getValue());
//             projectEssential.getTaskIds().clear();
//             projectEssential.getTaskIds().addAll(taskIdSet);
//             projectEssentialRedisRepository.save(projectEssential);
//         }
//
//         for (Map.Entry<Long, List<Long>> epicTask : epicTasks.entrySet()) {
//             long epicId = epicTask.getKey();
//             long projectId = epicToProject.get(epicId);
//             ProjectEssentialRedisItem projectEssential = projectEssentialRedisRepository
//                 .findById(projectId).orElse(new ProjectEssentialRedisItem(projectId));
//             Set<Long> epicIdSet = new HashSet<>(projectEssential.getEpicIds());
//             epicIdSet.addAll(epicTask.getValue());
//             projectEssential.getEpicIds().clear();
//             projectEssential.getTaskIds().addAll(epicIdSet);
//             projectEssentialRedisRepository.save(projectEssential);
//         }
//
//         Optional<UserRedisData> userDataCandidate = userDataRedisRepository.findById(userId);
//         UserRedisData userData = userDataCandidate.orElseGet(() -> new UserRedisData(userId));
//         userData.getFormattedTaskId().add(task.getId());
//         userDataRedisRepository.save(userData);
//     }
//
//     @Override
//     public void invalidateProject(long projectId) {
//         projectDetailDataRedisRepository.deleteById(projectId);
//         projectUserIdNameImgListRedisRepository.deleteById(projectId);
//         Optional<ProjectEssentialRedisItem> projectRedisCache = projectEssentialRedisRepository.findById(projectId);
//         if (projectRedisCache.isEmpty()) {
//             return;
//         }
//         ProjectEssentialRedisItem project = projectRedisCache.get();
//         invalidateEpics(project.getEpicIds());
//         projectEpicWithRangeRedisRepository.deleteAllById(project.getFormattedEpicProjectionId());
//         projectEssentialRedisRepository.deleteById(projectId);
//     }
//
//     protected void invalidateEpics(Collection<Long> epicIds) {
//         epicDetailRedisRepository.deleteAllById(epicIds);
//     }
//
//     @Override
//     public void invalidateUser(long userId) {
//         Optional<UserRedisData> userDataCandidate = userDataRedisRepository.findById(userId);
//         if (userDataCandidate.isPresent()) {
//             UserRedisData userData = userDataCandidate.get();
//             userTaskRedisRepository.deleteAllById(userData.getFormattedTaskId());
//         }
//         userDataRedisRepository.deleteById(userId);
//     }
//
//     @Override
//     public void invalidateTask(long taskId) {
//         taskEssentialRepository.deleteById(taskId);
//         Optional<TaskEssentialRedisData> cache = taskEssentialRepository.findById(taskId);
//         if (cache.isEmpty()) {
//             return;
//         }
//         invalidateProject(cache.get().getProjectId());
//     }
//
//     protected void invalidateTasks(Collection<Long> taskIds) {
//         Iterable<TaskEssentialRedisData> tasks = taskEssentialRepository.findAllById(taskIds);
//         Set<Long> projectIdSet = new HashSet<>();
//         tasks.forEach(task -> projectIdSet.add(task.getProjectId()));
//         projectIdSet.forEach(this::invalidateProject);
//         taskEssentialRepository.deleteAllById(taskIds);
//     }
// }
