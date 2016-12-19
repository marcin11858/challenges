package pl.wlazly.marcin.model;

import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by marcin on 18.12.16.
 */
public class Routes {

    private final Map<Integer, List<Integer>> busStations;

    private final Boolean completed;

    public Routes(Map<Integer, List<Integer>> busStations, Boolean completed) {
        this.busStations = busStations;
        this.completed = completed;
    }

    public Map<Integer, List<Integer>> getBusStations() {
        return busStations;
    }

    public Boolean isCompleted() {
        return completed;
    }
}
