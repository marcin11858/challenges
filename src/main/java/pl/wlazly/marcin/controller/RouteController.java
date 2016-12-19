package pl.wlazly.marcin.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.wlazly.marcin.model.DirectConnectionResponse;
import pl.wlazly.marcin.service.RouteService;

/**
 * Created by marcin on 17.12.16.
 */
@Controller
@RequestMapping("/api")
public class RouteController {

    @Autowired
    RouteService routeService;

    final static Logger logger = Logger.getLogger(RouteController.class);

    @GetMapping("/direct")
    @ResponseBody
    public DirectConnectionResponse isDirectConnection(@RequestParam("dep_sid") Integer departureId,
                                                       @RequestParam("arr_sid") Integer arrivedId) {
        return new DirectConnectionResponse(departureId, arrivedId,
                routeService.isDirectConnection(departureId, arrivedId));
    }
}
