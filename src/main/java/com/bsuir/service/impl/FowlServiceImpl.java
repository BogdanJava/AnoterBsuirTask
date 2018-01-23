package com.bsuir.service.impl;

import com.bsuir.dao.FowlDao;
import com.bsuir.dao.utils.DaoUtils;
import com.bsuir.model.Fowl;
import com.bsuir.service.FowlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Если в слое Repository мы работали напрямую с базой данных, то в этом слое мы работаем только с методами
 * Repository-объекта, а также обрабатываем возможные ошибки, осуществляем логирование.
 */

@Service
public class FowlServiceImpl implements FowlService {

    private static final Logger logger = LoggerFactory.getLogger(FowlServiceImpl.class);

    @Autowired
    private FowlDao fowlDao;

    @Override
    public Fowl getFowl(Model model) {
        Map<String, Object> modelMap = model.asMap();
        Fowl fowl = new Fowl();
        if (modelMap != null) {
            if (modelMap.get("id") != null)
                fowl.setId((int) modelMap.get("id"));
            if (modelMap.get("number") != null)
                fowl.setNumber((int) modelMap.get("number"));
            if (modelMap.get("name") != null)
                fowl.setName((String) modelMap.get("name"));
            if (modelMap.get("description") != null)
                fowl.setDescription((String) modelMap.get("description"));
            return fowl;
        } else return null;
    }

    @Override
    public int save(Fowl fowl) throws IOException, SQLException {
        if (fowl != null) {
            logger.debug("fowl added: " + fowl.toString());
            return fowlDao.save(fowl);
        } else {
            logger.debug("fowl not added");
            return -1;
        }
    }

    @Override
    public Fowl getFowl(String fowlName) {
        if (fowlName != null && !fowlName.equals("")) {
            Fowl fowl = fowlDao.getFowlByName(fowlName);
            logger.debug("get fowl: " + fowl.toString());
            return fowl;
        } else {
            logger.debug("can't get fowl: " + fowlName);
            return null;
        }
    }

    @Override
    public Fowl getFowl(int fowlId) {
        if (fowlId > 0) {
            Fowl fowl = fowlDao.getFowlById(fowlId);
            if (fowl != null) {
                logger.debug("get fowl by id: " + fowl.toString());
                return fowl;
            } else {
                logger.debug("can't get fowl by id");
                return null;
            }
        } else {
            logger.debug("can't get fowl by id");
            return null;
        }
    }

    @Override
    public List<Fowl> getAll() {
        logger.debug("get all fowls");
        return fowlDao.getAll();
    }

    @Override
    public void update(Fowl fowl) {
        if (fowl != null) {
            fowlDao.update(fowl);
            logger.debug("fowl updated: " + fowl.toString());
        } else {
            logger.debug("can't update fowl");
        }
    }

    @Override
    public boolean delete(long fowlId) {
        if (fowlId > 0) {
            if (fowlDao.deleteById(fowlId)) {
                logger.debug("fowl deleted by id: " + fowlId);
                return true;
            } else {
                logger.debug("fowl not deleted by id: " + fowlId);
                return false;
            }
        } else {
            logger.debug("can't delete fowl by id: " + fowlId);
            return false;
        }
    }

    @Override
    public void delete(List<String> fowlIdList) {
        for(String currId : fowlIdList){
            long id = Long.parseLong(currId);
            delete(id);
        }
    }
}
