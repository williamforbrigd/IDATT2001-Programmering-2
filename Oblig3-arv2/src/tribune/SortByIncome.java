package tribune;

import java.util.Comparator;

public class SortByIncome implements Comparator<Tribune> {

    @Override
    public int compare(Tribune t1, Tribune t2) {
        return Double.compare(t1.findIncome(),t2.findIncome());
    }
}
