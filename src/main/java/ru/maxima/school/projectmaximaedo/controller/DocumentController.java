package ru.maxima.school.projectmaximaedo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.DocumentDto;
import ru.maxima.school.projectmaximaedo.serviceImpl.DocumentServiceImpl;
import ru.maxima.school.projectmaximaedo.utils.Response;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    private final DocumentServiceImpl documentService;
    @Autowired
    public DocumentController(DocumentServiceImpl documentService) {
        this.documentService = documentService;
    }
    /**
     * Считывание списка документов
     * @return списка документов или ошибку
     */
    @GetMapping()
    public ResponseEntity<List<DocumentDto>> getDocuments() {
        List<DocumentDto> documents = documentService.getAll();
        if(documents == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список документов пуст");
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    /**
     * Считывание документа
     * @param id
     * @return документ или ошибку
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable("id") Long id) {
        DocumentDto documentDto = documentService.getById(id);
        if(documentDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Документ с " + id + " не найден");
        }
        return new ResponseEntity<>(documentDto, HttpStatus.OK);
    }

    /**
     * Обновление документа
     * @param id
     * @param documentDto
     * @return статус обновления документа
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateDocument(@PathVariable("id") Long id,
                                                  @Valid @RequestBody DocumentDto documentDto) {
        if (documentService.update(documentDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Документ с идентификатором " + id + " не найден");
        }
        return ResponseEntity.accepted().body(new Response("Обновление документа прошло успешно", LocalDateTime.now()));
    }

    /**
     * Создание документа
     * @param documentDto
     * @return статус создания документа
     */
    @PostMapping()
    public ResponseEntity<Response> createDocument(@Valid @RequestBody DocumentDto documentDto) {
        if (documentService.create(documentDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ошибка в создании документа");
        }
        return ResponseEntity.accepted().body(new Response("Создание документа прошло успешно", LocalDateTime.now()));
    }

    /**
     * Удаление документа
     * @param id
     * @return статус удаления документа
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDocument(@PathVariable("id") Long id) {
        if (documentService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление документа прошло успешно", LocalDateTime.now()));
    }

}
