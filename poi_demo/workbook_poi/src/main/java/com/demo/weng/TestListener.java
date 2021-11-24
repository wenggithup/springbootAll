package com.demo.weng;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @DATE: 2021/11/16 3:30 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class TestListener extends AnalysisEventListener {
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        System.out.println(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
