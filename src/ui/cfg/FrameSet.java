package ui.cfg;

import Util.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrameSet extends JFrame
{

    private List<JTextField> TextList = new ArrayList<>();
    private static List<JLabel> Labels = new ArrayList<>();

    static
    {
        Labels.add(new JLabel("上:"));
        Labels.add(new JLabel("下:"));
        Labels.add(new JLabel("左:"));
        Labels.add(new JLabel("右:"));
        Labels.add(new JLabel("作弊:"));
    }

    public FrameSet()
    {
        //固定窗口位置及大小
        this.setSize(300, 300);
        this.setResizable(false);
        FrameUtil.setFrameToCenter(this);

        //设置为边界布局
        this.setLayout(new BorderLayout());
        //添加主面板
        this.add(MainTabPanel(), BorderLayout.CENTER);

        //添加按钮面板
        this.add(ButtonPanel(), BorderLayout.SOUTH);


        //测试
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    /*创建主面板(包含两选项卡)*/
    private JTabbedPane MainTabPanel()
    {
        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("控制", CtrlPanel());
        jtp.addTab("皮肤", skinPanel());

        return jtp;
    }

    /*控制设置面板*/
    private JPanel CtrlPanel()
    {
        JPanel ctrlPanel = new JPanel();
        ctrlPanel.setLayout(null);

        for (int i = 0; i < Labels.size(); i++)
        {
            Labels.get(i).setBounds(i<4?10:160, 10 + 40 * (i<4?i:i-4), 30, 30);
            ctrlPanel.add(Labels.get(i));
        }

        JTextField jtfUp = new JTextField();
        JTextField jtfDown = new JTextField();
        JTextField jtfLeft = new JTextField();
        JTextField jtfRight = new JTextField();
        JTextField jtfCheat = new JTextField();

        TextList.add(jtfUp);
        TextList.add(jtfDown);
        TextList.add(jtfLeft);
        TextList.add(jtfRight);
        TextList.add(jtfCheat);

        for (int i = 0; i < TextList.size(); i++)
        {
            TextList.get(i).setBounds(i<4?30:190, 10 + 40 * (i<4?i:i-4), 60, 30);
            ctrlPanel.add(TextList.get(i));
        }

        return ctrlPanel;
    }

    /*皮肤设置面板*/
    private JPanel skinPanel()
    {
        return new JPanel();
    }

    /*创建按钮面板*/
    private JPanel ButtonPanel()
    {
        //创建按钮面板（流式布局）
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new JButton("确定"));
        buttonPanel.add(new JButton("取消"));
        buttonPanel.add(new JButton("应用"));
        return buttonPanel;
    }

    public static void main(String[] args)
    {
        new FrameSet();
    }

}