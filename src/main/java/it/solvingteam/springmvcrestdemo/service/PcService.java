package it.solvingteam.springmvcrestdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.springmvcrestdemo.dto.PcDTO;
import it.solvingteam.springmvcrestdemo.mapper.PcMapper;
import it.solvingteam.springmvcrestdemo.model.Pc;
import it.solvingteam.springmvcrestdemo.repository.PcRepository;

@Service
public class PcService {

	@Autowired
	private PcRepository pcRepository;
	    
	@Autowired
	private PcMapper pcMapper;
	    
    public List<PcDTO> findAll() {
        List<Pc> allPcs = this.pcRepository.findAll();
        return pcMapper.convertEntityToDto(allPcs);
    }

    public PcDTO getById(String id) throws Exception {
    	if(id==null || isNaN(id)) {
    		throw new Exception("id non valido");
    	}
    	Pc l= pcRepository.findById(Integer.parseInt(id)).orElse(null);
    	if(l==null) {
    		throw new Exception("Pc non trovato");
    	}
    	return pcMapper.convertEntityToDto(l);
    }
    
    public void delete(String id) throws Exception {
    	PcDTO l = this.getById(id);
    	pcRepository.delete(pcMapper.convertDtoToEntity(l));
    }

	public PcDTO inserisci(PcDTO pcDTO) throws Exception {
		if(pcDTO.getId()!=null) {
			throw new Exception("Id deve essere null");
		}
		if(pcDTO == null || pcDTO.getMarca()==null || pcDTO.getMarca().isEmpty() || 
				pcDTO.getDescrizione() == null || pcDTO.getDescrizione().isEmpty()) {
			throw new Exception("Input non valido");
		}
		return this.getById(pcRepository.save(pcMapper.convertDtoToEntity(pcDTO)).getId().toString());
	}

	public PcDTO aggiorna(PcDTO pcDTO) throws Exception {
		this.getById(pcDTO.getId());
		if(pcDTO == null || pcDTO.getMarca()==null || pcDTO.getMarca().isEmpty() || 
				pcDTO.getDescrizione() == null || pcDTO.getDescrizione().isEmpty()) {
			throw new Exception("Input non valido");
		}
		
		return this.getById(pcRepository.save(pcMapper.convertDtoToEntity(pcDTO)).getId().toString());		
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
