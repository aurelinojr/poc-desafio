package com.free.fluxocaixa.api;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    @Query(value="select cxa_id,\n" +
                 "   cxa_desc,\n" +
                 "   cxa_data,\n" +
                 "   cxa_valor,\n" +
                 "   cxa_oper  from caixa as c where c.cxa_data between :dtInicio and :dtFim order by c.cxa_id",
            nativeQuery = true)
    List<Caixa> findAllByCxa_DataBetween(@Param("dtInicio") LocalDate dtInicio, @Param("dtFim") LocalDate dtFim);

    @Query(value = "SELECT COALESCE(SUM((CASE cxa_oper WHEN 'C' THEN cxa_valor ELSE cxa_valor * -1 END)),0) AS saldo_anterior " +
            "FROM caixa WHERE cxa_data < :data", nativeQuery = true)
    Float getSaldoAnterior(@Param("data") Date data);

    default Float getSaldoAnterior(LocalDate data) {
        Date sqlDate = Date.valueOf(data);
        return getSaldoAnterior(sqlDate);
    }
}
