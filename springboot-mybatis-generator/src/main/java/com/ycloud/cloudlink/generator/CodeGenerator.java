package com.ycloud.cloudlink.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.ycloud.cloudlink.base.BaseEntity;
import com.ycloud.cloudlink.base.BaseEntityNotLogic;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author Shawn.Geng
 * @date 2020-01-20
 */
public class CodeGenerator {

    /**
     * 父包名 <p>
     * 生成的 Entity、Mapper、Service、Impl 都会位于该包下),包不存在会自动创建 <p>
     * 生成的 xml 文件会在 resources/mapper 文件夹下
     */
    private static final String PARENT_PACKAGE = "com.weng.business";

    /** 数据库地址 */
    private static final String URL = "jdbc:mysql://localhost:13306/test_demo?useUnicode=true&useSSL=false&characterEncoding=utf8&tinyInt1isBit=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final DbType DB_TYPE = DbType.MYSQL;

    /** 读取控制台内容 */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder help = new StringBuilder();
        help.append(tip + "：");
        System.out.print(help.toString());

        if ("请输入项目模块名：".equals(help.toString())) {
            if (scanner.hasNextLine()) {
                String ipt = scanner.nextLine();
                return ipt;
            }
        }

        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException(tip + "！");
    }

    public static void main(String[] args) throws IOException {
        // 获取当前项目的完整根目录路径(user.dir指向的是项目的根目录)
        String projectPath = System.getProperty("user.dir");

        // 生成的Java文件所在的基础目录
        String javaFilePath = "/src/main/java";

        // 生成的Xml文件所在的基础目录
        String xmlFilePath = "/src/main/resources/mapper";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        System.err.println("注意：若想生成在当前根目录项目下请直接回车");
        // 提示输入项目模块名(代码文件生成到该模块下)
        String projectModuleName = scanner("请输入项目模块名");

        // 如果项目模块名为空
        if (StringUtils.isEmpty(projectModuleName)) {
            // 生成文件的输出目录,这里会输出到当前项目的/src/main/java路径下
            gc.setOutputDir(projectPath + javaFilePath);
        } else {
            // 生成文件的输出目录,这里会输出到当前项目的/项目模块名/src/main/java路径下
            StringBuilder outPutDir = new StringBuilder(projectPath);
            outPutDir.append("/").append(projectModuleName).append(javaFilePath);
            // 判断该项目模块是否存在
            if (!new File(outPutDir.toString()).exists()) {
                throw new MybatisPlusException("项目模块 " + projectModuleName + " 不存在！");
            }
            gc.setOutputDir(outPutDir.toString());
        }

        // 作者
        gc.setAuthor(scanner("请输入开发人员"));
        // 是否打开输出目录,默认true
        gc.setOpen(false);
        // 实体类属性添加Swagger2注解,默认false
        gc.setSwagger2(false);
        // 是否覆盖已有文件,可以和下面的cfg.setFileCreate方法配合使用,默认false
        gc.setFileOverride(true);
        // 设置主键Id类型为手动输入,不让它自动填充
        gc.setIdType(IdType.INPUT);
        // 指定 Service 文件名
        // gc.setServiceName("%sService");
        // 将全局配置添加到生成器中
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 数据库类型
        dsc.setDbType(DB_TYPE);
        // 数据源
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        // 驱动
        dsc.setDriverName(DRIVER_NAME);
        // 用户名
        dsc.setUsername(USERNAME);
        // 密码
        dsc.setPassword(PASSWORD);
        // 自定义数据库表字段类型转换（可选）
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                // 数据库 datetime 类型转实体 LocalDateTime 类型
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.LOCAL_DATE_TIME;
                }
                // 数据库 date 类型转成实体 LocalDate 类型
                if (fieldType.toLowerCase().contains("date")) {
                    return DbColumnType.LOCAL_DATE;
                }
                // 数据库 text 类型转成实体 String 类型
                if (fieldType.toLowerCase().contains("text")) {
                    return DbColumnType.STRING;
                }
                // 数据库 bigint 类型转实体 String 类型
                if (fieldType.toLowerCase().contains("bigint")) {
                    return DbColumnType.STRING;
                }
                // 数据库 tinyint 类型转实体 Integer 类型
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.INTEGER;
                }
                // 数据库 decimal 类型转实体 BigDecimal 类型
                if (fieldType.toLowerCase().contains("decimal")) {
                    return DbColumnType.BIG_DECIMAL;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 模块名,也就是包名,这里只是控制台提示,改不改不影响
        pc.setModuleName(scanner("请输入包名"));
        // 父包名,也就是将新生成的文件放置于该包名下,根据业务修改
        pc.setParent(PARENT_PACKAGE);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 指定Xml文件模板(这里使用MyBatis-Plus生成器自带的模板所在位置,也可以定义成自己的模板)
        String xmlTemplatePath = "/templates/mapper.xml.vm";

        // 保存 mapper 文件夹路径对象
        File file;
        // 如果项目模块名为空
        if (StringUtils.isEmpty(projectModuleName)) {
            file = new File(projectPath + xmlFilePath);
        } else {
            // file = new File(projectPath + "/" + projectModuleName + xmlFilePath);
            file = new File(projectPath + "/" + xmlFilePath);
        }
        // 如果 mapper 文件夹不存在,则创建
        if (!file.exists()) {
            file.mkdir();
        }

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(xmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名,如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                StringBuilder outputFile = new StringBuilder(projectPath);
                if (StringUtils.isEmpty(projectModuleName)) {
                    outputFile.append(xmlFilePath)
                            .append("/")
                            .append(pc.getModuleName())
                            .append("/")
                            .append(tableInfo.getEntityName())
                            .append("Mapper")
                            .append(StringPool.DOT_XML);
                } else {
                    outputFile.append("/")
                            .append(projectModuleName)
                            .append(xmlFilePath)
                            .append("/")
                            .append(pc.getModuleName())
                            .append("/")
                            .append(tableInfo.getEntityName())
                            .append("Mapper")
                            .append(StringPool.DOT_XML);
                }
                return outputFile.toString();
            }
        });

        cfg.setFileCreate(new IFileCreate() {
            // 这里可以自定义生成时覆盖哪些文件
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断文件夹是否需要创建
                checkDir(filePath);
                File file = new File(filePath);
                boolean exist = file.exists();
                // 如果文件存在
                if (exist) {
                    // 如果当前生成的文件不是 Entity 类型的,则不用生成该文件(防止覆盖),如果要判断xml类型请使用|| filePath.endsWith("Mapper.xml")
                    if (FileType.ENTITY != fileType) {
                        return false;
                    }
                }
                // 不存在的文件都需要创建,如果是 Entity 实体类则覆盖旧的
                return true;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("/generator/entity.java");
        templateConfig.setMapper("/generator/mapper.java");
        templateConfig.setService("/generator/service.java");
        templateConfig.setServiceImpl("/generator/serviceImpl.java");
        // 不生成 Controller
        templateConfig.setController(null);
        // 上面已经自定义了 xml 文件的输出,这里无需再次生成
        templateConfig.setXml(null);
        // 将模板配置添加到生成器中
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否生成实体时,生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 实体类是否使用Lombok
        strategy.setEntityLombokModel(true);
        // 使用链式设置对象属性
        strategy.setChainModel(true);
        if ("0".equals(scanner("是否有逻辑删除字段(0-否,1-是)"))) {
            strategy.setSuperEntityClass(BaseEntityNotLogic.class);
        } else {
            strategy.setSuperEntityClass(BaseEntity.class);
        }
        // 不生成序列化
        strategy.setEntitySerialVersionUID(false);
        // 是否使用RestController风格
        strategy.setRestControllerStyle(true);
        // 是否开启驼峰连转
        strategy.setControllerMappingHyphenStyle(true);

        // setInclude：设置包含的表名(与exclude二选一)
        strategy.setInclude(scanner("请输入表名(多张表使用英文逗号分割)").split(","));
        // setExclude：设置需要排除的表名,允许正则表达式
        // strategy.setExclude(scanner("表名(多张表使用英文逗号分割)").split(","));
        // setLikeTable：设置模糊匹配的表名(与notLikeTable二选一)
        // strategy.setLikeTable(new LikeTable("sys", SqlLike.RIGHT));
        // setLikeTable：设置模糊排除的表名
        // strategy.setNotLikeTable(new LikeTable("sys", SqlLike.RIGHT));

        // 指定表名前缀(一般不需要设置,如果设置了则会把前缀的名称去掉,生成时仅保留前缀后面的名称)
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        // 将数据库表配置添加到生成器中
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
