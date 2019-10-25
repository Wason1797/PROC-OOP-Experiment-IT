package ec.edu.espe.experiment.springrest.seeder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ec.edu.espe.experiment.springrest.model.DBDetailOrder;
import ec.edu.espe.experiment.springrest.model.DBIngredient;
import ec.edu.espe.experiment.springrest.model.DBOrder;
import ec.edu.espe.experiment.springrest.model.DBSize;
import ec.edu.espe.experiment.springrest.repo.IDetailOrderRepo;
import ec.edu.espe.experiment.springrest.repo.IIngredientRepo;
import ec.edu.espe.experiment.springrest.repo.IOrderRepo;
import ec.edu.espe.experiment.springrest.repo.ISizeRepo;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private IIngredientRepo repoIngredient;

    @Autowired
    private ISizeRepo repoSize;

    @Autowired
    private IOrderRepo repoOrder;

     @Autowired
    private IDetailOrderRepo repodetail;

   
    @Autowired
    public DatabaseSeeder(IIngredientRepo repoIngredient){
        this.repoIngredient = repoIngredient;
    }

    @Override
    public void run(String... strings) throws Exception {

        List<DBIngredient> ingredients = new ArrayList<>();
        ingredients.add(new DBIngredient("Salami", (float) 0.50));
        ingredients.add(new DBIngredient("Peperoni", (float) 0.25));
        ingredients.add(new DBIngredient("queso", (float) 0.4));        
        repoIngredient.saveAll(ingredients);
        repoIngredient.flush();

        List<DBSize> sizes = new ArrayList<>();
        sizes.add(new DBSize("Mediana", (float) 15));
        sizes.add(new DBSize("Pequeña", (float) 17));
        sizes.add(new DBSize("Familiar", (float) 20)); 
        repoSize.saveAll(sizes);
        repoSize.flush();
        
        List<DBOrder> orders= new ArrayList<>();
        DBOrder ord = new DBOrder("Pepito Peccas", "1789654123", "Sangolqui", "0984625859", new Date(), 15.50f);
        ord.setSize(sizes.get(0));
        orders.add(ord);
        repoOrder.saveAll(orders);
        repoOrder.flush();

        List<DBDetailOrder> details = new ArrayList<>();

        ingredients.get(0).setId(1);
        orders.get(0).setId(1);

        DBDetailOrder detail = new DBDetailOrder(ingredients.get(0).getId(), orders.get(0).getId());
        detail.setPriceIngredient(ingredients.get(0).getPrice());
        detail.setOrder(orders.get(0));
        detail.setIngredient(ingredients.get(0));
        
        details.add(detail);
        repodetail.saveAll(details);
        

    }
}