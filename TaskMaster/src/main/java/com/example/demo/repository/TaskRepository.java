package com.example.demo.repository;

import com.example.demo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
	List<TaskEntity> findByTaskAlanKullaniciId(Long taskAlanKullaniciId);
	List<TaskEntity> findByTaskAlanKullaniciIdAndTamamlandiFalse(Long taskAlanKullaniciId);
	List<TaskEntity> findByTaskAlanKullaniciIdAndTamamlandiTrue(Long userId);
}
