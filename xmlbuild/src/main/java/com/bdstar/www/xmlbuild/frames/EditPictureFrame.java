package com.bdstar.www.xmlbuild.frames;

import com.bdstar.www.xmlbuild.beans.PictureBean;
import com.bdstar.www.xmlbuild.beans.PressBean;
import com.bdstar.www.xmlbuild.build.ListManual;
import com.bdstar.www.xmlbuild.build.MainBuild;
import com.bdstar.www.xmlbuild.build.PictureManual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditPictureFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JPanel picPanelOut;
    private final DataModel model;
    private final JPanel infoOut;
    private final JTextField picWidth;
    private final JTextField picHeight;
    private final JTextField picR;
    private final Dimension dimText;
    private final Dimension dimLable;
    private final JScrollPane scrollInfo;
    private final JPanel bottomPanel;
    private final JTextField picSrc;
    private final String picturePath;
    private final Dimension dimLine;
    private final JFileChooser fileChooser;
    private PicPanel picPanel;
    String rootPath;
    private File txtFile;
    private PictureBean pictureBean;
    ArrayList<LinkComponent> linkComponents = new ArrayList<>();

    public EditPictureFrame(final String rootPath) {
        this.rootPath = rootPath;
        picturePath = rootPath + PictureManual.pathLocal;
        File txtDir = new File(picturePath);
        if (!txtDir.exists()) {
            txtDir.mkdirs();
        }
        setLayout(null);
        setTitle("北斗用户手册数据生成器");
        setSize(1300, 600);
        setLocationRelativeTo(null);//窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileChooser = new JFileChooser(rootPath+ ListManual.pathLocal);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("请选择关联内容");
        Border etchedBorder = BorderFactory.createEtchedBorder();

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        dimLine = new Dimension();
        dimLine.setSize(340, 2);
        dimText = new Dimension();
        dimText.setSize(280, 30);
        dimLable = new Dimension();
        dimLable.setSize(50, 30);
        Dimension dimOut = new Dimension();
        dimOut.setSize(350, 0);
        //图片
        picPanelOut = new JPanel(new CardLayout());
        picPanelOut.setBorder(etchedBorder);
        contentPane.add(picPanelOut, BorderLayout.CENTER);
        picPanel = new PicPanel();
        picPanel.setLinkComponents(linkComponents);
        picPanelOut.add(picPanel);

        //
        bottomPanel = new JPanel();//
        bottomPanel.setBorder(etchedBorder);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(new JTextField("根目录："+rootPath));
         JButton btnBuild = addBtn(bottomPanel, "生成xml", 150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ListManual.build(rootPath);
                PictureManual.build(rootPath);

                JOptionPane.showMessageDialog(null, "已经生成xml");
            }
        });

        //
        infoOut = new JPanel();//new GridLayout(0, 2)
        infoOut.setBorder(new CompoundBorder());
        infoOut.setPreferredSize(dimOut);
        JLabel lableSrc = new JLabel("图片名:");
        picSrc = new JTextField();
        JLabel lableWidth = new JLabel("宽:");
        picWidth = new JTextField();
        JLabel lableHeight = new JLabel("高:");
        picHeight = new JTextField();
        JLabel lableR = new JLabel("半径:");
        picR = new JTextField("33");

        lableSrc.setPreferredSize(dimLable);
        picSrc.setPreferredSize(dimText);
        lableWidth.setPreferredSize(dimLable);
        picWidth.setPreferredSize(dimText);
        lableHeight.setPreferredSize(dimLable);
        picHeight.setPreferredSize(dimText);
        lableR.setPreferredSize(dimLable);
        picR.setPreferredSize(dimText);

        infoOut.add(lableSrc);
        infoOut.add(picSrc);
        infoOut.add(lableWidth);
        infoOut.add(picWidth);
        infoOut.add(lableHeight);
        infoOut.add(picHeight);
        infoOut.add(lableR);
        infoOut.add(picR);

        addBtn(infoOut, "添加", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addLink();
            }
        });
        addBtn(infoOut, "保存", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (txtFile != null && pictureBean != null) {
                    pictureBean.setR(picR.getText().trim());
                    ArrayList<PressBean> pressBeans = new ArrayList<>();
                    for (LinkComponent linkComponent : linkComponents) {
                        PressBean pressBean = new PressBean();
                        pressBean.setY(linkComponent.getPicY().getText().trim());
                        pressBean.setX(linkComponent.getPicX().getText().trim());
                        pressBean.setLink(linkComponent.getPicLink().getText().trim());
                        pressBeans.add(pressBean);
                    }

                    pictureBean.setPressBeans(pressBeans);

                    PictureManual.saveFile(txtFile, pictureBean);
                }

                JOptionPane.showMessageDialog(null, "保存完成");
            }
        });

        scrollInfo = new JScrollPane(infoOut, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollInfo, BorderLayout.EAST);


        //
        model = new DataModel(picturePath);
        final JList list = new JList(model);
        list.setBorder(BorderFactory.createTitledBorder("图片列表"));
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = list.getSelectedIndex();
                File file = model.getItemAt(index);
                picPanel.setFile(file);
                picPanelOut.validate();

                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.indexOf("."));
                String xmlPath =PictureManual.car_out;
                if(fileName.startsWith(DataModel.umin)){
                    xmlPath =PictureManual.car_in;
                }

                String txtFilePath = picturePath +"\\" + xmlPath+"\\" + fileName + ".txt";
                txtFile = new File(txtFilePath);
                if (!txtFile.exists()) {
                    try {
                        txtFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                pictureBean = PictureManual.readFile(txtFile);
                pictureBean.setSrc(fileName);
                pictureBean.setWidth(String.valueOf(picPanel.imgWidth));
                pictureBean.setHight(String.valueOf(picPanel.imgHeight));
                deleteAllLink();

                ArrayList<PressBean> pressBeans = pictureBean.getPressBeans();
                if (pressBeans != null && pressBeans.size() > 0) {
                    for (PressBean pressBean : pressBeans) {
                        addLink(pressBean);
                    }
                }

                picSrc.setText(pictureBean.getSrc());
                picWidth.setText(pictureBean.getWidth());
                picHeight.setText(pictureBean.getHight());

                updateUI();
            }
        });
        JScrollPane scrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.WEST);
    }

    private JButton addBtn(JPanel panel, String info, ActionListener actionListener) {
        return addBtn(panel, info, 60, actionListener);
    }

    private JButton addBtn(JPanel panel, String info, int width, ActionListener actionListener) {
        JButton btn = new JButton(info);
        Dimension dimLine = new Dimension();
        dimLine.setSize(width, 30);
        btn.setPreferredSize(dimLine);
        panel.add(btn);
        btn.addActionListener(actionListener);
        return btn;
    }

    private void addLink() {
        addLink(null);
    }

    private void addLink(PressBean pressBean) {
        JTextField line = new JTextField();
        line.setBackground(Color.black);
        line.setPreferredSize(dimLine);
        infoOut.add(line);

        JLabel labelLink = new JLabel("link:");
        final JTextField picLink = new JTextField();
        labelLink.setPreferredSize(dimLable);
        picLink.setPreferredSize(dimText);
        infoOut.add(labelLink);
        infoOut.add(picLink);
        picLink.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int result = fileChooser.showOpenDialog(EditPictureFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    picLink.setText(path);
                    updateUI();
                }
            }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });


        JLabel labelX = new JLabel("X:");
        JTextField picX = new JTextField();
        labelX.setPreferredSize(dimLable);
        picX.setPreferredSize(dimText);
        infoOut.add(labelX);
        infoOut.add(picX);
        JLabel labelY = new JLabel("Y:");
        JTextField picY = new JTextField();
        labelY.setPreferredSize(dimLable);
        picY.setPreferredSize(dimText);
        infoOut.add(labelY);
        infoOut.add(picY);
        if (pressBean != null) {
            picLink.setText(pressBean.getLink());
            picX.setText(pressBean.getX());
            picY.setText(pressBean.getY());
        }

        final LinkComponent linkComponent = new LinkComponent(picLink, picX, picY);
        linkComponents.add(linkComponent);
        JButton btnEdit = addBtn(infoOut, "编辑", 75, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!linkComponent.isEdit) {
                    checkEdit(linkComponent);
                }
            }
        });
        JButton btnDelete = addBtn(infoOut, "删除", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteLink(linkComponent);
                updateUI();
            }
        });
        int addHeight = dimLine.height + dimText.height * 4;
        Dimension infoH = infoOut.getPreferredSize();
        infoH.height = infoOut.getHeight() + addHeight;
        infoOut.setPreferredSize(infoH);
        updateUI();

        linkComponent.setBtnEdit(btnEdit);
        linkComponent.addComponent(line);
        linkComponent.addComponent(labelLink);
        linkComponent.addComponent(labelX);
        linkComponent.addComponent(labelY);
        linkComponent.addComponent(btnEdit);
        linkComponent.addComponent(btnDelete);
        checkEdit(linkComponent);
    }

    private void deleteAllLink() {
        for (LinkComponent linkComponent : linkComponents) {
            deleteLink(linkComponent, false);
        }

        linkComponents.clear();
    }

    private void deleteLink(LinkComponent linkComponent, boolean isRemove) {
        linkComponent.clearComponent(infoOut);
        int addHeight = dimLine.height + dimText.height * 4;
        Dimension infoH = infoOut.getPreferredSize();
        infoH.height =   infoH.height - addHeight;
        infoOut.setPreferredSize(infoH);
        if (isRemove) {
            linkComponents.remove(linkComponent);
        }
        infoOut.updateUI();
    }

    private void deleteLink(LinkComponent linkComponent) {
        deleteLink(linkComponent, true);
    }

    private void checkEdit(LinkComponent linkComponent) {
        picPanel.checkecLinkComponent = linkComponent;
        for (LinkComponent link : linkComponents) {
            if (link == linkComponent) {
                link.setEdit(true);
            } else {
                link.setEdit(false);
            }
        }
    }

    private void updateUI() {
        infoOut.validate();
        SwingUtilities.updateComponentTreeUI(this);//添加或删除组件后,更新窗口
//        JScrollBar jsb = scrollInfo.getVerticalScrollBar();//得到垂直滚动条
//        jsb.setValue(jsb.getMaximum());//把滚动条位置设置到最下面
    }


    public static void main(final String[] args) {
        String jarWholePath = MainBuild.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try {
            jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }
        String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();
        System.out.println("jarPath-->"+jarPath);

        String path =jarPath+"\\";// "E:\\CarManual\\";
        JFrame frame = new EditPictureFrame(path);
        frame.setVisible(true);
    }
//Main-Class: com.bdstar.www.xmlbuild.frames.EditPictureFrame
}
