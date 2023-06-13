package ru.maxima.school.projectmaximaedo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.AttachedFileDto;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import ru.maxima.school.projectmaximaedo.model.AttachedFile;
import ru.maxima.school.projectmaximaedo.serviceImpl.AttachedFileServiceImpl;
import ru.maxima.school.projectmaximaedo.utils.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/files")
public class AttachedFileController {
    private final AttachedFileServiceImpl fileService;

    @Autowired
    public AttachedFileController(AttachedFileServiceImpl fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/dir")
    public ResponseEntity<List<AttachedFile>> getFilesFromDir() {
        List<AttachedFile> files = null;
        try {
            files = fileService.readFilesFromDir();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
        if (files == null || files.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "В папке нет файлов для скачивания");
        }
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> createFileFromDir(@RequestBody AttachedFile file) {
        fileService.create(file);
        return ResponseEntity.accepted().body(new Response("Создание файла прошло успешно", LocalDateTime.now()));
    }

    @GetMapping()
    public ResponseEntity<List<AttachedFileDto>> getFiles(){
        List<AttachedFileDto> files = fileService.getAll();
        if(files == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список файлов  пуст");
        }
        return new ResponseEntity<>(files, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AttachedFileDto> getFile(@PathVariable("id") Long id) {
        AttachedFileDto file = fileService.getById(id);
        if(file == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Файл с " + id + " не найден");
        }
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateFile(@PathVariable("id") Long id,
                                               @Valid @RequestBody AttachedFileDto attachedFileDto) {
        if (fileService.update(attachedFileDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Файл с " + id + " не найден");
        }
        return ResponseEntity.accepted().body(new Response("Обновление файла прошло успешно", LocalDateTime.now()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteFile(@PathVariable("id") Long id) {
        if (fileService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление файла прошло успешно", LocalDateTime.now()));
    }
}
