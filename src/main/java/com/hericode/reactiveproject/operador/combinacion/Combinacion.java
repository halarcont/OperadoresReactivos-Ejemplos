package com.hericode.reactiveproject.operador.combinacion;

import com.hericode.reactiveproject.model.Persona;
import com.hericode.reactiveproject.model.Venta;
import com.hericode.reactiveproject.operador.creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {

    private static final Logger log = LoggerFactory.getLogger(Combinacion.class);

    //combina varios flujos de datos
    public void merge(){

        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "heri", 27));
        personas1.add(new Persona(2, "may", 25));
        personas1.add(new Persona(3, "pepe", 29));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "pablito", 27));
        personas2.add(new Persona(5, "clavo", 25));
        personas2.add(new Persona(6, "unclavito", 29));

        List<Venta> ventas =  new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.merge(fx1, fx2, fx3)
                .subscribe(p -> log.info(p.toString()));
    }

    ///lo mismo que el anterior, se espera que por lo menos todos los flujos tengan valores
    public void zip(){

        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "heri", 27));
        personas1.add(new Persona(2, "may", 25));
        personas1.add(new Persona(3, "pepe", 29));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "pablito", 27));
        personas2.add(new Persona(5, "clavo", 25));
        personas2.add(new Persona(6, "unclavito", 29));

        List<Venta> ventas =  new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.zip(fx1,fx2, fx3)
                .subscribe(x -> log.info(x.toString()));

    }

    ///similar al anterior lo que se necesita un flujo previo
    public void zipWith(){

        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1, "heri", 27));
        personas1.add(new Persona(2, "may", 25));
        personas1.add(new Persona(3, "pepe", 29));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4, "pablito", 27));
        personas2.add(new Persona(5, "clavo", 25));
        personas2.add(new Persona(6, "unclavito", 29));

        List<Venta> ventas =  new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        fx1.zipWith(fx3, (p1, v1) -> String.format("Flux1: %s, Flux3: %s", p1, v1))
                .subscribe(x -> log.info(x.toString()));
    }
}
