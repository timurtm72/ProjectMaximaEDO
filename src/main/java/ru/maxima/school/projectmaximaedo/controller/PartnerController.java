package ru.maxima.school.projectmaximaedo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.PartnerDto;
import ru.maxima.school.projectmaximaedo.serviceImpl.PartnerServiceImpl;
import ru.maxima.school.projectmaximaedo.utils.Response;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    private final PartnerServiceImpl partnerService;
    @Autowired
    public PartnerController(PartnerServiceImpl partnerService) {
        this.partnerService = partnerService;
    }

    /**
     * Считывание списка партнеров
     * @return списка партнеров или ошибку
     */
    @GetMapping()
    public ResponseEntity<List<PartnerDto>> getPartners() {
        List<PartnerDto> partners = partnerService.getAll();
        if(partners == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список партнеров пуст");
        }
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }

    /**
     * Считывание партнера
     * @param id
     * @return партнера или ошибку
     */
    @GetMapping("/{id}")
    public ResponseEntity<PartnerDto> getPartner(@PathVariable("id") Long id) {
        PartnerDto partnerDto = partnerService.getById(id);
        if(partnerDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Партнер с " + id + " не найден");
        }
        return new ResponseEntity<>(partnerDto, HttpStatus.OK);
    }

    /**
     * Обновление партнера
     * @param id
     * @param partnerDto
     * @return статус обновления партнера
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updatePartner(@PathVariable("id") Long id,
                                                  @Valid @RequestBody PartnerDto partnerDto) {
        if (partnerService.update(partnerDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Партнер с идентификатором " + id + " не найден");
        }
        return ResponseEntity.accepted().body(new Response("Обновление партнера прошло успешно", LocalDateTime.now()));
    }

    /**
     * Создание партнера
     * @param partnerDto
     * @return статус создания партнера
     */
    @PostMapping()
    public ResponseEntity<Response> createPartner(@Valid @RequestBody PartnerDto partnerDto) {
        if (partnerService.create(partnerDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ошибка в создании партнера");
        }
        return ResponseEntity.accepted().body(new Response("Создание партнера прошло успешно", LocalDateTime.now()));
    }

    /**
     * Удаление партнера
     * @param id
     * @return статус удаления партнера
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePartner(@PathVariable("id") Long id) {
        if (partnerService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление партнера прошло успешно", LocalDateTime.now()));
    }

}
