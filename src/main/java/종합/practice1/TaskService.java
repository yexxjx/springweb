package 종합.practice1;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    // 등록
    public TaskDto create(TaskDto taskDto){
        return taskRepository.save(taskDto.toEntity()).toDto();
    }

    // 조회
    public List<TaskDto> read(){
        return taskRepository.findAll().stream().map(TaskEntity::toDto).collect(Collectors.toList());
    }

    // 상세 조회
    public TaskDto detail(Long id){
        TaskEntity taskEntity=taskRepository.findById(id).orElseThrow();
        return taskEntity.toDto();
    }

    // 수정
    @Transactional
    public TaskDto update(Long id, TaskDto request){
        TaskEntity taskEntity=taskRepository.findById(id).orElseThrow();
        taskEntity.setTitle(request.getTitle());
        taskEntity.setContent(request.getContent());
        taskEntity.setStatus(request.getStatus());
        return taskEntity.toDto();
    }

    // 삭제
    public void delete(Long id){
        TaskEntity task=taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
    }

}
