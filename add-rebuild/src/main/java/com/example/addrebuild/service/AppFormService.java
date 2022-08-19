package com.example.addrebuild.service;

import com.example.addrebuild.domain.AppForm;
import com.example.addrebuild.exception.AppException;
import com.example.addrebuild.repo.AppFormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = AppException.class)
public class AppFormService {
    private final AppFormRepository appFormRepository;

    public AppFormService(AppFormRepository appFormRepository) {
        this.appFormRepository = appFormRepository;
    }

    public List<AppForm> getAppForms() {
        List<AppForm> appForms = appFormRepository.findAll();
        return appForms;
    }

    public AppForm addAppForm(AppForm appForm) {
        appForm.setCreationTime(new Date());
        return appFormRepository.saveAndFlush(appForm);
    }

    public AppForm updateAppForm(AppForm appForm) throws AppException {
        if (appForm == null || !appFormRepository.existsById(appForm.getId())) {
            throw new AppException(AppException.Type.FIELD_REQUIRED);
        }

        return appFormRepository.saveAndFlush(appForm);
    }

    public void deleteAppForm(long appFormId) {
        appFormRepository.deleteById(appFormId);
    }

    public AppForm getAppFormById(long appFormId) {
        AppForm appForm = appFormRepository.findById(appFormId).orElse(null);
        return appForm;
    }

}
