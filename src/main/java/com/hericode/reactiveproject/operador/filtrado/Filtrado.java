package com.hericode.reactiveproject.operador.filtrado;

import com.hericode.reactiveproject.model.Persona;
import com.hericode.reactiveproject.operador.creacion.Creacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {

    private static final Logger log = LoggerFactory.getLogger(Filtrado.class);

    //filtra los elementos segun los datos proporcionados
    public void filter(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .filter(p -> p.getEdad() > 28)
                .subscribe(p -> log.info(p.toString()));
    }

    /*evitar los elementos duplicados.
    se hace necesario generar en el modelo los metodos equals() and hashCode() */
    public void distinct(){
        //ejemplo 1
        Flux.fromIterable(List.of(1,1,2,2))
                .distinct()
                .subscribe(p -> log.info(p.toString()));

        //ejemplo 2
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(1, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .distinct()
                .subscribe(p -> log.info(p.toString()));
    }

    /*devuelve solamente los elementos del flujo (desde el inicio) indicado por parametro,
     en este ejemplo se toman los 2 primeros elementos*/
    public void take(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .take(2)
                .subscribe(p -> log.info(p.toString()));
    }

    /*hace lo mismo que el anterior pero de atras hacia alante, o sea,
    en este ejemplo se toma el ultimo elemento del flujo, depende del numero de elementos que se le indique,
    si se pusiera un 2 se tomarian los dos ultimos*/
    public void takeLast() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .takeLast(1)
                .subscribe(p -> log.info(p.toString()));
    }

    //evita tomar el elemento del flujo indicado, en este ejemplo se evita el 1er elemento
    public void skip() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .skip(1)
                .subscribe(p -> log.info(p.toString()));
    }

    //lo mismo que el anterior pero de atras hacia alante
    public void skipLast() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .skipLast(1)
                .subscribe(p -> log.info(p.toString()));
    }

}
