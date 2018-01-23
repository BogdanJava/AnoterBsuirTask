package com.bsuir.dao;

import com.bsuir.model.Fowl;

import java.sql.Connection;
import java.util.List;

public interface FowlDao {
    int save(Fowl fowl);
    Fowl getFowlByName(String fowlName);
    Fowl getFowlById(int fowlId);
    List<Fowl> getAll();
    void update(Fowl fowl);
    boolean deleteById(long fowlId);
}
