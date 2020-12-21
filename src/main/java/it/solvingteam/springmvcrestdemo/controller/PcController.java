package it.solvingteam.springmvcrestdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.springmvcrestdemo.dto.ErrorDTO;
import it.solvingteam.springmvcrestdemo.dto.PcDTO;
import it.solvingteam.springmvcrestdemo.service.PcService;

@RestController
@RequestMapping("pc")
public class PcController {

    @Autowired
    private PcService pcService;

    @PutMapping
    public ResponseEntity<PcDTO> update(@RequestBody PcDTO pcDTO) throws Exception {
    	return ResponseEntity.ok().body(pcService.aggiorna(pcDTO));
    }
    
    @PostMapping
    public ResponseEntity<PcDTO> create(@RequestBody PcDTO pcDTO) throws Exception {
    	return ResponseEntity.ok().body(pcService.inserisci(pcDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<PcDTO> delete(@PathVariable("id") String id) throws Exception {
		pcService.delete(id);
		return ResponseEntity.ok().build();

    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<PcDTO> getOne(@PathVariable("id") String id) throws Exception {
		return ResponseEntity.ok().body(pcService.getById(id));
    }    
    
    @GetMapping
    public ResponseEntity<List<PcDTO>> getAll() {
    	List<PcDTO> resultPcsDTO = pcService.findAll();
        return ResponseEntity.ok().body(resultPcsDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }

}
