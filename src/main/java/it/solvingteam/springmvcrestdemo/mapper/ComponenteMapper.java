package it.solvingteam.springmvcrestdemo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.springmvcrestdemo.dto.ComponenteDTO;
import it.solvingteam.springmvcrestdemo.model.Componente;
import it.solvingteam.springmvcrestdemo.service.PcService;

@Component
public class ComponenteMapper extends AbstractMapper<Componente, ComponenteDTO> {
	
    @Autowired
    private PcService pcService;
    
	@Autowired
	private PcMapper pcMapper;

	@Override
	public ComponenteDTO convertEntityToDto(Componente entity) {
        if (entity == null) {
            return null;
        }

        ComponenteDTO componenteDTO = new ComponenteDTO();
        componenteDTO.setId(entity.getId().toString());
        componenteDTO.setMarca(entity.getMarca());
        componenteDTO.setCodice(entity.getCodice());
        componenteDTO.setDescrizione(entity.getDescrizione());
        componenteDTO.setPc_id(entity.getPc().getId().toString());
        
        return componenteDTO;
	}

	@Override
	public Componente convertDtoToEntity(ComponenteDTO dto) throws Exception {
        if (dto == null) {
            return null;
        }

        Componente componente = new Componente();

        if (dto.getId() != null) {
        	componente.setId(Integer.valueOf(dto.getId()));
        }
        
        componente.setMarca(dto.getMarca());
        componente.setCodice(dto.getCodice());
        componente.setDescrizione(dto.getDescrizione());
        componente.setPc(pcMapper.convertDtoToEntity(pcService.getById(dto.getPc_id())));

        return componente;
	}

}
