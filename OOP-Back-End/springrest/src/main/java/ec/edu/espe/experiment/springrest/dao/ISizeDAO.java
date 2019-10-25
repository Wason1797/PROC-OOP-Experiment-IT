package ec.edu.espe.experiment.springrest.dao;

import java.util.List;

import ec.edu.espe.experiment.springrest.dto.Size;
import ec.edu.espe.experiment.springrest.model.DBSize;

public interface ISizeDAO{
    public List<Size> getAll();
    public Size get(Integer id);
    public Size put(Size entity);
    public Size post(Size entity);
    public Size toSize(DBSize dbSize);
}