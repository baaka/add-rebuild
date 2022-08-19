package com.example.addrebuild.repo;

import com.example.addrebuild.domain.AppForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppFormRepository extends JpaRepository<AppForm, Long> {
}
