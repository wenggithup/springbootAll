import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * @DATE: 2022/1/25 4:17 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class TestJava {
    public static void main(String[] args) {

        Client client = Client.builder().endpoints("http://127.0.0.1:2379").build();

        KV kvClient = client.getKVClient();

        try {
            kvClient.put(ByteSequence.from("123".getBytes(StandardCharsets.UTF_8)), ByteSequence.from("123".getBytes(StandardCharsets.UTF_8))).get();
            GetResponse getResponse = kvClient.get(ByteSequence.from("123".getBytes(StandardCharsets.UTF_8))).get();
            System.out.println(getResponse.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
