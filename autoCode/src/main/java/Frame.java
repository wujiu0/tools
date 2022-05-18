import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Frame extends JFrame {
    Frame frame;
    private static final String explain = "由于每次实验都需要将代码拷进实验报告里，而我们却需要重复做“打开.java源文件->全选->复制->粘贴进word”这样的过程\n通过此工具，你只需要提供项目的目录，该工具将自动识别指定目录内所有的java源文件，并将其中的内容全部导出到“result.java”中\n\n注意：该工具暂只支持utf-8编码格式的文件导出导出的文件\n\t请使用绝对路径";
    static {
        JOptionPane.showMessageDialog(null, explain, "使用说明", JOptionPane.INFORMATION_MESSAGE);
    }
    JLabel l_dir, l_type;
    JComboBox<String> c_type;
    JTextField t_dir;
    JButton start = new JButton("确定");

    public Frame() {
        frame = this;
        this.setTitle("实验代码导出");
        this.setSize(400, 200);
        this.setLocation(800, 500);
        this.setResizable(false);
        init();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init() {
        this.setLayout(null);
        l_type = new JLabel("文件类型:");
        this.add(l_type);
        l_type.setBounds(5, 5, 100, 50);
        c_type = new JComboBox<>(new String[] { "java", "jsp" });
        this.add(c_type);
        c_type.setBounds(100, 15, 250, 30);
        l_dir = new JLabel("项目目录:");
        this.add(l_dir);
        l_dir.setBounds(5, 55, 100, 50);
        t_dir = new JTextField();

        this.add(t_dir);
        t_dir.setBounds(100, 65, 220, 30);
        JButton openBtn = new JButton("...");
        openBtn.addActionListener(e -> showFileOpenDialog(t_dir));
        this.add(openBtn);
        openBtn.setBounds(325,65,30,30);
        this.add(start);
        start.setBounds(150, 110, 100, 30);
        start.addActionListener(e -> {
            String dir = t_dir.getText();
            CodeCp.setPath(dir);
            CodeCp.setType(Objects.requireNonNull(c_type.getSelectedItem()).toString());
            try {
                JOptionPane.showMessageDialog(start, "导出成功，导出文件在" + CodeCp.copy(), "提示",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(start, "路径不存在", "提示", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    protected void showFileOpenDialog(JTextField dir) {
        // 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();

        // 设置默认显示的文件夹为当前文件夹
        fileChooser.setCurrentDirectory(new File("."));

        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            File file = fileChooser.getSelectedFile();
            dir.setText(file.getAbsolutePath());
        }

    }

}
