package com.cy;

import com.knziha.plod.dictionary.mdict;

import java.io.IOException;

public class Mdict extends mdict {

    public Mdict(String fn, String cssPathName) throws IOException {
        super(fn);
        this.cssPathName = cssPathName;
    }

    private String cssPathName;

    public String getCssPathName() {
        return cssPathName;
    }

    public Mdict setCssPathName(String cssPathName) {
        this.cssPathName = cssPathName;
        return this;
    }
}
