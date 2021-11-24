package com.demo.weng;

import com.alibaba.excel.EasyExcel;

import com.alibaba.excel.util.CollectionUtils;
import org.ehcache.core.util.CollectionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @DATE: 2021/11/12 4:56 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class test {
    private final String fileName = "/Users/wengchuanjie/Desktop/test.xlsx";
/*
    @PostMapping("/import")
    public String importFile(@RequestParam(value = "file", required = false)MultipartFile file) throws Exception {

        InputStream inputStream = null;
        if (null != file) {
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                return "获取流失败！！！";
            }
        }
        //从本地读取文件
        if (null == inputStream){
            FileInputStream fileInputStream  = new FileInputStream("/Users/wengchuanjie/Desktop/org_data_mini.xlsx");
            inputStream = fileInputStream;
        }
        Workbook wb=new HSSFWorkbook();
        wb.setForceFormulaRecalculation(true);

        try {
            wb = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            return "获取流数据失败";
        }
        inputStream.close();
        //获取第0sheet页
        Sheet sheet = wb.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();

        List<String> list = new ArrayList<>();
        for (int i = 0; i <=lastRowNum; i++) {
            //第i行
            Row row = sheet.getRow(i);
            //第0列
            Cell cell = row.getCell(0);
            Cell cell1 = row.getCell(1);
            cell.setCellType(CellType.STRING);
            cell1.setCellType(CellType.STRING);
            String lie0 = cell.getStringCellValue();
            String lie1 = cell1.getStringCellValue();
            list.add(lie0);list.add(lie1);
        }
        return list.toString();
    }*/

    @GetMapping("/easyImport")
    public String easyImport(){
        // DemoDataListener listener = new DemoDataListener();
        //TestListener listener = new TestListener();
        //ExcelReaderBuilder read = EasyExcel.read(fileName,listener);
        OrgNameUploadFileListener listener = new OrgNameUploadFileListener();
        EasyExcel.read(fileName)
                .registerReadListener(listener)
                .sheet()
                .headRowNumber(1)
                .doRead();

        System.out.println(listener.getList());
        List<List<String>> list = listener.getList();
        System.out.println(list.size());
        System.out.println(list.get(1).toString());
        //System.out.println(listener.getList());
        return "success";
    }

    @GetMapping("/test/setOrgIds")
    public String setOrgIds(){
        List<OrganizationBean> list = new ArrayList<>();
        OrganizationBean bean1 = new OrganizationBean(1,0);
        OrganizationBean bean2 = new OrganizationBean(2,0);
        OrganizationBean bean3 = new OrganizationBean(3,1);
        OrganizationBean bean4 = new OrganizationBean(4,1);
        OrganizationBean bean5 = new OrganizationBean(5,2);
        OrganizationBean bean6 = new OrganizationBean(6,3);
        OrganizationBean bean7 = new OrganizationBean(7,3);
        OrganizationBean bean8 = new OrganizationBean(8,6);
        list.add(bean1);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);
        Map<Long, OrganizationBean> dbMapID = new HashMap<>();
        dbMapID.put(2l,bean2);
        Map<Long, OrganizationBean> inputMapID = new HashMap<>();
        inputMapID.put(1l,bean1);
        inputMapID.put(3l,bean3);
        inputMapID.put(4l,bean4);
        inputMapID.put(5l,bean5);
        inputMapID.put(6l,bean6);
        inputMapID.put(7l,bean7);
        inputMapID.put(8l,bean8);


        Map<Long, List<Long>> parentIdMap = new HashMap<>();
        parentIdMap.put(1l,new ArrayList<>(Arrays.asList(3l,4l)));
        parentIdMap.put(3l,new ArrayList<>(Arrays.asList(6l,7l)));
        parentIdMap.put(6l,new ArrayList<>(Arrays.asList(8l)));

        Map<Long, List<Long>> parentIddbMap = new HashMap<>();
        parentIddbMap.put(2l,new ArrayList<>(Arrays.asList(5l)));
        setOrgIds(list,dbMapID,inputMapID,parentIdMap , parentIddbMap);
       list.stream().forEach(t-> System.out.println(t.toString()));
        return "success";
    }


    /**
     * 设置orgIds，用分隔符隔开
     * @param newData 需要导入的数据
     * @param dbMapID 数据库map，k：id，v对象
     * @param inputMapID 导入数据map，k ：id，v：对象
     * @param inputMapPID
     * @param
     */
    private void setOrgIds(List<OrganizationBean> newData, Map<Long, OrganizationBean> dbMapID, Map<Long, OrganizationBean> inputMapID,
                           Map<Long, List<Long>> inputMapPID, Map<Long, List<Long>> dbMapPID) {

            for (OrganizationBean organizationBean : newData) {
                StringBuilder sb = new StringBuilder();
                //当前组织机构id
                long orgID = organizationBean.getOrgID();
                //父类id
                long parentOrgID = organizationBean.getParentOrgID();

                String allParentStr =  getAllParent(parentOrgID,dbMapID,inputMapID,sb);

                System.out.println(orgID+","+allParentStr);
                organizationBean.setName(allParentStr);

        }


            for (OrganizationBean morg : newData) {
                long pid = morg.getParentOrgID();
                List<Long> orgIds = new ArrayList<>();
                //父类对象
                OrganizationBean pmorg = dbMapID.get(pid);
                // 父组织机构
                String pOrgCode = "";
                if (null != pmorg) {
                    pOrgCode = pmorg.getOrgCode();
                    if (pOrgCode == null) {
                        pOrgCode = "";
                    }

                    //如果存在父类
                    if (!CollectionUtils.isEmpty(pmorg.getOrgIds())){
                         orgIds.addAll(pmorg.getOrgIds());
                    }else {
                        orgIds.add(pmorg.getOrgID());
                    }
                }
                Set<Long> ids = new HashSet<>();
                //查看导入父类id下所有的子节点id
                List<Long> inputIds = inputMapPID.get(pid);
                if (null != inputIds) {
                    ids.addAll(inputIds);
                }
                //查看数据库父类id下所有的子节点id
                List<Long> dbIds = dbMapPID.get(pid);
                if (null != dbIds) {
                    ids.addAll(dbIds);
                } else {
                    dbIds = new ArrayList<>();
                }

                String maxOrgCode = "";
                for (Long id : ids) {
                    OrganizationBean org = dbMapID.get(id);
                    if (null != org) {
                        String _orgCodeString = org.getOrgCode();
                        if (_orgCodeString != null
                                && maxOrgCode.compareTo(_orgCodeString) < 0) {
                            maxOrgCode = _orgCodeString;
                        }
                    }
                }
                if (maxOrgCode.equals("")) {
                    maxOrgCode = "1000";
                } else {
                    maxOrgCode = maxOrgCode.substring(pOrgCode.length());
                }
                orgIds.add(morg.getOrgID());
                int moc = Integer.parseInt(maxOrgCode);
                moc++;
                morg.setOrgCode(pOrgCode + moc);
                morg.setOrgIds(orgIds);
                dbMapID.put(morg.getOrgID(), morg);
                dbIds.add(morg.getOrgID());
                dbMapPID.put(morg.getParentOrgID(), dbIds);

            }


    }

    /**
     * 递归获取所有的父类
     * @param parentOrgID
     * @param dbMapID
     * @param inputMapID
     * @param sb
     * @return
     */
    private String getAllParent(long parentOrgID, Map<Long, OrganizationBean> dbMapID, Map<Long, OrganizationBean> inputMapID, StringBuilder sb) {
        //查询数据库
        OrganizationBean organizationBean = dbMapID.get(parentOrgID);
        if (null != organizationBean){
            sb.append(organizationBean.getOrgID()+",");
            long dbParentId = organizationBean.getParentOrgID();
            if (!Integer.valueOf(String.valueOf(dbParentId)).equals(0)){
                getAllParent(dbParentId,dbMapID,inputMapID,sb);
            }
        }
        OrganizationBean bean = inputMapID.get(parentOrgID);
        if (null != bean){
            sb.append(bean.getOrgID()+",");
            long inputParentId = bean.getParentOrgID();
            if (!Integer.valueOf(String.valueOf(inputParentId)).equals(0)){
                getAllParent(inputParentId,dbMapID,inputMapID,sb);
            }else {
                sb.append("0");
            }
        }else {
            sb.append("0");
        }
        return sb.toString();

    }

}
