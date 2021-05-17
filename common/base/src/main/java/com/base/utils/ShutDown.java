package com.base.utils;

public class ShutDown extends Thread {
    @Override
    public void run() {
        try {
            /*URL httpUrl = new URL("https://api.telegram.org/bot1067418419:AAFzVWG39m4vVk_HH23VroQlgLKi5eleVeE/sendMessage?chat_id=-366604957&text=环境：");
            HttpURLConnection con = (HttpURLConnection) httpUrl.openConnection();
            //默认值我GET
            con.setRequestMethod("GET");
            //添加请求头
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : ");
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //打印结果
            System.out.println(response.toString());*/
        } catch (Exception e) {
            System.out.println("😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭");
        }
    }
}
