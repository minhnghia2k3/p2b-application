package p2b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import p2b.entity.PermissionEntity;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    List<PermissionEntity> findByIsDefault(boolean isDefault);
}
