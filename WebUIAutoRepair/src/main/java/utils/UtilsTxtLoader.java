package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UtilsTxtLoader {
    public static String readFile02(String path) throws IOException {
        System.out.println("加载文件的路径为：" +path);

        StringBuilder file = new StringBuilder();
        // Use a string collection to store the path in the text, or String[] array
        FileInputStream fis = new FileInputStream(path);
        // Prevent garbled characters in the path If utf-8 is garbled, change it to GBK
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            file.append(line);
        }
        br.close();
        isr.close();
        fis.close();
        return file.toString();
    }

    public static void save(String savePath,String fileName,String content){
        if (!new File(savePath).exists()){
            new File(savePath).mkdirs();
        }
        File file = new File(savePath +fileName);
        BufferedWriter bw;

        try {
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
