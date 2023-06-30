package com.hericode.reactiveproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    private Integer idVenta;
    private LocalDateTime fecha;

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fecha=" + fecha +
                '}';
    }
}
