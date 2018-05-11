package study.algorithm.Chap07.Partition;

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Partition extends Applet implements Runnable, ActionListener
{
    private Image offscreenImage;
    private Graphics offscreenGraphics;
    private int aWidth;
    private int aHeight;
    private Thread runner;
    private int groupSize;
    private personGroup thePersonGroup;
    private boolean runFlag;
    private int order;
    private Button newButton;
    private Button sizeButton;
    private Button drawButton;
    private Button runButton;
    private Button stepButton;
    
    public void init() {
        this.thePersonGroup = new personGroup(this.groupSize, this.order);
        this.setLayout(new FlowLayout(2));
        this.add(this.newButton = new Button("New"));
        this.newButton.addActionListener(this);
        this.add(this.sizeButton = new Button("Size"));
        this.sizeButton.addActionListener(this);
        this.add(this.drawButton = new Button("Draw"));
        this.drawButton.addActionListener(this);
        this.add(this.runButton = new Button("Run"));
        this.runButton.addActionListener(this);
        this.add(this.stepButton = new Button("Step"));
        this.stepButton.addActionListener(this);
        this.aWidth = this.thePersonGroup.getAppletWidth();
        this.aHeight = this.thePersonGroup.getAppletHeight();
        this.offscreenImage = this.createImage(this.aWidth, this.aHeight);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        this.runFlag = false;
        this.thePersonGroup.setDrawMode(2);
    }
    
    public void paint(final Graphics graphics) {
        this.thePersonGroup.draw(this.offscreenGraphics);
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.newButton) {
            this.runFlag = false;
            this.order = ((this.order == 1) ? 2 : 1);
            this.thePersonGroup = new personGroup(this.groupSize, this.order);
        }
        else if (actionEvent.getSource() == this.sizeButton) {
            this.runFlag = false;
            this.groupSize = ((this.groupSize == 12) ? 100 : 12);
            this.thePersonGroup = new personGroup(this.groupSize, this.order);
        }
        else if (actionEvent.getSource() == this.drawButton) {
            this.runFlag = false;
            this.thePersonGroup.setDrawMode(2);
        }
        else if (actionEvent.getSource() == this.runButton) {
            this.runFlag = true;
            this.thePersonGroup.setDrawMode(1);
        }
        else if (actionEvent.getSource() == this.stepButton && !this.thePersonGroup.getDone()) {
            this.runFlag = false;
            this.thePersonGroup.partStep();
            this.thePersonGroup.setDrawMode(2);
        }
        this.repaint();
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.runner = null;
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            if (this.runFlag && !this.thePersonGroup.getDone()) {
                this.thePersonGroup.partStep();
                this.repaint();
                this.thePersonGroup.setDrawMode(1);
                final int n = (this.groupSize == 12) ? 250 : 75;
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public Partition() {
        this.groupSize = 12;
        this.order = 1;
    }
}
