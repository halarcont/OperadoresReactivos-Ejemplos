package com.hericode.reactiveproject.operador.creacion;

import com.hericode.reactiveproject.model.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {

    private static final Logger log = LoggerFactory.getLogger(Creacion.class);

    public void justFrom(){
        Mono.just(new Persona(1, "heri", 27));
        //Flux.fromIterable(coleccion);
        //Observable.just(item);

    }
    //para expresar flujos vacios
    public void empty(){
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }
    //crear un flujo de datos a partir de un rango de numeros
    public void range(){
        /*el primer numero se considera >= y el segundo numero es <,
        o sea que el ultimo valor no se va a considerar*/
        Flux.range(0, 3)
                .doOnNext(i -> log.info("i:" + i))
                .subscribe();
    }

    //repetir un flujo de datos de acuerdo al numero que se le pase
    public void repeat(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        /*Flux.fromIterable(personas)
                .repeat(3)
                .subscribe(p -> log.info(p.toString()));*/

        Mono.just(new Persona(1, "heri", 27))
                .repeat(3)
                .subscribe(x -> log.info(x.toString()));

    }



}
