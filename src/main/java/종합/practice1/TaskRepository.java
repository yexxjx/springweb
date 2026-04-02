package example.practice1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
