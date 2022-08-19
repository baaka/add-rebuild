package com.app.rebuild.repo;

import com.app.rebuild.domain.AppForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppFormRepository extends JpaRepository<AppForm, Long> {
}
