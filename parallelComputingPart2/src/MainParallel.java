public class MainParallel {


    public static void main(String[] args) {
        int[] threads = {1,2,4,6,8};
        int[] dataSizes = {30000, 60000, 90000, 120000, 150000, 180000, 210000, 240000, 270000, 300000};
        for (int dataSize : dataSizes) {
            System.out.println("data size: " + dataSize);
            int[] readArray = createUnsortedArray(dataSize);
            int[] resultArray = new int[dataSize];
            for (int thread : threads) {
                long start = System.currentTimeMillis();

                RankSort rankSort = new RankSort(readArray, resultArray, thread);
                long elapsedTimeMillis = System.currentTimeMillis() - start;
                System.out.println("For thread " + thread);
                System.out.println("Time to peform algorithm: " + elapsedTimeMillis);

                if (isSorted(resultArray)) {
                    System.out.println("Array is sorted correctly");
                } else {
                    System.out.println("Array is not sorted");
                }
            }
        }
    }

    public static int[] createUnsortedArray(int elementCount)  {
        int[] resultArray = new int[elementCount];
        for (int i = 0; i < elementCount; i++) {
            resultArray[i] = elementCount % (i + 3);
        }
        return resultArray;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
