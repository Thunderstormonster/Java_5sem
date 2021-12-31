package bstu.kachanova.javaproject.demo.service;

import bstu.kachanova.javaproject.demo.exception.RepositoryException;
import bstu.kachanova.javaproject.demo.exception.ServiceException;
import bstu.kachanova.javaproject.demo.models.Scooter;
import bstu.kachanova.javaproject.demo.repository.BookRepository;
import bstu.kachanova.javaproject.demo.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//слой сервиса, прослойка между контролером и слоем ресурсов,
// используется для получения данных из ресурса, их проверки и преобразования

@Service
public class BookService implements IBookService {
    //добавляем инъекцию
    //присваиывание значение бина
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Scooter create(Scooter Scooter)throws ServiceException {
        return bookRepository.save(Scooter);
    }

    @Override
    public boolean existsByName(String name) throws ServiceException {
        try {
            return bookRepository.existsByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Scooter> getAll()throws ServiceException {
        return bookRepository.findAll();
    }

    @Override
    public void deleteByName(String name)throws ServiceException {
        try {
            bookRepository.deleteByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Scooter getById(Long id)throws ServiceException {
        try {
            return bookRepository.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public Scooter getByName(String name)throws ServiceException {
        try {
            return bookRepository.getByName(name);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public void updateBookById(Long id, String name, String description, int cost ) throws ServiceException{
        try {
            bookRepository.updateBookById(id, name, description, cost);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}

