package com.hericode.reactiveproject.operador.transformacion;

import com.hericode.reactiveproject.model.Persona;
import com.hericode.reactiveproject.operador.creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {

    private static final Logger log = LoggerFactory.getLogger(Transformacion.class);

    //permite transformar los elementos que vienen en el flujo
    public void map(){
        /*List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .map(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return p;
                })
                .subscribe(p -> log.info(p.toString()));*/

        //crea un flujo a partir de un rango de numeros y luego usa map para modificar los datos en el
        Flux<Integer> fx = Flux.range(0,10);
        Flux<Integer> fx2 = fx.map(x -> x + 10);
        fx2.subscribe(x -> log.info("X :" + x));
    }

    //con este operador de puede retornar otro flujo distinto
    public void flatMap(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad()+10);
                    return Mono.just(p);
                })
                .subscribe(p -> log.info(p.toString()));
    }

    //agrupa los datos del flujo de acuerdo al elemennto pasado
    public void groupBy(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(1, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .groupBy(Persona::getIdPersona)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(x -> log.info(x.toString()));
    }
}
