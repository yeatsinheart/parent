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
import java.util.List;

public class MavenProjectCreater {

    public static void main(String[] args) {
        String project = "user";
       // String projectPath = System.getProperty("user.dir") + File.separator + "business"+ File.separator + "game";
        String projectPath = System.getProperty("user.dir") + File.separator + "business";
        String parent = "business";
        String parentPackage = "code";
        String realPath = projectPath + File.separator + project;
        newMultyModule(parent, parentPackage, realPath, project);
    }

    public static void newMultyModule(String parent, String parentPackage, String basePath, String project) {
        FileUtil.remove(basePath);
        ParentPom.init(parent, parentPackage, basePath, project);

        ApiPom.init(parentPackage, basePath + File.separator + project + "-api", project);
        ApiFile.init(basePath + File.separator + project + "-api", project);

        ServicePom.init(parentPackage, basePath + File.separator + project + "-service", project);
        ServiceFile.init(basePath + File.separator + project + "-service", project);

        WebPom.init(parentPackage, basePath + File.separator + project + "-web", project);
        WebFile.init(basePath + File.separator + project + "-web", project);

        DbPom.init(parentPackage, basePath + File.separator + project + "-db", project);
        DbFile.init(basePath + File.separator + project + "-db", project);
    }

}
