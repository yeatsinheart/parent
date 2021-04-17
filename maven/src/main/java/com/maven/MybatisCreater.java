package com.maven;

public class MybatisCreater {
    public static String starter(String project){
return "package com."+project+".config;\n" +
        "import org.mybatis.spring.annotation.MapperScan;\n" +
        "import org.springframework.context.annotation.Bean;\n" +
        "import org.springframework.context.annotation.Configuration;\n" +
        "\n" +
        "import com.baomidou.mybatisplus.annotation.DbType;\n" +
        "import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;\n" +
        "import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;\n" +
        "\n" +
        "//mybatis-plus.mapper-locations.classpath*=/mapper/**/*.xml\n" +
        "@Configuration\n" +
        "@MapperScan(\"com.*.mapper\")\n" +
        "public class MybatisPlusConfig {\n" +
        "\n" +
        "    @Bean\n" +
        "    public MybatisPlusInterceptor mybatisPlusInterceptor() {\n" +
        "        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();\n" +
        "        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));\n" +
        "        return interceptor;\n" +
        "    }\n" +
        "\n" +
        "}\n";
    }
}
