package com.akash.modifier;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("modifier/modify")
@RequiredArgsConstructor
public class ModifierController {

    private final ModifierService modifierService;
    @DeleteMapping("deleteFeature")
    public ResponseEntity<ArrayList<ArrayList<String>>> deleteFeature(@RequestBody ArrayList<ArrayList<String>> data,
                                                                      @RequestParam("index") Integer index) throws IndexOutOfBoundsException{
        return ResponseEntity.ok(modifierService.deleteOneIndex(data,index));
    }

    @DeleteMapping("deleteFeature/{indexes}")
    public ResponseEntity<ArrayList<ArrayList<String>>> deleteMultipleFeature(@RequestBody ArrayList<ArrayList<String>> data,
                                                              @PathVariable("indexes") List<Integer> indexes) throws IndexOutOfBoundsException{

        return ResponseEntity.ok(modifierService.deleteIndexes(data,new ArrayList<>(indexes)));
    }

    @PostMapping("addData")
    public ResponseEntity<ArrayList<ArrayList<String>>> addData(@RequestBody DataAddRequest<ArrayList<String>> dataAddRequest){
        return ResponseEntity.ok(
                modifierService.addAttribute(
                        dataAddRequest.getOperationalData(),
                        dataAddRequest.getAddOnData()));
    }

    @PostMapping("addMultipleData")
    public ResponseEntity<ArrayList<ArrayList<String>>> addMultipleData(@RequestBody DataAddRequest<ArrayList<ArrayList<String>>> dataAddRequest){
        return ResponseEntity.ok(
                modifierService.addMultipleAttribute(
                        dataAddRequest.getOperationalData(),
                        dataAddRequest.getAddOnData()));
    }

}
