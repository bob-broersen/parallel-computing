import java.io.Serializable;
import java.util.HashMap;

public class ArrayPart implements Serializable {
    /**
     * Serialized id
     */
    private static final long serialVersionUID = 4566087626035759882L;
    private Integer[] readArray;
    private HashMap<Integer, Integer> writeMap;
    private int firstIndex;
    private int lastIndex;



    public ArrayPart(Integer[] totalValues, HashMap<Integer, Integer> writeMap, int firstIndex, int lastIndex) {
        this.readArray = totalValues;
        this.writeMap = writeMap;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
    }

    public void addToWriteMap(Integer key, Integer value){
        writeMap.put(key, value);
    }

    public HashMap<Integer, Integer> getWriteMap() {
        return writeMap;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }


    public Integer[] getReadArray() {
        return readArray;
    }


}
