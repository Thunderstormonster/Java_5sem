package bstu.kachanova.javaproject.demo.service.interfaces;

import bstu.kachanova.javaproject.demo.models.Scooter;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IScooterService {
    @Transactional
    void deleteById(Long id) throws ServiceException;

    Scooter create(Scooter computerStuff)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    boolean existsByName(String name) throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    List<Scooter> getAll()throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;
    @Transactional
    void deleteByName(String name)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    Scooter getById(Long id)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    Scooter getByName(String name)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    @Transactional
    void updateScooterById(
            Long id,
            String name,
            String description,
            int cost)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;
}

