package ec.edu.espe.experiment.springrest.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.edu.espe.experiment.springrest.dao.ISizeDAO;
import ec.edu.espe.experiment.springrest.dto.Size;
import ec.edu.espe.experiment.springrest.model.DBSize;
import ec.edu.espe.experiment.springrest.repo.ISizeRepo;

@Repository
public class SizeDAO implements ISizeDAO{

    @Autowired
    private ISizeRepo repo;

    @Override
    public List<Size> getAll(){
        List<Size> list = new ArrayList<>();
        try{
            List<DBSize> list_dbSize = repo.findAll();
            if(list_dbSize != null){
                for(DBSize dbSize : list_dbSize){
                    Size order = new Size();
                    order = toSize(dbSize);
                    list.add(order);
                }
            }
        }
        catch(Exception e){
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public Size get(Integer id){
        Size size = null;
        try{
            Optional<DBSize> dbSize = repo.findById(id);
            if(dbSize != null){
                size = toSize(dbSize.get());
            }
        }
        catch(Exception e){
            size = null;
        }
        return size;
    }

    @Override
    public Size post(Size size){       
        Size response = new Size();
        try{
            DBSize dbSize = new DBSize(size.getName(), 
                size.getPrice());
            repo.save(dbSize);
            repo.flush();
            response = toSize(dbSize);
        }
        catch(Exception e){
            response = null;
        }
        return response;
    }

    @Override
    public Size put(Size size){
        Size response = null;
        try{
            Optional<DBSize> dbSize = repo.findById(size.get_id());
            if(dbSize != null){
                DBSize aux = new DBSize();
                aux.setId(dbSize.get().getId());
                aux.setName(size.getName() != null ? size.getName() : dbSize.get().getName());
                aux.setPrice(size.getPrice() != null ? size.getPrice() : dbSize.get().getPrice());
                repo.save(aux);
                repo.flush();
                response = toSize(aux);
            }            
        }
        catch(Exception e){
            response = null;
        }
        return response;
    }

    @Override
    public Size toSize(DBSize dbSize){
        return new Size(dbSize.getId(), 
            dbSize.getName(), 
            dbSize.getPrice());
    }
}