package com.hericode.reactiveproject.operador.condicional;

import com.hericode.reactiveproject.model.Persona;
import com.hericode.reactiveproject.operador.error.ErrorOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Condicional {

    private static final Logger log = LoggerFactory.getLogger(Condicional.class);

    //retorna los elementos indicados en caso de vacio
    public void defaultIfEmpty() {
        //Mono.just(new Persona(1, "heri", 27))
        //Mono.empty()
        Flux.empty()
                .defaultIfEmpty(new Persona(0, "default", 99))
                .subscribe(x -> log.info(x.toString()));
    }

    //emite flujos hasta que suceda un valor que cumpla con la expresion indicada
    public void takeUntil(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 28));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .takeUntil(p -> p.getEdad() > 26)
                .subscribe(x -> log.info(x.toString()));
    }

    /*lanza una excepcion si la lectura demora mas de lo que se le indica esperar,
    el operador delayElements() indica el tiempo de lectura de emision de cada dato*/
    public void timeout() throws InterruptedException {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 28));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                //si los segundos aca son menores que 2 on va a haber problemas
                .delayElements(Duration.ofSeconds(3))
                .timeout(Duration.ofSeconds(2))
                .subscribe(x -> log.info(x.toString()));

        //para que el hilo principal de la app siga viviendo hasta que los demas procesos asincronos terminen su ejecucion
        Thread.sleep(10000);

    }
}
