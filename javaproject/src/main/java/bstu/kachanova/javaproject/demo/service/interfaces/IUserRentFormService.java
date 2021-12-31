package bstu.kachanova.javaproject.demo.service.interfaces;

import bstu.kachanova.javaproject.demo.models.RentForm;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IUserRentFormService {
    @Transactional
    void deleteById(Long id)throws ServiceException;
    @Transactional
    void deleteByUserIdAndScooterId(Long user_id, Long computerStuff_id) throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    RentForm create(RentForm userRentForm)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;
    boolean existsByBookId(Long computerStuff_id) throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;
    RentForm getById(Long id)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    List<RentForm> getAllByUserId(Long user_id)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;
    List<RentForm> getAllByRent(boolean rent)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;

    List<RentForm> getAllByBookExpirationDateLessThan(Date computerStuff_expirationDate)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;
    @Transactional
    void setUserRentFormById(Long id, boolean rent)throws ServiceException, bstu.kachanova.javaproject.demo.exception.ServiceException;
}
