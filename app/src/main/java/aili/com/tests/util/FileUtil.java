package aili.com.tests.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件处理工具类
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-05 16:29
 */

public class FileUtil {

    /**
     * 更加文件名字读取
     *
     * @param filePath
     * @param charsetName
     * @return
     */
    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent;
    }

    /**
     * 递归删除指定路径下的所有文件
     *
     * @param file 参数类型 File 对象
     */
    public static void deleteAllFile(File file) {
        try {
            if (file.isFile() || file.list().length == 0) {
                file.delete();
            } else {
                File[] files = file.listFiles();
                if (files.length > 0) {
                    for (File f : files) {
                        deleteAllFile(f);//递归删除每一个文件
                        f.delete();//删除该文件夹
                    }
                }
            }
        } catch (Exception e) {
        }
    }


}
