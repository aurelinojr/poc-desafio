package com.free.fluxocaixa.api;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "caixa")
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cxa_id;
    @Column(nullable = false)
    private String cxa_desc;
    @Column(nullable = false,precision = 18, scale = 2, columnDefinition = "DECIMAL(18, 2)")
    private Float cxa_valor;
    @Column(nullable = false, columnDefinition = "char(1)")
    private String cxa_oper;
    @Column(nullable = false)
    private LocalDate cxa_data;
    @Transient
    private Float saldo_anterior;
    @Transient
    private Float saldo_atual;
}