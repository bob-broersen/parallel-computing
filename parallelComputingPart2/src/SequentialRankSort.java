public class SequentialRankSort {
    public SequentialRankSort(int[] readArray, int[] resultArray) {
        int currentItem;
        int currentPosition;

        for (int j = 0; j < readArray.length; j++) {
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
            resultArray[currentPosition] = currentItem;
        }
    }
}
