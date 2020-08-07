package com.example.mode.mode_builderpattern;

public abstract  class Builder {
    public abstract void BuildCPU();
    public abstract void BuildMainboard();
    public abstract void BuildHD();
    public abstract Computer getComputer();
}
