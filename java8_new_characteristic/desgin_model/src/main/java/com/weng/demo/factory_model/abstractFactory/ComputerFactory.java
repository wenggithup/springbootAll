package com.weng.demo.factory_model.abstractFactory;

/**
 * @DATE: 2022/8/31 4:28 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public abstract class ComputerFactory {

    public Computer getComputer(){
        return new Computer(createCpu(),createMainBoard(),createHardDisk());
    }

    abstract CPU createCpu();

    abstract  MainBoard createMainBoard();

    abstract HardDisk createHardDisk();
}
