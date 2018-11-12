package ui.cfg;

import Util.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FrameSet extends JFrame
{

    private static List<JTextField> TextList = new ArrayList<>();
    private static List<JLabel> Labels = new ArrayList<>();

    private JButton OJBK = new JButton("确定");
    private JButton CANCEL = new JButton("取消");
    private JButton APPLY = new JButton("应用");
    private Properties PROP = new Properties();

    private static JTextField jtfUp = new JTextField();
    private static JTextField jtfDown = new JTextField();
    private static JTextField jtfLeft = new JTextField();
    private static JTextField jtfRight = new JTextField();
    private static JTextField jtfCheat = new JTextField();

    static
    {
        Labels.add(new JLabel("上:"));
        Labels.add(new JLabel("下:"));
        Labels.add(new JLabel("左:"));
        Labels.add(new JLabel("右:"));
        Labels.add(new JLabel("作弊:"));

        TextList.add(jtfUp);
        TextList.add(jtfDown);
        TextList.add(jtfLeft);
        TextList.add(jtfRight);
        TextList.add(jtfCheat);
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

        //获取配置文件
        loadProfile();
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

        //显示Label
        for (int i = 0; i < Labels.size(); i++)
        {
            Labels.get(i).setBounds(i < 4 ? 10 : 160, 10 + 40 * (i < 4 ? i : i - 4), 30, 30);
            ctrlPanel.add(Labels.get(i));
        }

        //显示文本输入框
        for (int i = 0; i < TextList.size(); i++)
        {
            TextList.get(i).setBounds(i < 4 ? 30 : 190, 10 + 40 * (i < 4 ? i : i - 4), 60, 30);
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
        //确定按钮

        buttonPanel.add(OJBK);

        OJBK.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                writeProfile(PROP);
                setVisible(false);
            }
        });

        //取消按钮
        buttonPanel.add(CANCEL);
        CANCEL.addActionListener(actionEvent -> this.setVisible(false));

        //应用按钮
        buttonPanel.add(APPLY);
        APPLY.addActionListener(actionEvent -> writeProfile(PROP));
        return buttonPanel;
    }

    public static void main(String[] args)
    {
        new FrameSet();

    }

    private void loadProfile()
    {
        try
        {
            FileInputStream fis = new FileInputStream("./data/control.Properties");
            PROP.load(fis);
            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void writeProfile(Properties prop)
    {
        //获取设置键位
        PROP.setProperty("Up",jtfUp.getText());
        PROP.setProperty("Down",jtfDown.getText());
        PROP.setProperty("Left",jtfLeft.getText());
        PROP.setProperty("Right",jtfRight.getText());
        PROP.setProperty("Cheat",jtfCheat.getText());
        //写入配置文件
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream("./data/control.Properties");
            prop.store(fos, "Key profile");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fos != null)
                {
                    fos.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }



}