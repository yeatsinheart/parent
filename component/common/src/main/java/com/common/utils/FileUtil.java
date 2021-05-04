package com.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FileUtil {
    // 删除文件
    public static boolean remove(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            log.warn("文件{}删除失败", path);
        }
        return false;
    }

    // 创建目录
    public static boolean mkdir(String path) {
        File directory = new File(path);
        parent(directory);
        return directory.mkdirs();
    }

    // 创建文件
    public static File create(String path) {
        File file = null;
        try {
            file = new File(path);
            parent(file);
            file.createNewFile();
        } catch (Exception ioe) {
            log.warn("文件{}未创建{}", path, ioe);
        }
        return file;
    }

    // 迭代创建父路径
    public static void parent(File file) {
        boolean result = false;
        if (!file.getParentFile().exists()) {
            result = file.getParentFile().mkdirs();
            if (!result) {
                log.warn("创建父目录失败");
            }
        }
    }

    // 写入内容
    public static void write(String path, String content, boolean faildelete) {
        File f = create(path);
        if (null == f) {
            log.warn("文件{}找不到", path);
        }
        try {
            FileUtils.write(f, content, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            log.warn("文件{}未写入成功", path);
            if (f.exists() && faildelete) {
                f.delete();
            }
        }
    }

    // 添加内容
    public static void append(String path, String content, boolean faildelete) {
        File f = create(path);
        if (null == f) {
            log.warn("文件{}找不到", path);
        }
        try {
            FileUtils.write(f, content, StandardCharsets.UTF_8.name(), true);
        } catch (Exception e) {
            log.warn("文件{}未写入成功", path);
            if (f.exists() && faildelete) {
                f.delete();
            }
        }
    }

    public static void main(String[] args) {
        create("test" + File.separator + "pom.xml");
    }
}
