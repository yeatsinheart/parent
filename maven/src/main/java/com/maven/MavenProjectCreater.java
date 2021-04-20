package com.maven;

import com.common.utils.FileUtil;
import com.maven.multity.api.ApiFile;
import com.maven.multity.api.ApiPom;
import com.maven.multity.db.DbFile;
import com.maven.multity.db.DbPom;
import com.maven.multity.parent.ParentPom;
import com.maven.multity.service.ServiceFile;
import com.maven.multity.service.ServicePom;
import com.maven.multity.web.WebFile;
import com.maven.multity.web.WebPom;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MavenProjectCreater {

    public static void main(String[] args) {
        //业务线
        String business = "business";
        String basePath = System.getProperty("user.dir") + File.separator + business;
        //模块
        List<String> ps = Arrays.asList(
                "admin", "site",
                "center", "user", "risk",
                "order", "money", "cost",
                "pay", "webcast",
                "chat",
                "report",
                ""
        );
        ps=Arrays.asList("demo");
        String finalBasePath = basePath;
        String finalBusiness = business;
        ps.forEach(s -> {
            String realPath = finalBasePath + File.separator + s;
            newMultyModule(finalBusiness, realPath, s);
        });
       /* business = "game";
        basePath = System.getProperty("user.dir") + File.separator + business;
        List<String> gs = Arrays.asList(
                "lottery", "pork",
                ""
        );*/

    }

    public static void newMultyModule(String business, String basePath, String project) {
        FileUtil.remove(basePath);
        ParentPom.init(business, basePath, project);

        ApiPom.init(business, basePath + File.separator + project + "-api", project);
        ApiFile.init(basePath + File.separator + project + "-api", project);

        ServicePom.init(business, basePath + File.separator + project + "-service", project);
        ServiceFile.init(basePath + File.separator + project + "-service", project);

        WebPom.init(business, basePath + File.separator + project + "-web", project);
        WebFile.init(basePath + File.separator + project + "-web", project);

        DbPom.init(business, basePath + File.separator + project + "-db", project);
        DbFile.init(basePath + File.separator + project + "-db", project);
    }

}
