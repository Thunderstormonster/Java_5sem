package bstu.kachanova.javaproject.demo.service;

import bstu.kachanova.javaproject.demo.exception.RepositoryException;
import bstu.kachanova.javaproject.demo.exception.ServiceException;
import bstu.kachanova.javaproject.demo.models.RentForm;
import bstu.kachanova.javaproject.demo.repository.UserRentFormRepository;
import bstu.kachanova.javaproject.demo.service.interfaces.IUserRentFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserRentFormService implements IUserRentFormService {
    @Autowired
    private UserRentFormRepository userRentFormRepository;
    @Override
    public void deleteById(Long id) {
        userRentFormRepository.deleteById(id);
    }

    @Override
    public void deleteByUserIdAndScooterId(Long user_id, Long computerStuff_id) throws ServiceException {
        try {
            userRentFormRepository.deleteByUserIdAndScooterId(user_id, computerStuff_id);
        } catch (RepositoryException e) {

            throw new ServiceException(e);
        }
    }

    @Override
    public RentForm create(RentForm userRentForm){
        return userRentFormRepository.save(userRentForm);
    }

    @Override
    public boolean existsByBookId(Long computerStuff_id) throws ServiceException {
        try {
            return userRentFormRepository.existsByScooterId(computerStuff_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public RentForm getById(Long id)throws ServiceException {
        return userRentFormRepository.getById(id);
    }

    @Override
    public List<RentForm> getAllByUserId(Long user_id)throws ServiceException {
        try {
            return userRentFormRepository.getAllByUserId(user_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public List<RentForm> getAllByRent(boolean rent)throws ServiceException {
        try {
            return userRentFormRepository.getAllByRent(rent);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public List<RentForm> getAllByBookExpirationDateLessThan(Date computerStuff_expirationDate) throws ServiceException{
        try {
            return userRentFormRepository.getAllByScooterExpirationDateLessThan(computerStuff_expirationDate);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }

    @Override
    public void setUserRentFormById(Long id, boolean rent) throws ServiceException{
        try {
            userRentFormRepository.setUserRentFormById(id, rent);
        } catch (RepositoryException e) {
            throw new ServiceException(e);

        }
    }
}

