package com.gen.controller;

import com.gen.entities.Table;
import com.gen.services.MysqlService;
import com.gen.utils.FileGenUtil;
import com.gen.utils.MysqlUtil;
import com.gen.utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Slf4j
@Controller
public class TestController {
    @Autowired
    MysqlService dbService;

    @GetMapping("/hello")
    public Mono<String> hello(final Model model) {
        model.addAttribute("name", "泥瓦匠");
        model.addAttribute("city", "浙江温岭");
        String path = "hello";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    @GetMapping("/")
    public String index() {
        String path = "db";
        return path;
    }

    // 获取数据库信息
    //localhost:8080/db?db=mysql&url=47.242.219.77:3306/chzx_chat&user=root&pwd=IQdtJcwVuspR0WT6&project=testdemo&uuid=1s
    @GetMapping("/down")
    public Mono<Void> db(String db, String url, String user, String pwd, String table, String project, String uuid, ServerHttpResponse response) {
        //被压缩的文件夹
        String tmp = uuid;
        File tmpFile = new File(tmp);
        String sourceFile = tmp + File.separator + uuid;
        if (StringUtils.isAllBlank(sourceFile)) {
            return Mono.empty();
        }
        List<String> tableQuery = new ArrayList<>();
        if (!StringUtils.isAllEmpty(table)) {
            tableQuery = Arrays.asList(table.split(","));
        }
        SqlSessionFactory sqlSessionFactory = MysqlUtil.connect(db, url, user, pwd);
        List<Table> tables = dbService.getAllTables(sqlSessionFactory, tableQuery);
        for (Table table1 : tables) {
            table1.setColumnList(dbService.getAllColumns(sqlSessionFactory, table1.getName()));
            FileGenUtil.gen(table1, project, sourceFile);
            //重定向到下载位置
        }

        String toFile = tmp + File.separator + "code" + uuid + ".zip";
        File to = new File(toFile);

        //压缩结果输出，即压缩包
        try {
            FileUtils.forceMkdirParent(to);
            FileOutputStream fos = new FileOutputStream(to);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);
            //递归压缩文件夹
            ZipUtils.zipFile(fileToZip, fileToZip.getName(), zipOut);
            //关闭输出流
            zipOut.close();
            fos.close();
        } catch (Exception e) {
            log.error("打包文件{}出错{}", toFile, e);
        }
        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + uuid + ".zip");
        response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return zeroCopyResponse.writeWith(to, 0, to.length())
                .doFinally(a -> {
                    try {
                        FileUtils.deleteDirectory(tmpFile);
                    } catch (Exception e) {
                        log.error("下载后删除文件失败{},{}", tmpFile.getAbsolutePath(), e);
                    }
                });
        /*return ServerResponse.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+ uuid + ".zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body((p, a) -> {
                    ZeroCopyHttpOutputMessage resp = (ZeroCopyHttpOutputMessage) p;
                    return resp.writeWith(to, 0, to.length());
                }).doFinally(a -> {to.deleteOnExit();from.deleteOnExit();});*/

    }

}
