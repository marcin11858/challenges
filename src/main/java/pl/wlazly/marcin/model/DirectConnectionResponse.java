package pl.wlazly.marcin.model;

/**
 * Created by marcin on 18.12.16.
 */
public class DirectConnectionResponse {

    private final Integer dep_sid;

    private final Integer arr_sid;

    private final Boolean direct_bus_route;

    public DirectConnectionResponse(Integer dep_sid, Integer arr_sid, Boolean direct_bus_route) {
        this.dep_sid = dep_sid;
        this.arr_sid = arr_sid;
        this.direct_bus_route = direct_bus_route;
    }

    public Integer getDep_sid() {
        return dep_sid;
    }

    public Integer getArr_sid() {
        return arr_sid;
    }

    public Boolean getDirect_bus_route() {
        return direct_bus_route;
    }
}
