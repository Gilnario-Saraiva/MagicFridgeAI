package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItem> criar(@RequestBody FoodItem foodItem){

        FoodItem salvo = foodItemService.salvar(foodItem);
        return ResponseEntity.ok(salvo);
    }
    @GetMapping
    public ResponseEntity<List<FoodItem>> listar(){
        List<FoodItem> lista = foodItemService.listar();
        return ResponseEntity.ok(lista);
    }
    @DeleteMapping
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        foodItemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItem> atualizar(@RequestBody FoodItem foodItemAtualizado, @PathVariable Long id){
        FoodItem foodOriginal = foodItemService.buscarPorId(id).stream().findFirst().orElse(null);
        if(foodOriginal != null){
            foodItemAtualizado.setId(foodOriginal.getId());
            FoodItem foodItem = foodItemService.alterar(foodItemAtualizado);
            return ResponseEntity.ok(foodItem);
        }
        return ResponseEntity.notFound().build();
    }
}
