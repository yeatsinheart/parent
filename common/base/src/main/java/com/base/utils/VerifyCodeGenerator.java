package com.base.utils;

import com.base.secret.Base64Util;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class VerifyCodeGenerator {

    // 使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 使用系统默认字符源生成验证码
     *
     * @param verifySize 验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize) {
        String code = generateVerifyCode(verifySize, VERIFY_CODES);
        return code;
    }

    /**
     * 使用指定源生成验证码
     *
     * @param verifySize 验证码长度
     * @param sources    验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    public static void main(String[] args) throws IOException {
        int w = 80, h = 40;
        for (int i = 0; i < 50; i++) {
            long start = System.currentTimeMillis();
            String verifyCode = generateVerifyCode(4);
            // File file = new File(dir, verifyCode + ".jpg");
            //writeImage(w, h, file, verifyCode);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(ImgUitls.genBufferedImage(w, h, verifyCode), "jpeg", stream);
            String img = Base64Util.img(stream.toByteArray());
            long end = System.currentTimeMillis();
            System.out.println(end - start + "--" + img.length());
            System.out.println(img);
        }
    }
}
