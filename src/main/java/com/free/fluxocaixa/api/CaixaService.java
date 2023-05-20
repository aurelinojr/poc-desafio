package com.free.fluxocaixa.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CaixaService {
    @Autowired
    private CaixaRepository caixaRepository;
    public List<Caixa> getCaixaByPeriodOrderId(String dtInicio, String dtFim) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate inicio;
            LocalDate fim;
            if (dtInicio.length() == 0 || dtFim.length() == 0) {
                LocalDate dataAtual = LocalDate.now();
                inicio = dataAtual.minusDays(30);
                fim = dataAtual;
            } else {
                inicio = LocalDate.parse(dtInicio, formatter);
                fim = LocalDate.parse(dtFim, formatter);
            }
            List<Caixa> consolidadoDiario = new ArrayList<>();
            Float saldo_anterior = caixaRepository.getSaldoAnterior(inicio);
            Float saldo_atual = saldo_anterior;
            List<Caixa> lancamentos = caixaRepository.findAllByCxa_DataBetween(inicio, fim);
            for (Caixa lancamento : lancamentos) {
                saldo_atual += lancamento.getCxa_valor();
                Caixa caixa = new Caixa();
                caixa.setSaldo_anterior(saldo_anterior);
                caixa.setCxa_id(lancamento.getCxa_id());
                caixa.setCxa_desc(lancamento.getCxa_desc());
                caixa.setCxa_oper(lancamento.getCxa_oper());
                caixa.setCxa_valor((lancamento.getCxa_valor()));
                caixa.setCxa_data(lancamento.getCxa_data());
                caixa.setSaldo_atual(saldo_atual);
                consolidadoDiario.add(caixa);
                saldo_anterior =  saldo_atual;
            }
            return consolidadoDiario;
        } catch (DateTimeParseException e) {
            throw new RuntimeException("A data informada é inválida! Formato dd-mm-yyyy");
        }
    }


    public Caixa insertCaixa(Caixa caixa) {
       return caixaRepository.save(caixa);
    }

    public Caixa updateCaixa(Caixa caixa, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro!");
        Optional<Caixa> cxa = caixaRepository.findById(id);
        if (cxa.isPresent()) {
            Caixa db = cxa.get();
            db.setCxa_desc(caixa.getCxa_desc());
            db.setCxa_valor(caixa.getCxa_valor());
            db.setCxa_oper(caixa.getCxa_oper());
            db.setCxa_data(caixa.getCxa_data());
            return caixaRepository.save(db);
        } else {
            throw new RuntimeException("Lançamento não encontrado!");
        }
    }

    public void deleteCaixa(Long id) {
        Assert.notNull(id,"Não foi possível excluir o registro!");
        Optional<Caixa> cxa = caixaRepository.findById(id);
        if (cxa.isPresent()) {
            caixaRepository.deleteById(id);
        }  else {
            throw new RuntimeException("Lançamento não encontrado!");
        }
    }

    public Caixa getCaixa(Long id) {
        Assert.notNull(id,"Não foi possível obter o registro!");
        Optional<Caixa> cxa = caixaRepository.findById(id);
        if (cxa.isPresent()) {
            return cxa.get();
        }  else {
            throw new RuntimeException("Lançamento não encontrado!");
        }
    }
}
