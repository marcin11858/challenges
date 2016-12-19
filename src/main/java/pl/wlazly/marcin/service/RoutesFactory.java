package pl.wlazly.marcin.service;

import org.springframework.stereotype.Service;
import pl.wlazly.marcin.model.Routes;

import java.util.*;

/**
 * Created by marcin on 18.12.16.
 */
@Service
public class RoutesFactory {
    private RoutesFactory.RouteState state = RoutesFactory.RouteState.EMPTY;

    private Map<Integer, List<Integer>> busStations = new HashMap<>();

    private Set<Integer> routeIds = new TreeSet<>();

    private Integer totalRoutes = 0;

    public void setTotalRoutes(Integer totalRoutes) {
        if (state == RoutesFactory.RouteState.EMPTY) {
            state = RoutesFactory.RouteState.PROCESSING;
            this.totalRoutes = totalRoutes;
        } else {
            clearData();
        }
    }

    public void processRout(final Integer routeId, final List<Integer> routBusStations) {
        if (!routeIds.contains(routeId) && state == RoutesFactory.RouteState.PROCESSING) {
            routeIds.add(routeId);
            for (Integer busStationId : routBusStations) {
                if (!busStations.containsKey(busStationId)) {
                    busStations.put(busStationId, new LinkedList<>());
                }
                busStations.get(busStationId).add(routeId);
            }
            if (totalRoutes.equals(routeIds.size())) {
                state = RoutesFactory.RouteState.COMPLETED;
            }
        } else {
            clearData();
        }
    }

    public Routes build() {
        return new Routes(busStations, state == RouteState.COMPLETED);
    }

    private void clearData() {
        state = RoutesFactory.RouteState.DATA_ERROR;
        busStations = Collections.emptyMap();
        routeIds = Collections.emptySet();
        totalRoutes = 0;
    }

    private enum RouteState {
        EMPTY, PROCESSING, COMPLETED, DATA_ERROR
    }


}
