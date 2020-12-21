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
import it.solvingteam.springmvcrestdemo.dto.ComponenteDTO;
import it.solvingteam.springmvcrestdemo.service.ComponenteService;

@RestController
@RequestMapping("componente")
public class ComponenteController {

    @Autowired
    private ComponenteService componenteService;

    @PutMapping
    public ResponseEntity<ComponenteDTO> update(@RequestBody ComponenteDTO componenteDTO) throws Exception {
    	return ResponseEntity.ok().body(componenteService.aggiorna(componenteDTO));
    }
    
    @PostMapping
    public ResponseEntity<ComponenteDTO> create(@RequestBody ComponenteDTO componenteDTO) throws Exception {
    	return ResponseEntity.ok().body(componenteService.inserisci(componenteDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ComponenteDTO> delete(@PathVariable("id") String id) throws Exception {
		componenteService.delete(id);
		return ResponseEntity.ok().build();

    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<ComponenteDTO> getOne(@PathVariable("id") String id) throws Exception {
		return ResponseEntity.ok().body(componenteService.getById(id));
    }    
    
    @GetMapping
    public ResponseEntity<List<ComponenteDTO>> getAll() {
    	List<ComponenteDTO> resultComponentiDTO = componenteService.findAll();
        return ResponseEntity.ok().body(resultComponentiDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }


}
