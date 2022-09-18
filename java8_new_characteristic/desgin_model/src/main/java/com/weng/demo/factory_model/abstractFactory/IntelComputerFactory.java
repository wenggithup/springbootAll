package com.weng.demo.factory_model.abstractFactory;

/**
 * @DATE: 2022/8/31 4:37 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class IntelComputerFactory extends ComputerFactory{
    @Override
    CPU createCpu() {
        CPU cpu = new CPU();
        cpu.setCpuName("intel cpu");
        return cpu;
    }

    @Override
    MainBoard createMainBoard() {
        MainBoard mainBoard = new MainBoard();
        mainBoard.setMainBoardName("intel mainBoard");
        return mainBoard;
    }

    @Override
    HardDisk createHardDisk() {
        HardDisk hardDisk = new HardDisk();
        hardDisk.setHardDishName("hardDisk cpu");
        return hardDisk;
    }
}
