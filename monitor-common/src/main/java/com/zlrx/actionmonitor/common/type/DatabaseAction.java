package com.zlrx.actionmonitor.common.type;

import lombok.Getter;

public enum DatabaseAction {
    INSERT(1), UPDATE(2), DELETE(4);

    @Getter
    private int type;

    DatabaseAction(int type) {
        this.type = type;
    }

}
