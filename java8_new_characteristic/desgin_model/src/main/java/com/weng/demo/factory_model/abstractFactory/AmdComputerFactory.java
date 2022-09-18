package com.weng.demo.factory_model.abstractFactory;

/**
 * @DATE: 2022/8/31 4:38 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class AmdComputerFactory extends ComputerFactory{
    @Override
    CPU createCpu() {
        CPU cpu = new CPU();
        cpu.setCpuName("amd cpu");
        return cpu;
    }

    @Override
    MainBoard createMainBoard() {
        MainBoard mainBoard = new MainBoard();
        mainBoard.setMainBoardName("amd mainBoard");
        return mainBoard;
    }

    @Override
    HardDisk createHardDisk() {
        HardDisk hardDisk = new HardDisk();
        hardDisk.setHardDishName("amd cpu");
        return hardDisk;
    }
}
