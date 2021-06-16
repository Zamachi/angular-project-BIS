package rs.ac.singidunum.appbackend.services;

public interface iAutoMapperService {
    <T> T map(Object model, Class<T> entity);
}
