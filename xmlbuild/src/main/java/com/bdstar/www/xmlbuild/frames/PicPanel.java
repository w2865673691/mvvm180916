package com.bdstar.www.xmlbuild.frames;

import com.bdstar.www.xmlbuild.beans.PressBean;
import com.bdstar.www.xmlbuild.build.ListManual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PicPanel extends JPanel {
    ArrayList<LinkComponent> linkComponents;
    public LinkComponent checkecLinkComponent;
    private JTextField jTextField;
    private Image img;
    public int imgHeight;
    public int imgWidth;
    File file;
    private int imgX;
    private int imgY;

    public PicPanel() {
        Dimension dim = new Dimension();
        dim.setSize(200, 30);
        jTextField = new JTextField();
        jTextField.setPreferredSize(dim);
        jTextField.setBackground(Color.getColor("#00000000"));
        add(jTextField);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                int mouseX = mouseEvent.getX() - imgX;
                int mouseY = mouseEvent.getY() - imgY;
                String info = "";
                if (mouseX > 0 && mouseY > 0 && mouseX < imgWidth && mouseY < imgHeight) {
                    info = "X:" + (mouseEvent.getX() - imgX) + "  Y:" + (mouseEvent.getY() - imgY);
                }
                jTextField.setText(info);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int mouseX = mouseEvent.getX() - imgX;
                int mouseY = mouseEvent.getY() - imgY;
                if (mouseX > 0 && mouseY > 0 && mouseX < imgWidth && mouseY < imgHeight) {
                    if (checkecLinkComponent != null) {
                        checkecLinkComponent.getPicX().setText("" + (mouseEvent.getX() - imgX));
                        checkecLinkComponent.getPicY().setText("" + (mouseEvent.getY() - imgY));
                        updateUI();
                    }
                }
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
    }

    public void setFile(File file) {
        this.file = file;
        if (file != null) {
            String name = file.getAbsolutePath();
//            System.out.println("name-->" + name);
            img = Toolkit.getDefaultToolkit().getImage(name);
            imgWidth = img.getWidth(null);
            imgHeight = img.getHeight(null);
            setSize(imgWidth, imgHeight);
        }
        validate();
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (file != null) {
            int width = PicPanel.this.getWidth();
            int height = PicPanel.this.getHeight();
            imgX = (width - imgWidth) / 2;
            imgY = (height - imgHeight) / 2;
            graphics.drawImage(img, imgX, imgY, null);
        }
        if (linkComponents != null) {
            for (LinkComponent linkComponent : linkComponents) {
                PressBean pressBean = new PressBean();
                pressBean.setY(linkComponent.getPicY().getText().trim());
                pressBean.setX(linkComponent.getPicX().getText().trim());
                pressBean.setLink(linkComponent.getPicLink().getText().trim());

                try {
                    String xP = pressBean.getX();
                    if (xP == null || xP.length() <= 0) {
                        continue;
                    }
                    String yP = pressBean.getY();
                    if (yP == null || yP.length() <= 0) {
                        continue;
                    }

                    int width = 10;
                    int x = Integer.valueOf(pressBean.getX()) + imgX - width / 2;
                    int y = Integer.valueOf(pressBean.getY()) + imgY - width / 2;

                    graphics.setColor(Color.YELLOW);
                    graphics.fillOval(x, y, width, width);

                    String link = pressBean.getLink();
                    link=link.substring(link.indexOf(ListManual.pathLocal));
                    graphics.drawString(link, x,y-15);
                } catch (Exception e) {
                }
            }
        }
    }

    public void setLinkComponents(ArrayList<LinkComponent> linkComponents) {
        this.linkComponents = linkComponents;
    }
}