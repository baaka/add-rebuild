package com.example.addrebuild.api;

import com.example.addrebuild.AppConstants;
import com.example.addrebuild.domain.AppForm;
import com.example.addrebuild.exception.AppException;
import com.example.addrebuild.service.AppFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.APP_REST_CONTEXT_PATH + "/appForm")
@RequiredArgsConstructor
public class AppFormRestController {
    private final AppFormService appFormService;

    @GetMapping
    public List<AppForm> getAppForms() {
        return appFormService.getAppForms();
    }

    @PostMapping
    public AppForm addAppForm(AppForm appForm) {
        return appFormService.addAppForm(appForm);
    }

    @PutMapping
    public AppForm updateAppForm(AppForm appForm) throws AppException {
        return appFormService.updateAppForm(appForm);
    }

    @DeleteMapping("/{appFormId}")
    public void deleteAppForm(@PathVariable long appFormId) {
        appFormService.deleteAppForm(appFormId);
    }
}
