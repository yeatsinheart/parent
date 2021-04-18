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

public class MavenProjectCreater {

    public static void main(String[] args) {
        newMultyModule("ademo");
    }


    public static void newMultyModule(String project) {
        FileUtil.remove(project);
        File file = new File(project);
        ParentPom.init(file.getAbsolutePath(),project);

        ApiPom.init(file.getAbsolutePath()+File.separator+project+"-api",project);
        ApiFile.init(file.getAbsolutePath()+File.separator+project+"-api",project);

        ServicePom.init(file.getAbsolutePath()+File.separator+project+"-service",project);
        ServiceFile.init(file.getAbsolutePath()+File.separator+project+"-service",project);

        WebPom.init(file.getAbsolutePath()+File.separator+project+"-web",project);
        WebFile.init(file.getAbsolutePath()+File.separator+project+"-web",project);

        DbPom.init(file.getAbsolutePath()+File.separator+project+"-db",project);
        DbFile.init(file.getAbsolutePath()+File.separator+project+"-db",project);
    }

}
