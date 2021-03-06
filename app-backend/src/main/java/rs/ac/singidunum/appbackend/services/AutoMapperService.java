package rs.ac.singidunum.appbackend.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoMapperService implements iAutoMapperService {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public <T> T map(Object model, Class<T> entity) {
        return modelMapper.map(model, entity);
    }
}
