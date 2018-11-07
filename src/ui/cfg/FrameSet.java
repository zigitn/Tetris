package ui.cfg;

import javax.swing.*;
import java.awt.*;

public class FrameSet extends JFrame
{
    private JButton ojbk = new JButton("确定");
    private JButton jbCancel = new JButton("取消");
    private JButton jbApply = new JButton("应用");

    public FrameSet()
    {
        //设置为边界布局
        this.setLayout(new BorderLayout());
        //添加主面板
        this.add(createMainPanel(),BorderLayout.CENTER);

        //添加按钮面板
        this.add(this.createButtonPanel(),BorderLayout.SOUTH);


        this.setVisible(true);
    }


    /*创建按钮面板*/
    private JPanel createButtonPanel()
    {
        //创建按钮面板（流式布局）
        JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jp.add(this.ojbk);
        return null;
    }


    /*创建主面板(选项卡)*/
    private JTabbedPane createMainPanel()
    {
        return new JTabbedPane();
    }

}

