class Robot {
    private int W;
    private int H;
    private int P;
    private int pos;
    private boolean moved;

    public Robot(int width, int height) {
        this.W = width;
        this.H = height;
        this.P = 2 * (W + H - 2); 
        this.pos = 0;
        this.moved = false;
    }
    
    public void step(int num) {
        moved = true;
        pos = (pos + num) % P; 
    }
    
    public int[] getPos() {
        if (pos < W) {
            return new int[]{pos, 0};
        } else if (pos < W + H - 1) {
            return new int[]{W - 1, pos - (W - 1)};
        } else if (pos < 2 * W + H - 2) {
            return new int[]{W - 1 - (pos - (W + H - 2)), H - 1};
        } else {
            return new int[]{0, H - 1 - (pos - (2 * W + H - 3))};
        }
    }
    
    public String getDir() {
        if (pos == 0) {
            return moved ? "South" : "East";
        } else if (pos < W) {
            return "East";
        } else if (pos < W + H - 1) {
            return "North";
        } else if (pos < 2 * W + H - 2) {
            return "West";
        } else {
            return "South";
        }
    }
}