class Robot {
    private int w;
    private int h;
    private int p;
    private int pos;
    private boolean moved;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        this.p = 2 * (w - 1) + 2 * (h - 1);
        this.pos = 0;
        this.moved = false;
    }
    
    public void step(int num) {
        moved = true;
        pos = (pos + num) % p;
    }
    
    public int[] getPos() {
        if (pos < w) {
            return new int[]{pos, 0};
        } else if (pos < w + h - 1) {
            return new int[]{w - 1, pos - (w - 1)};
        } else if (pos < 2 * w + h - 2) {
            return new int[]{w - 1 - (pos - (w + h - 2)), h - 1};
        } else {
            return new int[]{0, h - 1 - (pos - (2 * w + h - 3))};
        }
    }
    
    public String getDir() {
        if (pos == 0) {
            return moved ? "South" : "East";
        } else if (pos < w) {
            return "East";
        } else if (pos < w + h - 1) {
            return "North";
        } else if (pos < 2 * w + h - 2) {
            return "West";
        } else {
            return "South";
        }
    }
}