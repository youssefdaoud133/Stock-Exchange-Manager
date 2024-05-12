package oop.stockexchangemanager.Database;

public class OwnedStocks extends Collections<Integer> {
    public Integer update(Integer key, Integer value) {
        if(!data.containsKey(key))
           return create(key,value);
        else
            return data.merge(key, value, Integer::sum);
    }
}
