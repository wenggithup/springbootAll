package com.weng.demo.factory_model.abstractFactory;

import lombok.AllArgsConstructor;

/**
 * @DATE: 2022/8/31 4:31 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class Computer {
    private CPU cpu;

    private MainBoard mainBoard;

    private HardDisk hardDisk;

    public Computer(CPU cpu, MainBoard mainBoard, HardDisk hardDisk) {
        this.cpu = cpu;
        this.mainBoard = mainBoard;
        this.hardDisk = hardDisk;
        System.out.println("computer in creating.......");
        System.out.println("cpu is "+cpu.getCpuName());
        System.out.println("mainBoard is "+mainBoard.getMainBoardName());
        System.out.println("hardDisk is "+hardDisk.getHardDishName());
    }
}
