package com.maven;

public class StarterCreater {
    public static String starter(String project){
return "package com."+project+";\n" +
        "\n" +
        "import org.springframework.boot.SpringApplication;\n" +
        "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
        "import org.springframework.context.ConfigurableApplicationContext;\n" +
        "\n" +
        "@SpringBootApplication\n" +
        "public class Application {\n" +
        "\n" +
        "    public static void main(String[] args) {\n" +
        "        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);\n" +
        "    }\n" +
        "\n" +
        "}\n";
    }
}
