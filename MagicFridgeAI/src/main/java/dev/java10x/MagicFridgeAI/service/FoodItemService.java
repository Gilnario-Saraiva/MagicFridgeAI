package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<FoodItem> buscarPorId(Long id){
        return foodItemRepository.findById(id);
    }

    public void deletar(Long id){
        foodItemRepository.deleteById(id);
    }

    public FoodItem alterar(FoodItem foodItem){
        Optional<FoodItem> original = buscarPorId(foodItem.getId());
        if(original.isPresent()){
            return foodItemRepository.save(foodItem);
        }
        return null;
    }
}
