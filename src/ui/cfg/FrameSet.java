package ui.cfg;

import Util.FrameUtil;
import control.GameControl;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FrameSet extends JFrame
{
    private GameControl gameControl;
    private static List<JComboBox<String>> comboBoxes = new ArrayList<>();
    private static List<JLabel> Labels = new ArrayList<>();

    private JButton OJBK = new JButton("确定");
    private JButton CANCEL = new JButton("取消");
    private JButton APPLY = new JButton("应用");

    private Properties PROP = new Properties();

    private static final String[] keyList = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                                             "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    private static final String[] keyNameList = {"up", "down", "left", "right", "downToBottom","cheat"};
    private static final String[] labelNameList;
    private static JComboBox<String> jcbUp = new JComboBox<>(keyList);
    private static JComboBox<String> jcbDown = new JComboBox<>(keyList);
    private static JComboBox<String> jcbLeft = new JComboBox<>(keyList);
    private static JComboBox<String> jcbRight = new JComboBox<>(keyList);
    private static JComboBox<String> jcbCheat = new JComboBox<>(keyList);
    private static JComboBox<String> jcbDownToBottom = new JComboBox<>(keyList);

    static
    {
        labelNameList = new String[]{"旋转", "下", "左", "右","速降", "作弊"};
        for (String labelName : labelNameList)
        {
            Labels.add(new JLabel(labelName + ":"));
        }

        comboBoxes.add(jcbUp);
        comboBoxes.add(jcbDown);
        comboBoxes.add(jcbLeft);
        comboBoxes.add(jcbRight);
        comboBoxes.add(jcbCheat);
        comboBoxes.add(jcbDownToBottom);
    }

    public FrameSet(GameControl gameControl)
    {
        this.gameControl = gameControl;
        //固定窗口位置及大小
        this.setSize(500, 300);
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
            Labels.get(i).setBounds(i < 4 ? 10 : 220, 10 + 40 * (i < 4 ? i : i - 4), 30, 30);
            ctrlPanel.add(Labels.get(i));
        }

        //显示文本输入框
        for (int i = 0; i < comboBoxes.size(); i++)
        {
            comboBoxes.get(i).setBounds(i < 4 ? 40 : 250, 10 + 40 * (i < 4 ? i : i - 4), 60, 30);
            ctrlPanel.add(comboBoxes.get(i));
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

        OJBK.addActionListener(actionEvent ->
                               {
                                   writeProfile(PROP);
                                   this.dispose();
                               });

        //取消按钮
        buttonPanel.add(CANCEL);
        CANCEL.addActionListener(actionEvent -> this.dispose());

        //应用按钮
        buttonPanel.add(APPLY);
        APPLY.addActionListener(actionEvent -> writeProfile(PROP));
        this.setVisible(true);
        return buttonPanel;

    }


    private void loadProfile()
    {
        try
        {
            FileInputStream fis = new FileInputStream("./data/control.Properties");
            PROP.load(fis);
            for (int i = 0; i < keyNameList.length; i++)
            {
                comboBoxes.get(i).setSelectedItem(PROP.getProperty(keyNameList[i]));
            }
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
        for (int i = 0; i < keyNameList.length; i++)
        {
            for (int j = 0; j != i && j < keyNameList.length; j++)
            {
                if (comboBoxes.get(i).getSelectedItem() == comboBoxes.get(j).getSelectedItem())
                {
                    JOptionPane.showMessageDialog(null, labelNameList[i] + " 与 " + labelNameList[j] + " 冲突");
                    return;
                }
            }
            PROP.setProperty(keyNameList[i], (String) comboBoxes.get(i).getSelectedItem());
        }
        //写入配置文件
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream("./data/control.Properties");
            prop.store(fos, "Key profile");
            gameControl.setKeyProfile();

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