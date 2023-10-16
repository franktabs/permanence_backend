package com.afriland.dsi.permanence.controllers;

import com.afriland.dsi.permanence.controllers.abstracts.modelConvert.MonthConvertController;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path = "month")
public class MonthController extends MonthConvertController {

   /* @Autowired
    MonthService monthService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MonthDto>> allMonth() {
        List<Month> months = monthService.getAllMonth();
        List<MonthDto> monthDtos = new ArrayList<>();
        for (Month month : months) {
            monthDtos.add(convertMonthToDto(month, 1, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(monthDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MonthDto> getOneMonth(@PathVariable Long id){
        Month month = monthService.getMonthById(id);
        if(month==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertMonthToDto(month, 1, 1, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody MonthDto monthDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Month month = convertDtoToMonth(monthDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertMonthToDto(monthService.create(month), 1, 1, 1));

        } catch (DataIntegrityViolationException e) {
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici" + message + "\n\n");
            if (message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
    }

    public static MonthDto convertMonthToDto(Month month, int depthPermanence, int depthPlanning, int depthSuperviseur) {
        MonthDto monthDto = new MonthDto(
                month.getId(),
                month.getName(),
                month.getNumero(),
                month.getStart(),
                month.getEnd()
        );

        if (depthPermanence > 0) {
            Set<PermanenceDto> permanenceDtos = new HashSet<>();
            if (month.getPermanences() != null) {
                for (Permanence permanence : month.getPermanences()) {
                    permanenceDtos.add(PermanenceController.convertPermanenceToDto(permanence, 1, 1, depthPermanence - 1));
                }
            }

            monthDto.setPermanences(permanenceDtos);
        }

        if(depthPlanning > 0){
            if(month.getPlanning()!=null){
                monthDto.setPlanning(PlanningController.convertPlanningToDto(month.getPlanning(), depthPlanning-1));
            }
        }

        if(depthSuperviseur>0){
            if(month.getSuperviseur()!=null){
                monthDto.setSuperviseur(PersonnelController.convertPersonnelToDto(month.getSuperviseur(), 1, 1, 1, 1, 1, depthSuperviseur-1, 1, 0, 0));
            }
        }

        return monthDto;
    }

    public static Month convertDtoToMonth(MonthDto monthDto){
        Month month = new Month(
                monthDto.getId(),
                monthDto.getName(),
                monthDto.getNumero(),
                monthDto.getStart(),
                monthDto.getEnd()
        );

        Set<Permanence> permanences = new HashSet<>();
        if(monthDto.getPermanences()!=null){
            for(PermanenceDto permanenceDto : monthDto.getPermanences()){
                permanences.add(PermanenceController.convertDtoToPermanence(permanenceDto));
            }
        }
        month.setPermanences(permanences);

        if(monthDto.getPlanning()!=null){
            month.setPlanning(PlanningController.convertDtoToPlanning(monthDto.getPlanning()));
        }

        if(monthDto.getSuperviseur()!=null){
            month.setSuperviseur(PersonnelController.convertDtoToPersonnel(monthDto.getSuperviseur()));
        }

        return month;
    }*/


}
