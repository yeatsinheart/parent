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
        casino();
    }

    public static void casino() {
        create(Arrays.asList("tenant", "user", "cms",
                "bus",
                "cost","pay","game",
                "report"
        ), "business");
    }

    public static void create(List<String> modules, String business) {
        //业务线
        // System.getProperty("user.dir") + File.separator +
        String basePath = business;
        modules.forEach(s -> {
            String realPath = basePath + File.separator + s;
            newMultyModule(business, realPath, s);
        });
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
