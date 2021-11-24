package com.demo.weng;




import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @DATE: 2021/11/16 2:35 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class DemoDataListener extends AnalysisEventListener<Map<Integer,String>> {

    private List<List<String>> list = null;

    DemoDataListener(){
        list = new ArrayList<>();
    }
    public List<List<String>> getList() {
        return list;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //处理表头

        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void invoke(Map<Integer, String> integerStringMap, AnalysisContext analysisContext) {
        List<String> list1 = new ArrayList<>();
        for (Map.Entry<Integer, String> integerStringEntry : integerStringMap.entrySet()) {
            list1.add(integerStringEntry.getValue());
        }
        list.add(list1);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
