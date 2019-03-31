package com.zlrx.actionmonitor.model;

import com.zlrx.actionmonitor.type.HealthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Health {
    private HealthType health;
}
