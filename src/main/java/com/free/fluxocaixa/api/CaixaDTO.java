package com.free.fluxocaixa.api;


import jakarta.persistence.Transient;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class CaixaDTO {
    private Long cxa_id;
    private String cxa_desc;
    private Float cxa_valor;
    private String cxa_oper;
    private LocalDate cxa_data;
    @Transient
    private Float saldo_anterior;
    @Transient
    private Float saldo_atual;
    public CaixaDTO(Caixa caixa) {
        this.cxa_id = caixa.getCxa_id();
        this.cxa_desc = caixa.getCxa_desc();
        this.cxa_valor = caixa.getCxa_valor();
        this.cxa_oper = caixa.getCxa_oper();
        this.cxa_data = caixa.getCxa_data();
        this.saldo_anterior = caixa.getSaldo_anterior();
        this.saldo_atual = caixa.getSaldo_atual();
    }
}
