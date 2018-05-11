package study.algorithm.Chap07.Partition;

import java.awt.*;

public class personGroup {
	private final int appletWidth = 370;
	private final int appletHeight = 300;
	private final int maxHeight = 200;
	private final int topMargin = 30;
	private final int leftMargin = 10;
	private final int barLeftMargin = 35;
	private final int textHeight = 13;
	private int aSize;
	private person[] theArray;
	private int barWidth;
	private int barSeparation;
	private boolean doneFlag;
	private int codePart;
	private int oldCodePart;
	private int comps;
	private int swaps;
	private int initOrder;
	private int center;
	private int pivot;
	private int leftScan;
	private int rightScan;
	private int partition;
	private String note;
	private int drawMode;

	public personGroup(final int aSize, final int initOrder) {
		this.aSize = aSize;
		this.initOrder = initOrder;
		this.theArray = new person[this.aSize];
		if (this.aSize == 100) {
			this.barWidth = 2;
			this.barSeparation = 1;
		} else {
			this.barWidth = 18;
			this.barSeparation = 7;
		}
		this.comps = 0;
		this.swaps = 0;
		this.doneFlag = false;
		this.leftScan = -1;
		this.rightScan = this.aSize;
		this.codePart = 1;
		final Color color = new Color(0, 0, 0);
		this.note = "Press any button";
		if (this.initOrder == 1) {
			for (int i = 0; i < this.aSize; ++i) {
				this.theArray[i] = new person((int) (Math.random() * 199.0),
				    new Color((int) (Math.random() * 254.0), (int) (Math.random() * 254.0), (int) (Math.random() * 254.0)));
			}
		} else {
			for (int j = 0; j < this.aSize; ++j) {
				final int n = 195 - 195 * j / this.aSize;
				this.theArray[j] = new person(n, new Color(255 - n, 85 * (j % 3), n));
			}
		}
		this.drawMode = 2;
	}

	public void setDrawMode(final int drawMode) {
		this.drawMode = drawMode;
	}

	public int getAppletWidth() {
		return 370;
	}

	public int getAppletHeight() {
		return 300;
	}

	public boolean getDone() {
		return this.doneFlag;
	}

	public void arrowText(final Graphics graphics, final Color color, final String s, final int n, final int n2,
	    final boolean b, final boolean b2) {
		if (n < 0 || n > this.aSize - 1) {
			return;
		}
		final int n3 = 35 + n * (this.barWidth + this.barSeparation);
		final int n4 = 230 + (n2 + 1) * 13;
		graphics.setColor(color);
		if (b2) {
			graphics.drawString(s, n3, n4);
		}
		if (b) {
			graphics.drawLine(n3 + this.barWidth / 2, 232, n3 + this.barWidth / 2, n4 - 13);
			graphics.drawLine(n3 + this.barWidth / 2, 232, n3 + this.barWidth / 2 - 3, 237);
			graphics.drawLine(n3 + this.barWidth / 2, 232, n3 + this.barWidth / 2 + 3, 237);
		}
	}

	public void drawOneBar(final Graphics graphics, final int n) {
		if (n < 0 || n > this.aSize - 1) {
			return;
		}
		final int height = this.theArray[n].getHeight();
		final int n2 = 35 + n * (this.barWidth + this.barSeparation);
		final int n3 = 230 - height;
		final Color color = this.theArray[n].getColor();
		graphics.setColor(Color.lightGray);
		graphics.fillRect(n2, 30, this.barWidth, 200);
		graphics.setColor(color);
		graphics.fill3DRect(n2, n3, this.barWidth, height, true);
	}

	public void draw(final Graphics graphics) {
		if (this.drawMode != 2) {
			this.drawOneBar(graphics, this.leftScan);
			this.drawOneBar(graphics, this.rightScan);
		} else {
			graphics.setColor(Color.lightGray);
			graphics.fillRect(0, 0, 370, 300);
			for (int i = 0; i < this.aSize; ++i) {
				this.drawOneBar(graphics, i);
			}
		}
		graphics.setColor(Color.black);
		final int n = 230 - this.pivot;
		graphics.drawLine(5, n, 350, n);
		graphics.setColor(Color.lightGray);
		graphics.fillRect(0, 0, 120, 32);
		graphics.setColor(Color.black);
		graphics.drawString("Comparisons = " + this.comps, 10, 28);
		graphics.drawString("Swaps = " + this.swaps, 10, 15);
		graphics.setColor(Color.lightGray);
		graphics.fillRect(0, 230, 370, 78);
		if (this.aSize == 12) {
			if (this.oldCodePart == 7 || this.oldCodePart == 8) {
				this.arrowText(graphics, Color.magenta, "partition", this.partition, 1, true, true);
			} else {
				this.arrowText(graphics, Color.blue, "leftScan", this.leftScan, 2, true, true);
				this.arrowText(graphics, Color.blue, "rightScan", this.rightScan, 3, true, true);
			}
			this.arrowText(graphics, Color.black, this.note, 0, 4, false, true);
		} else if (this.oldCodePart == 7 || this.oldCodePart == 8) {
			this.arrowText(graphics, Color.magenta, "xxx", this.partition, 3, true, false);
		} else {
			this.arrowText(graphics, Color.blue, "xxx", this.leftScan, 2, true, false);
			this.arrowText(graphics, Color.blue, "xxx", this.rightScan, 2, true, false);
		}
		this.drawMode = 2;
	}

	public void partStep() {
		if (this.doneFlag) {
			return;
		}
		switch (this.codePart) {
		case 1: {
			this.pivot = 70 + (int) (Math.random() * 60.0);
			this.note = "Pivot value is " + this.pivot;
			this.oldCodePart = this.codePart;
			this.codePart = 3;
		}
		case 3: {
			this.note = "Will scan from left";
			this.oldCodePart = this.codePart;
			this.codePart = 4;
		}
		case 4: {
			this.oldCodePart = this.codePart;
			++this.comps;
			if (this.theArray[++this.leftScan].getHeight() < this.pivot) {
				this.note = "Continuing left scan";
				this.codePart = 4;
				return;
			}
			if (this.leftScan >= this.rightScan) {
				this.note = "Scans have met";
				this.codePart = 7;
				return;
			}
			this.note = "Will scan from right";
			this.codePart = 5;
		}
		case 5: {
			this.oldCodePart = this.codePart;
			++this.comps;
			final person[] theArray = this.theArray;
			final int rightScan = this.rightScan - 1;
			this.rightScan = rightScan;
			if (theArray[rightScan].getHeight() > this.pivot) {
				this.note = "Continuing right scan";
				this.codePart = 5;
				return;
			}
			if (this.leftScan >= this.rightScan) {
				this.note = "Scans have met";
				this.codePart = 7;
				return;
			}
			this.note = "Will swap leftScan and rightScan";
			this.codePart = 6;
		}
		case 6: {
			this.swap(this.leftScan, this.rightScan);
			this.note = "Will scan again from left";
			this.oldCodePart = this.codePart;
			this.codePart = 4;
		}
		case 7: {
			this.partition = this.leftScan;
			this.note = "Arrow shows partition";
			this.oldCodePart = this.codePart;
			this.codePart = 8;
		}
		case 8: {
			this.doneFlag = true;
			this.leftScan = 0;
			this.rightScan = this.aSize - 1;
			this.note = "Partition is complete";
			this.oldCodePart = this.codePart;
			this.codePart = 1;
		}
		default: {
		}
		}
	}

	public void swap(final int n, final int n2) {
		final person person = this.theArray[n];
		this.theArray[n] = this.theArray[n2];
		this.theArray[n2] = person;
		++this.swaps;
	}
}
