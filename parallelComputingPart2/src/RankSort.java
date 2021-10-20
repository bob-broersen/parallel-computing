public class RankSort {
    public RankSort(int[] readArray, int[] resultArray, int numberOfThreads) {



            int blockSize = readArray.length / numberOfThreads;

            RankSortThread threads[] = new RankSortThread[numberOfThreads];
            for(int i = 0, j = 0; i < numberOfThreads; i++, j += blockSize) {
                threads[i] = new RankSortThread(readArray, resultArray, numberOfThreads, i);
                threads[i].start();
            }

            try {
                for(int i = 0; i < numberOfThreads; i++) {
                    threads[i].join();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
    }
}


