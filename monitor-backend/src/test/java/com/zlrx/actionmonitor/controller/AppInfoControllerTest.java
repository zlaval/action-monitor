package com.zlrx.actionmonitor.controller;

import com.zlrx.actionmonitor.model.ApplicationInfo;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static junit.framework.TestCase.assertEquals;

public class AppInfoControllerTest {

    @Test
    public void getApplicationInfoReturnOkHttpRespCode() {
        //given
        AppInfoController appInfoController = new AppInfoController();

        //when
        ResponseEntity<ApplicationInfo> result = appInfoController.getApplicationInfo();

        //then
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
