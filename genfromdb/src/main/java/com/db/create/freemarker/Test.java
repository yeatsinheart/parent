package com.db.create.freemarker;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
@Data
@EqualsAndHashCode(callSuper=false)
public class Test {
    public static void main(String[] args) {
        File directory = new File("src/main/java/com/auto/database/freemarker");

        System.out.println(directory.getAbsolutePath());
    }
}
