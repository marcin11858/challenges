package pl.wlazly.marcin.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wlazly.marcin.model.Routes;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by marcin on 17.12.16.
 */
@Service
public class RouteService {

    @Autowired
    Routes routes;

    private final static Logger logger = Logger.getLogger(RouteService.class);

    public Boolean isDirectConnection(Integer departureId, Integer arrivalId) {
        if (routes.getBusStations().containsKey(departureId) && routes.getBusStations().containsKey(arrivalId)) {
            List<Integer> intersection = new LinkedList<>(routes.getBusStations().get(departureId));
            intersection.retainAll(routes.getBusStations().get(arrivalId));
            return !intersection.isEmpty();
        } else {
            return false;
        }
    }

}
