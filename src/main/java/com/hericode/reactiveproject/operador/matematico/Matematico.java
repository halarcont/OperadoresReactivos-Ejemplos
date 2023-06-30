package com.hericode.reactiveproject.operador.matematico;

import com.hericode.reactiveproject.model.Persona;
import com.hericode.reactiveproject.operador.condicional.Condicional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Matematico {

    private static final Logger log = LoggerFactory.getLogger(Matematico.class);

    //permite obtener el promedio de un conjunto de datos
    public void average(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 28));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(x -> log.info(x.toString()));
    }

    //cuenta cuantos elementos hay en el flujo
    public void count(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 28));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .count()
                .subscribe(x -> log.info("Cantidad:" + x));
    }

    //obtiene el menor elemento comparando con el valor pasado por parametro
    public void min(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 28));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(p -> log.info(p.get().toString()));

    }

    //suma los elementos del flujo de datos
    public void sum(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 28));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(x -> log.info("suma: " + x));

    }

    //realiza un resumen de todas las operaciones de acuerdo a parametro pasado
    public void summarizing(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 28));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas)
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(x -> log.info("Resumen: " + x));
    }


}
