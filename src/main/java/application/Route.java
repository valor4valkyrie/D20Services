package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class Route {

    @Autowired
    private Environment env;

    public String run() {
         String username = env.getProperty("spring.datasource.username");
        return username;
    }
/*
    @Bean
    public RouterFunction route(){
        return new RouterFunction() {
           /* @Override
            public Mono<HandlerFunction> route(ServerRequest request) {
                return RoutterFunctions.route(RequestPredicates.GET("/test")
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_STREAM_JSON)),
                        stats::stats);
            }
        }
    }*/

}
