//package com.gateway.dubbo;
//
//import com.game.gateway.metadata.MetadataCollector;
//import com.game.gateway.utils.Constants;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.apache.dubbo.common.URL;
//import org.apache.dubbo.common.constants.CommonConstants;
//import org.apache.dubbo.metadata.report.identifier.KeyTypeEnum;
//import org.apache.dubbo.metadata.report.identifier.MetadataIdentifier;
//
//import javax.annotation.PostConstruct;
//
//@Slf4j
//public class ZookeeperMetadataCollector implements MetadataCollector {
//
//    private final static String METADATA_NODE_NAME = "";//service.data
//    private final static String DEFAULT_ROOT = "dubbo";
//    private CuratorFramework client;
//    private URL url;
//    private String root;
//
//    @Override
//    public URL getUrl() {
//        return this.url;
//    }
//
//    @Override
//    public void setUrl(URL url) {
//        this.url = url;
//    }
//
//    @PostConstruct
//    @Override
//    public void init() {
//        String group = url.getParameter(Constants.GROUP_KEY, DEFAULT_ROOT);
//        if (!group.startsWith(Constants.PATH_SEPARATOR)) {
//            group = Constants.PATH_SEPARATOR + group;
//        }
//        root = group;
//        String host = url.getAddress();
//        client = CuratorFrameworkFactory.newClient(host, new ExponentialBackoffRetry(1000, 3));
//        client.start();
//    }
//
//    @Override
//    public String getProviderMetaData(MetadataIdentifier key) {
//        return doGetMetadata(key);
//    }
//
//    private String getNodePath(MetadataIdentifier metadataIdentifier) {
//        String nodePath = toRootDir() + metadataIdentifier.getUniqueKey(KeyTypeEnum.PATH);
//        ///dubbo/metadata/com.game.report.service.ClientReportService/1.0.0/provider/report-service
//        //+ Constants.PATH_SEPARATOR + METADATA_NODE_NAME;
//        return nodePath;
//    }
//
//    private String toRootDir() {
//        if (null == root) {
//            return "";
//        }
//        if (CommonConstants.PATH_SEPARATOR.equals(root)) {
//            return root;
//        }
//        return root + CommonConstants.PATH_SEPARATOR;
//    }
//
//    private String doGetMetadata(MetadataIdentifier identifier) {
//        //TODO error handing 会触发空指针异常
//        try {
//            //log.error("获取zookeeper元信息{}",identifier);
//            String path = getNodePath(identifier);
//
//            if (client.checkExists().forPath(path) == null) {
//                log.info("查找注册服务元信息不存在{}", path);
//                return null;
//            }
//            String meta = new String(client.getData().forPath(path));
//            return meta;
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return null;
//    }
//}
