package com.zlrx.actionmonitor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationInfo {
    private String applicationBuildTime;
    private String applicationVersion;
}
