package com.zh.es.util;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//@Slf4j
public class ESUtils {


    private static RestHighLevelClient client;
    private static String[] nodes = {
            "http://139.224.116.65:9200",
            "http://139.224.116.65:9200"
    };


    public static RestHighLevelClient getClient() {
        return client;
    }

    public static void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
//                log.error("close es client exception {}", e);
            }
        }
    }



    public static void main(String[] args) {
        // 测试
        RestHighLevelClient client = getClient();
        IndexRequest indexRequest = new IndexRequest();
//        BulkRequestBuilder bulkRequestBuilder = new BulkRequestBuilder(client);

//        BulkProcessor build = BulkProcessor.builder(client.getLowLevelClient(),
//                new BulkProcessor.Listener() {
//                    @Override
//                    public void beforeBulk(long executionId, BulkRequest request) {
//
//                    }
//
//                    @Override
//                    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
//
//                    }
//
//                    @Override
//                    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
//
//                    }
//                }
//
//
//        ).build();

        close();
    }


    public static HttpHost[] getNode(String... nodes) {
        List<HttpHost> list = new LinkedList<>();
        for (String s1 : nodes) {
            list.add(HttpHost.create(s1));
        }
        return (HttpHost[]) list.toArray();
    }


    static {
        RestClientBuilder builder = RestClient.builder(getNode(nodes));
        client = new RestHighLevelClient(builder);
    }
}
