package com.hericode.reactiveproject;

import com.hericode.reactiveproject.operador.combinacion.Combinacion;
import com.hericode.reactiveproject.operador.condicional.Condicional;
import com.hericode.reactiveproject.operador.error.ErrorOp;
import com.hericode.reactiveproject.operador.filtrado.Filtrado;
import com.hericode.reactiveproject.model.Persona;
import com.hericode.reactiveproject.operador.matematico.Matematico;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactiveProjectApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ReactiveProjectApplication.class);

    public void reactor(){
        Mono.just(new Persona(1, "Heri", 27))
                .doOnNext(p -> {
                    log.info("[Reactor} Persona:" + p);
                })
                .subscribe(p -> log.info("[Reactor} Persona:" + p));

    }

    public void rxjava2(){
        Observable.just(new Persona(1, "Heri", 27))
                .doOnNext(p ->log.info("[Reactor} Persona:" + p))
                .subscribe(p -> log.info("[Rxjava2} Persona:" + p));

    }

    public void mono(){
        Mono.just(new Persona(1, "heri", 27)).subscribe(p -> log.info((p.toString())));
    }

    public void flux(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux.fromIterable(personas).subscribe(p -> log.info(p.toString()));
    }

    public void fluxMono(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "heri", 27));
        personas.add(new Persona(2, "may", 25));
        personas.add(new Persona(3, "pepe", 29));

        Flux<Persona> fx = Flux.fromIterable(personas);
        fx.collectList().subscribe(lista -> log.info(lista.toString()));

    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //mono();
        //flux();
        //fluxMono();

        //Creacion app = new Creacion();
        //app.range();
        //app.repeat();

        //Transformacion app = new Transformacion();
        //app.map();
        //app.flatMap();
        //app.groupBy();

        //Filtrado app = new Filtrado();
        //app.filter();
        //app.distinct();
        //app.take();
        //app.takeLast();
        //app.skip();
        //app.skipLast();

        //Combinacion app = new Combinacion();
        //app.merge();
        //app.zip();
        //app.zipWith();

        //ErrorOp app = new ErrorOp();
        //app.retry();
        //app.errorReturn();
        //app.errorResume();
        //app.errorMap();

        //Condicional app = new Condicional();
        //app.defaultIfEmpty();
        ///app.takeUntil();
        //app.timeout();

        Matematico app = new Matematico();
        //app.average();
        //app.count();
        //app.min();
        //app.sum();
        app.summarizing();

    }

    @Override
    public String toString() {
        return "ReactiveProjectApplication{}";
    }
}
