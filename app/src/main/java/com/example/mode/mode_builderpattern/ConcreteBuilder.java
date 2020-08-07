package com.example.mode.mode_builderpattern;

/**
 * 创建具体的建造者（ConcreteBuilder）:装机人员
 */
public class ConcreteBuilder extends Builder {
    Computer computer = new Computer();
    @Override
    public void BuildCPU() {
        computer.add("BuildCPU");
    }

    @Override
    public void BuildMainboard() {
        computer.add("BuildMainboard");
    }

    @Override
    public void BuildHD() {
        computer.add("BuildHD");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
