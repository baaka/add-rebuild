package com.app.rebuild.api;

import com.app.rebuild.AppConstants;
import com.app.rebuild.domain.AppForm;
import com.app.rebuild.service.AppFormService;
import com.app.rebuild.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(AppConstants.APP_REST_CONTEXT_PATH + "/appForm")
@RequiredArgsConstructor
public class AppFormController {
    private final AppFormService appFormService;

    @GetMapping
    public List<AppForm> getAppForms() {
        return appFormService.getAppForms();
    }

    @PostMapping
    public AppForm addAppForm(@RequestBody AppForm appForm) {
        return appFormService.addAppForm(appForm);
    }

    @PutMapping
    public AppForm updateAppForm(@RequestBody AppForm appForm) throws AppException {
        return appFormService.updateAppForm(appForm);
    }

    @DeleteMapping("/{appFormId}")
    public void deleteAppForm(@PathVariable long appFormId) {
        appFormService.deleteAppForm(appFormId);
    }
}
