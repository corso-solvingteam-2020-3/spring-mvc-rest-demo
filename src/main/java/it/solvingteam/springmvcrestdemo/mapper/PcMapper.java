package it.solvingteam.springmvcrestdemo.mapper;

import org.springframework.stereotype.Component;

import it.solvingteam.springmvcrestdemo.dto.PcDTO;
import it.solvingteam.springmvcrestdemo.model.Pc;

@Component
public class PcMapper extends AbstractMapper<Pc, PcDTO> {

	@Override
	public PcDTO convertEntityToDto(Pc entity) {
        if (entity == null) {
            return null;
        }

        PcDTO pcDTO = new PcDTO();
        
        pcDTO.setId(entity.getId().toString());
        pcDTO.setMarca(entity.getMarca());
        pcDTO.setDescrizione(entity.getDescrizione());
        
        return pcDTO;
	}

	@Override
	public Pc convertDtoToEntity(PcDTO dto) {
	     if (dto == null) {
	      return null;
	     }
	
	     Pc pc = new Pc();
	
	     if (dto.getId() != null) {
	    	 pc.setId(Integer.valueOf(dto.getId()));
	     }
	     
	     pc.setMarca(dto.getMarca());
	     pc.setDescrizione(dto.getDescrizione());
	     
	     return pc;
	}

}
