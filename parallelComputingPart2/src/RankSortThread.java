class RankSortThread extends Thread {
    private int[] readArray;
    private int[] writeArray;
    private int threadNumber;
    private int threadIndex;

    private int currentItem;
    private int currentPosition;
    private int firstIndex;
    private int lastIndex;
    private int currentThreadNum;

    public RankSortThread(int[] readArray, int[] resultArray, int threadNum, int threadIndex) {
        this.readArray = readArray;
        this.writeArray = resultArray;
        this.threadNumber = threadNum;
        this.threadIndex = threadIndex;

        this.currentItem = 0;
        this.currentPosition = 0;
        this.firstIndex = 0;
        this.lastIndex = 0;
        this.currentThreadNum = 0;
    }

    @Override
    public void run() {
        currentThreadNum = threadIndex;

        int blockSize = (int) Math.ceil(readArray.length / threadNumber);
        firstIndex = currentThreadNum * blockSize;
        if (threadNumber - 1 == currentThreadNum) {
            lastIndex = readArray.length;
        } else {
            lastIndex = (currentThreadNum + 1) * blockSize;
        }

        // Rank sort
        for (int j = firstIndex; j < lastIndex; j++) {
            currentItem = readArray[j];
            currentPosition = 0;
            for (int i = 0; i < readArray.length; i++) {
                if (currentItem > readArray[i]) {
                    currentPosition++;
                }
                if ((currentItem == readArray[i]) && (j < i)) {
                    currentPosition++;
                }
            }
            writeArray[currentPosition] = currentItem;
        }

    }

}