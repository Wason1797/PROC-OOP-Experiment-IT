package ec.edu.espe.experiment.springrest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.experiment.springrest.dao.IIngredientDAO;
import ec.edu.espe.experiment.springrest.dto.Ingredient;



@RequestMapping("/api/ingredient")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})

public class RestIngredientController {

    @Autowired
    private IIngredientDAO dao;

    @GetMapping
    public List<Ingredient> getAll() {        
        return dao.getAll();
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredient> get(@PathVariable("id") Integer id) {
        Ingredient response =  dao.get(id);
        if(response != null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredient> post(@RequestBody Ingredient entity) {
        Ingredient response =  dao.post(entity);
        if(response != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredient> put(@RequestBody Ingredient entity){
        Ingredient response = dao.put(entity);
        if(response != null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
}
