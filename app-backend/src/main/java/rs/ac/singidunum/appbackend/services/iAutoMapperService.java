package rs.ac.singidunum.appbackend.services;

public interface iAutoMapperService {
    public <T> T map(Object model, Class<T> entity);
}
