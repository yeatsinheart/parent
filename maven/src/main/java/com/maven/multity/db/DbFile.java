package com.maven.multity.db;

import com.base.utils.FileUtil;
import com.base.utils.StringUtil;
import com.maven.multity.BaseRunner;

import java.io.File;

public class DbFile {

    public static String level = "db";

    public static void init(String projectpath, String project) {

        //初始化文件夹结构
        String src = projectpath + File.separator + "src";
        FileUtil.mkdir(src + File.separator + "test" + File.separator + "java" + File.separator + level + File.separator + project);
        String base = src + File.separator + "main";

        String basePackage = base + File.separator + "java" + File.separator + level + File.separator + project;
        String starter = basePackage + File.separator + StringUtil.firstUpper(project) + StringUtil.firstUpper(level) + "Application.java";
        FileUtil.write(starter, BaseRunner.starter(level,project), true);

        String entities = basePackage + File.separator + "entities";
        FileUtil.mkdir(entities);
        String mapper = basePackage + File.separator + "mappers";
        FileUtil.mkdir(mapper);
        String dao = basePackage + File.separator + "daos";
        String baseDao = dao + File.separator + "BaseDao.java";
        FileUtil.mkdir(dao);
        // FileUtil.write(baseDao, baseDao(project), true);

        String daoImpl = dao + File.separator + "impls";
        FileUtil.mkdir(daoImpl);

        String baseResource = base + File.separator + "resources";
        String mapperxml = baseResource + File.separator + "mappers";
        FileUtil.mkdir(mapperxml);
        String resources = baseResource + File.separator + "application.properties";
        FileUtil.write(resources, resource(project), true);
        String datasql = baseResource + File.separator + "datasql.sql";
        FileUtil.write(datasql, datasql(project), true);
    }

    public static String baseDao(String project) {
        return "package " + level + "." + project + ".daos;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.core.conditions.Wrapper;\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import com.baomidou.mybatisplus.extension.service.IService;\n" +
                "\n" +
                "import java.util.Collection;\n" +
                "import java.util.Map;\n" +
                "import java.util.function.Function;\n" +
                "\n" +
                "public class BaseDao<T> implements IService<T> {\n" +
                "    @Override\n" +
                "    public boolean saveBatch(Collection<T> entityList, int batchSize) {\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean updateBatchById(Collection<T> entityList, int batchSize) {\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean saveOrUpdate(T entity) {\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public BaseMapper<T> getBaseMapper() {\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Class<T> getEntityClass() {\n" +
                "        return null;\n" +
                "    }\n" +
                "}\n";
    }


    public static String datasql(String project) {
        return "SELECT 1 FROM DUAL;";
    }

    public static String resource(String project) {
        return "spring.application.name=" + project + "-" + level + "\n" +
                "spring.output.ansi.enabled=always\n" +
                "#logging.level.root=debug\n" +
                "logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint}%clr(${LOG_LEVEL_PATTERN:%5p}) %clr(${PID}){magenta}%clr([%t]){faint} %clr(%-40.40logger{39}){cyan}[line:%line]%clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:%wEx}\n" +
                "" +
                "" +
                "#3.2.1以上使用\n" +
                "modulelist=com.baomidou.mybatisplus.extension.p6spy.MybatisPlusLogFactory,com.p6spy.engine.outage.P6OutageFactory\n" +
                "#3.2.1以下使用或者不配置\n" +
                "#modulelist=com.p6spy.engine.logging.P6LogFactory,com.p6spy.engine.outage.P6OutageFactory\n" +
                "# 自定义日志打印\n" +
                "logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger\n" +
                "#日志输出到控制台\n" +
                "appender=com.baomidou.mybatisplus.extension.p6spy.StdoutLogger\n" +
                "# 使用日志系统记录 sql\n" +
                "#appender=com.p6spy.engine.spy.appender.Slf4JLogger\n" +
                "# 设置 p6spy driver 代理\n" +
                "deregisterdrivers=true\n" +
                "# 取消JDBC URL前缀\n" +
                "useprefix=true\n" +
                "# 配置记录 Log 例外,可去掉的结果集有error,info,batch,debug,statement,commit,rollback,result,resultset.\n" +
                "excludecategories=info,debug,result,commit,resultset\n" +
                "# 日期格式\n" +
                "dateformat=yyyy-MM-dd HH:mm:ss\n" +
                "# 实际驱动可多个\n" +
                "#driverlist=org.h2.Driver\n" +
                "# 是否开启慢SQL记录\n" +
                "outagedetection=true\n" +
                "# 慢SQL记录标准 2 秒\n" +
                "outagedetectioninterval=2"
                ;
    }
}
