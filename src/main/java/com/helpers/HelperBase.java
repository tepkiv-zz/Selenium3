package com.helpers;

import com.BrowserController;

public abstract class HelperBase {

    protected BrowserController manager;

    public HelperBase(BrowserController manager) {
        this.manager = manager;
    }
}