package com.free.fluxocaixa.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/fluxocaixa")
public class CaixaController {
    @Autowired
    private CaixaService caixaService;
    @GetMapping("/caixa/{inicio}/{fim}")
    public ResponseEntity<List<CaixaDTO>> getCaixaByPeriod(@PathVariable("inicio") @DateTimeFormat(pattern = "dd/MM/yyyy") String dtInicio,
                                                           @PathVariable("fim") @DateTimeFormat(pattern = "dd/MM/yyyy") String dtFim) {
         return ResponseEntity.ok(caixaService.getCaixaByPeriodOrderId(dtInicio, dtFim).stream().map(CaixaDTO::new).collect(Collectors.toList()));

    }

    @GetMapping("/consolidado_diario/{data}")
    public ResponseEntity<List<CaixaDTO>> getCaixaByPeriod(@PathVariable("data") @DateTimeFormat(pattern = "dd/MM/yyyy") String dtInformada) {
        return ResponseEntity.ok(caixaService.getCaixaByPeriodOrderId(dtInformada, dtInformada).stream().map(CaixaDTO::new).collect(Collectors.toList()));
    }
    @GetMapping("/caixa/{id}")
    public ResponseEntity<CaixaDTO> getCaixa(@PathVariable("id") Long id) {
        return  ResponseEntity.ok(new CaixaDTO(caixaService.getCaixa(id)));
    }
    @PostMapping("/insert_caixa")
    public ResponseEntity insertcaixa(@RequestBody Caixa caixa) {
        Caixa t = caixaService.insertCaixa(caixa);
        return ResponseEntity.ok(new CaixaDTO(t));
    }
    @PutMapping("/update_caixa/{id}")
    public ResponseEntity<CaixaDTO> updateCaixa(@PathVariable("id") Long id, @RequestBody Caixa caixa) {
        Caixa t = caixaService.updateCaixa(caixa,id);
        return ResponseEntity.ok(new CaixaDTO(t));
    }
    @DeleteMapping("/delete_caixa/{id}")
    public String deleteCaixa(@PathVariable("id") Long id) {
        caixaService.deleteCaixa(id);
        return "Lançamento deletado com sucesso!";
    }

}
