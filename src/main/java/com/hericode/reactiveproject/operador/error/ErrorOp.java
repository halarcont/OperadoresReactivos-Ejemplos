package com.hericode.reactiveproject.operador.error;

import com.hericode.reactiveproject.model.Persona;
import com.hericode.reactiveproject.operador.creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ErrorOp {

    private static final Logger log = LoggerFactory.getLogger(ErrorOp.class);

    //ejecuta nuevamente el codigo segun las veces indicadas
    public void retry(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ERROR")))
                .retry(1)
                .doOnNext(x -> log.info(x.toString()))
                .subscribe();
    }

    //permite controlar que lanzar en caso de algun error, el operador concatWith() permite concatenar operadores
    public void errorReturn(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ERROR")))
                .onErrorReturn(new Persona(0, "xyz", 99))
                .subscribe(x -> log.info(x.toString()));

    }

    ///similar al anterior, permite devolver un valor de respaldo en forma de Flux o Mono
    public void errorResume(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ERROR")))
                .onErrorResume(e -> Mono.just(new Persona(0, "xyz", 99)))
                .subscribe(x -> log.info(x.toString()));
    }

    //permite manejar excepciones personalizadas
    public void errorMap(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ERROR")))
                .onErrorMap(e -> new InterruptedException(e.getMessage()))
                .subscribe(x -> log.info(x.toString()));
    }
}
