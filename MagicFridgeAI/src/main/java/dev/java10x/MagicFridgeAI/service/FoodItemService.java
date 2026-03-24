package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem salvar(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> listar(){
        return foodItemRepository.findAll();
    }

    public FoodItem listarPorId(Long id){
        return foodItemRepository.findById(id).orElse(null);
    }

    public void deletar(Long id){
        foodItemRepository.deleteById(id);
    }

    public FoodItem alterar(FoodItem foodItem){
        FoodItem original = listarPorId(foodItem.getId());
        if(original != null){
            return foodItemRepository.save(foodItem);
        }
        return null;
    }
}
