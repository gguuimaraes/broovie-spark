package br.com.broovie.contrato;

import java.util.List;

public interface OperacoesDao<E> {
    void create(E entidade);
    E findOne(Long id);
    List<E> findAll();
    void update(E entidade);
    void delete(E entidade);
}