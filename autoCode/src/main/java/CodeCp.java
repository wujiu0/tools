import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

public class CodeCp {
    private static File path;
    private static String type;
    private static ArrayList<File> fileList = new ArrayList<>();

    public static void setPath(String pathStr) {
        CodeCp.path = new File(pathStr);
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        CodeCp.type = type;
    }

    private static void getFiles(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if (file.toString().toLowerCase().endsWith("." + type)) {
                    CodeCp.fileList.add(file);
                    System.out.println(file.getAbsolutePath());
                }
            } else {
                getFiles(file);
            }
        }
        System.out.println(fileList.size());
    }

    

    public static String copy() throws FileNotFoundException {
        String resultPath = path + "\\result.txt";

        // 创建存放结果的文件
        // 此处选择声明抛出FileNotFoundException，让调用者处理（对话框提示）
        BufferedWriter bw = createResultFile(resultPath);

        // 路径存在，获取路径下的所有.java文件列表
        getFiles(path);

        try {
            String line;
            for (File file : fileList) {
                BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8));
                while ((line = br.readLine()) != null) {
                    // System.out.println(line);
                    bw.write(line);
                    bw.newLine();
                }
                br.close();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 刷新内存中的文件列表，防止连续使用读错数据
        fileList.clear();
        return resultPath;
    }

    private static BufferedWriter createResultFile(String resultPath) throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultPath, true), StandardCharsets.UTF_8));
    }

}
