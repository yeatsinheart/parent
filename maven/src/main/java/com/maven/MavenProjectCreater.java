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
        List<String> modules = Arrays.asList(
                "global","tenant", "user",
                "cost","money","game","chat",
                "bus",
                "report"
        );
        create(modules, "business");
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

        FileUtil.write(".idea/misc.xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project version=\"4\">\n" +
                "  <component name=\"FrameworkDetectionExcludesConfiguration\">\n" +
                "    <type id=\"Spring\" />\n" +
                "  </component>\n" +
                "  <component name=\"MavenProjectsManager\">\n" +
                "    <option name=\"originalFiles\">\n" +
                "      <list>\n" +
                "        <option value=\"$PROJECT_DIR$/pom.xml\" />\n" +
                "        <option value=\"$PROJECT_DIR$/router/pom.xml\" />\n" +
                "        <option value=\"$PROJECT_DIR$/gate/pom.xml\" />\n" +
                "      </list>\n" +
                "    </option>\n" +
                "  </component>\n" +
                "  <component name=\"ProjectRootManager\" version=\"2\" languageLevel=\"JDK_15\" default=\"true\" project-jdk-name=\"15\" project-jdk-type=\"JavaSDK\" />\n" +
                "</project>", true);
    }

}
