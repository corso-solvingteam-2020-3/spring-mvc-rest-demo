package it.solvingteam.springmvcrestdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.springmvcrestdemo.dto.ComponenteDTO;
import it.solvingteam.springmvcrestdemo.mapper.ComponenteMapper;
import it.solvingteam.springmvcrestdemo.model.Componente;
import it.solvingteam.springmvcrestdemo.repository.ComponenteRepository;

@Service
public class ComponenteService {

	@Autowired
	private ComponenteRepository componenteRepository;
	    
	@Autowired
	private ComponenteMapper componenteMapper;
	    
    public List<ComponenteDTO> findAll() {
        List<Componente> allComponenti = this.componenteRepository.findAll();
        return componenteMapper.convertEntityToDto(allComponenti);
    }

    public ComponenteDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Componente l= componenteRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(l==null) {
    		throw new Exception("Componente non trovato");
    	}
    	return componenteMapper.convertEntityToDto(l);
    }
    
    public void delete(String id) throws Exception {
    	ComponenteDTO l = this.getById(id);
    	componenteRepository.delete(componenteMapper.convertDtoToEntity(l));
    }

	public ComponenteDTO inserisci(ComponenteDTO componenteDTO) throws Exception {
		if(componenteDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		if(componenteDTO == null || componenteDTO.getMarca()==null || componenteDTO.getMarca().isEmpty() || 
				componenteDTO.getCodice() == null || componenteDTO.getCodice().isEmpty() ||
				componenteDTO.getDescrizione() == null || componenteDTO.getDescrizione().isEmpty()) {
			throw new Exception("Input non valido");
		}
		
		return this.getById(componenteRepository.save(componenteMapper.convertDtoToEntity(componenteDTO)).getId().toString());
	}

	public ComponenteDTO aggiorna(ComponenteDTO componenteDTO) throws Exception {
		this.getById(componenteDTO.getId());
		if(componenteDTO == null || componenteDTO.getMarca()==null || componenteDTO.getMarca().isEmpty() || 
				componenteDTO.getCodice() == null || componenteDTO.getCodice().isEmpty() ||
				componenteDTO.getDescrizione() == null || componenteDTO.getDescrizione().isEmpty()) {
			throw new Exception("Input non valido");
		}
		
		return this.getById(componenteRepository.save(componenteMapper.convertDtoToEntity(componenteDTO)).getId().toString());		
	}

	private boolean isNaN(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

}
