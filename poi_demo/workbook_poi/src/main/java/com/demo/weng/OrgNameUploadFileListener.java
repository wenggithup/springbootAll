package com.demo.weng;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @DATE: 2021/11/17 4:44 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class OrgNameUploadFileListener extends AnalysisEventListener<Map<Integer,String>> {

    private List<List<String>> list;

    public List<List<String>> getList() {
        return list;
    }

    public OrgNameUploadFileListener() {
        this.list = new ArrayList<>();
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        List<String> headList = new ArrayList<>();
        //判断是否有数据
        if (headMap.size() > 0) {
            headList.add("NOT_NULL");
        } else {
            headList.add("NULL");
        }
        list.add(headList);
        List<String> headList1 = new ArrayList<>();
        for (Map.Entry<Integer, String> integerStringEntry : headMap.entrySet()) {

            if (null == integerStringEntry.getValue()){
                headList1.add("");
            }else {
                headList1.add(integerStringEntry.getValue());
            }

        }
        list.add(headList1);

        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void invoke(Map<Integer, String> integerStringMap, AnalysisContext analysisContext) {
        return;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
