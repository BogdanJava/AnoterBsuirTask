package com.bsuir.service;

import com.bsuir.model.Fowl;
import org.springframework.ui.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface FowlService {
    int save(Fowl fowl) throws IOException, SQLException;
    Fowl getFowl(String fowlName);
    Fowl getFowl(int fowlId);
    List<Fowl> getAll();
    void update(Fowl fowl);
    boolean delete(long fowlId);
    void delete(List<String> fowlIdList);
    Fowl getFowl(Model model);
}
