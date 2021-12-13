import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @DATE: 2021/12/12 4:15 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class Log4jTest {
    @Test
    public void quickStartLog4j() {
        BasicConfigurator.configure();
        Logger logger = Logger.getLogger(Log4jTest.class);

        logger.info("hello log4j");

        logger.fatal("fatal");//非常严重的错误，导致系统奔溃
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("追踪信息");
    }
}
