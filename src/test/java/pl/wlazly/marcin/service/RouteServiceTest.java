package pl.wlazly.marcin.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wlazly.marcin.BusRouteChallengeConfiguration;
import pl.wlazly.marcin.model.Routes;

import java.util.*;

/**
 * Created by marcin on 18.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceTest {

    @InjectMocks
    RouteService routeService;

    @Mock
    Routes routes;

    @Test
    public void shouldNotFoundDirectConnection() {

        Mockito.when(routes.getBusStations()).thenReturn(generateTestData());

        Assert.assertFalse(routeService.isDirectConnection(500, 572));

    }

    @Test
    public void shouldFoundDirectConnection() {
        Mockito.when(routes.getBusStations()).thenReturn(generateTestData());

        Assert.assertTrue(routeService.isDirectConnection(1, 3));
    }

    private Map<Integer, List<Integer>> generateTestData() {
        Map<Integer, List<Integer>> routes = new HashMap<>();
        routes.put(1, Arrays.asList(1,2,3));
        routes.put(2, Arrays.asList(4,5));
        routes.put(3, Arrays.asList(1,4));
        routes.put(4, Arrays.asList(8,9,10));
        routes.put(5, Arrays.asList(7,9));
        return routes;
    }


}
