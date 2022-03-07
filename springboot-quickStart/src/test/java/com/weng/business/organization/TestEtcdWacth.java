package com.weng.business.organization;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @DATE: 2022/1/21 10:39 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class TestEtcdWacth {


/*    @Autowired
    private KvClient kvClient;

    @Test
   public void testEtcdWatch(){
        String keyPrefix = "vrv-im-config";
        ByteString keyPrefixStr = ByteString.copyFrom(keyPrefix.getBytes(StandardCharsets.UTF_8));

        ByteString key1 = ByteString.copyFrom("vrv-im-config-key1".getBytes(StandardCharsets.UTF_8));
        ByteString key2 = ByteString.copyFrom("".getBytes(StandardCharsets.UTF_8));
        //ByteString key2 = ByteString.copyFrom("vrv-im-config-key2".getBytes(StandardCharsets.UTF_8));
        ByteString key3 = ByteString.copyFrom("vrv-im-config-key3".getBytes(StandardCharsets.UTF_8));
        ByteString key4 = ByteString.copyFrom("vrv-im-config-key4".getBytes(StandardCharsets.UTF_8));

        //kvClient.delete(key4).sync();
        kvClient.put(key1,key2).sync();

    }*/
}
